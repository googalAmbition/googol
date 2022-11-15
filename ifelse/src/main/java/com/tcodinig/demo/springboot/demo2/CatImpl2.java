package com.tcodinig.demo.springboot.demo2;

import org.springframework.stereotype.Service;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class CatImpl2 implements IAnimal {

    @Override
    public void name() {
        System.out.println("Cat");
    }

    @Override
    public boolean isMatch(String code) {
        return "cat".equals(code);
    }
}

