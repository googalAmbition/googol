package com.tcodinig.demo.springboot.demo3;

import com.tcodinig.demo.springboot.IAnimal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
public class StrategyFactory {

    private static Map<String, IAnimal> map = new HashMap<>();

    public static void register(String code, IAnimal animal){
        map.put(code, animal);
    }

    public static IAnimal get(String code){
        return map.get(code);
    }
}

