package com.tcoding.demo.base.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author 陈天成
 * @date 2022/10/18.
 */
public class AssertDemoTest {

    @Test
    public void assertDemo() {
        IntStream.range(0, 10).forEach(x -> {
            int random = new Random().nextInt(10);
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