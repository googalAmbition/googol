package com.tcoding.demo.base.concurrent;

import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
}