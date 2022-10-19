package com.tcoding.demo.base.concurrent;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author 陈天成
 * @date 2022/10/10.
 */
public class AssertDemo {

    /**
     * vm option -ea
     */
    public static void main(String[] args) {
        AssertDemo assertDemo = new AssertDemo();
        IntStream.range(0, 10).forEach(x -> {
            int random = assertDemo.random();
            assert random > 5 : "random must > 5";
            System.out.println("success");
        });
    }

    public int random() {
        Random random = new Random();
        int i = random.nextInt(10);
        System.out.println(i);
        return i;
    }
}

