package com.tcodinig.demo.springboot.demo2;

import org.springframework.stereotype.Service;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class PigImpl2 implements IAnimal {

    @Override
    public void name() {
        System.out.println("Pig");
    }

    @Override
    public boolean isMatch(String code) {
        return "pig".equals(code);
    }
}

