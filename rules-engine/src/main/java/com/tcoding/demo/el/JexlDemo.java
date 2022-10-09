package com.tcoding.demo.el;

import com.tcoding.demo.Person;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlScript;
import org.apache.commons.jexl3.MapContext;

import java.util.Map;

/**
 * @author 陈天成
 * @date 2022/10/8.
 */
public class JexlDemo {

    public static void main(String[] args) {
        JexlEngine engine = new JexlBuilder().create();
        JexlScript script = engine.createScript("a.getName()");

        MapContext context = new MapContext(Map.of("a", new Person("demo", 18)));
        System.out.println(script.execute(context));
    }
}

