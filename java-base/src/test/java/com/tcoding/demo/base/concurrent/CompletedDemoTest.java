package com.tcoding.demo.base.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 陈天成
 * @date 2022/10/18.
 */
public class CompletedDemoTest {

    @Test
    public void completeFu() {

        List<CompletableFuture<Integer>> list = IntStream.range(0, 10)
            .mapToObj(i -> CompletableFuture.supplyAsync(() -> sleep(i)))
            .collect(Collectors.toList());

        /**
         * 有一个执行完返回
         */
        CompletableFuture.anyOf(list.toArray(new CompletableFuture[] {}))
            .exceptionally(x -> {
                System.out.println(x.getMessage());
                return -1;
            })
            .thenAccept(System.out::println).join();
    }

    @Test
    public void completeAll() {
        /**
         * 所有执行完
         */
        List<CompletableFuture<Void>> list = IntStream.range(0, 10)
            .mapToObj(i -> CompletableFuture.runAsync(this::run))
            .collect(Collectors.toList());
        CompletableFuture.allOf(list.toArray(new CompletableFuture[] {}))
            .exceptionally(x -> {
                System.err.println(x.getMessage());
                return null;
            })
            .thenAccept(System.out::println).join();
    }

    public int sleep(int index) {
        Random random = new Random();
        int i = random.nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(i);
            if (index == 5) {
                throw new RuntimeException();
            }
            System.out.println("===========  " + index + " ============");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return index;
    }

    public void run() {
        long begin = System.currentTimeMillis();
        // 自定义一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 循环创建10个CompletableFuture
        List<CompletableFuture<Integer>> collect = IntStream.range(1, 10).mapToObj(i -> CompletableFuture.supplyAsync(() -> {
            // 在i=5的时候抛出一个NPE
            if (i == 5) {
                throw new NullPointerException();
            }
            try {
                // 每个依次睡眠1-9s，模拟线程耗时
                TimeUnit.SECONDS.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            return i;
        }, executorService)
            // 这里处理一下i=5时出现的NPE
            // 如果这里不处理异常，那么异常会在所有任务完成后抛出,小伙伴可自行测试
            .exceptionally(error -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 100;
            })).collect(Collectors.toList());
        // List列表转成CompletableFuture的Array数组,使其可以作为allOf()的参数
        // 使用join()方法使得主线程阻塞，并等待所有并行线程完成
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[] {})).join();
        System.out.println("最终耗时" + (System.currentTimeMillis() - begin) + "毫秒");
        executorService.shutdown();
    }

    public Future<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });
        return completableFuture;
    }

    @Test
    public void get() throws ExecutionException, InterruptedException {
        Future<String> future = calculateAsync();
        String s = future.get();
        Assert.assertEquals("Hello", s);
    }

    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
        Assert.assertEquals("Hello", future.get());
    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
            = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture
            .thenApply(s -> s + " World");
        Assert.assertEquals("Hello World", future.get());
    }

    @Test
    public void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
            = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
            .thenAccept(s -> System.out.println("Computation returned: " + s));

        future.get();
    }

    @Test
    public void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
            = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> future = completableFuture
            .thenRun(() -> System.out.println("Computation finished."));

        future.get();
    }

    @Test
    public void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
            = CompletableFuture.supplyAsync(() -> "Hello")
            .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        Assert.assertEquals("Hello World", completableFuture.get());
    }

    @Test
    public void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
            = CompletableFuture.supplyAsync(() -> "Hello")
            .thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2);

        Assert.assertEquals("Hello World", completableFuture.get());
    }

    @Test
    public void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> "Hello")
            .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                (s1, s2) -> System.out.println(s1 + s2));
    }

    @Test
    public void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
            = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
            = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
            = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
            = CompletableFuture.allOf(future1, future2, future3);

        // ...

        combinedFuture.get();

        Assert.assertTrue(future1.isDone());
        Assert.assertTrue(future2.isDone());
        Assert.assertTrue(future3.isDone());

        String combined = Stream.of(future1, future2, future3)
            .map(CompletableFuture::join)
            .collect(Collectors.joining(" "));

        Assert.assertEquals("Hello Beautiful World", combined);
    }

    @Test
    public void handle() throws ExecutionException, InterruptedException {
        String name = null;

        // ...

        CompletableFuture<String> completableFuture
            = CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : "Hello, Stranger!");

        Assert.assertEquals("Hello, Stranger!", completableFuture.get());

        completableFuture.completeExceptionally( new RuntimeException("Calculation failed!"));
        completableFuture.get(); // ExecutionException
    }

    @Test
    public void thenApplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture
            = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture
            .thenApplyAsync(s -> s + " World");

        Assert.assertEquals("Hello World", future.get());
    }
}