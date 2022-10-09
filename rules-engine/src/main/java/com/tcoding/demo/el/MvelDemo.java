package com.tcoding.demo.el;

import com.tcoding.demo.Person;
import org.mvel2.MVEL;

import java.util.Map;

/**
 * @author 陈天成
 * @date 2022/10/8.
 */
public class MvelDemo {

    public static void main(String[] args) {
        System.out.println(MVEL.eval("a.getName()", Map.of("a", new Person("demo", 18))));
    }
}

