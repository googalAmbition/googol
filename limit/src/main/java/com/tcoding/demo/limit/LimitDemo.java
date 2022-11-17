package com.tcoding.demo.limit;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;

/**
 * @author 陈天成
 * @date 2022/11/17.
 */
@Component
public class LimitDemo {

    /**
     * 计数器
     */
    private final Semaphore count = new Semaphore(5);

    /**
     * 信号量
     */
    private final Semaphore flag = new Semaphore(5);

    /**
     * 滑动窗口
     */
    private final AtomicInteger[] window = new AtomicInteger[10];

    /**
     * 漏桶
     */
    private final BlockingQueue<Long> queue = new LinkedBlockingDeque<>(5);

    /**
     * 令牌桶
     */
    private final BlockingQueue<Integer> token = new LinkedBlockingDeque<>(5);

    @PostConstruct
    public void init() {
        //初始化定时任务线程池
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(2, t -> {
            Thread thread = new Thread(t);
            thread.setName("limit");
            return thread;
        });
        // 每10s执行5次
        service.scheduleAtFixedRate(() -> count.release(5), 10, 10, TimeUnit.SECONDS);

        // 10个窗口，每次滑动1s
        Arrays.fill(window, new AtomicInteger(0));
        service.scheduleAtFixedRate(() -> {
            int index = (int) (System.currentTimeMillis() / 1000 % 10);
            window[index] = new AtomicInteger(0);
        }, 1, 1, TimeUnit.SECONDS);

        // 一恒定的速率执行
        service.scheduleAtFixedRate(() -> {
            try {
                if (System.currentTimeMillis() - queue.take() > 1000L) {
                    process();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 100, 100, TimeUnit.MILLISECONDS);

        // 以恒定的速率放入令牌
        service.scheduleAtFixedRate(() -> {
            try {
                token.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * 计数器限流
     */
    public void count() {

        try {
            count.acquire();
            System.out.println("count");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 信号量限流
     */
    public void flag() {
        try {

            flag.acquire();
            System.out.println("flag");
            int i = new Random().nextInt(10);
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            flag.release();
        }
    }

    /**
     * 滑动窗口
     */
    public void window() {
        int sum = 0;
        for (int i = 0; i < window.length; i++) {
            sum += window[i].get();
        }
        if (sum > 10) {
            return;
        }
        System.out.println("window");
        int index = (int) (System.currentTimeMillis() / 1000 % 10);
        window[index].getAndAdd(1);
    }

    /**
     * 漏桶限流
     */
    public void bucket() {
        try {
            queue.put(System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void token() {
        try {
            token.take();
            System.out.println("token");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void process() {
        System.out.println("process");
    }
}

