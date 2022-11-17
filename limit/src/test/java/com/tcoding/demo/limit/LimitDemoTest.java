package com.tcoding.demo.limit;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Resource;

/**
 * @author 陈天成
 * @date 2022/11/17.
 */
class LimitDemoTest extends LimitApplicationTests {

    @Resource
    private LimitDemo demo;

    @Test
    public void count() throws InterruptedException {
        process(() -> demo.count());
    }

    @Test
    public void flag() throws InterruptedException {
        process(() -> demo.flag());
    }

    @Test
    public void window() throws InterruptedException {
        process(() -> demo.window());
    }

    @Test
    public void bucket() throws InterruptedException {
        process(() -> demo.bucket());
    }

    @Test
    public void token() throws InterruptedException {
        process(() -> demo.token());
    }

    private void process(Process process) throws InterruptedException {
        CompletableFuture<?>[] objects = IntStream.range(0, 10).mapToObj(i -> CompletableFuture.runAsync(() -> {
            while (true) {
                process.execute();
            }
        })).collect(Collectors.toList()).toArray(new CompletableFuture<?>[] {});
        CompletableFuture.allOf(objects);
        new CountDownLatch(1).await();
    }

    @FunctionalInterface
    public interface Process {

        void execute();
    }
}