package com.tcodinig.demo.springboot.demo2;

import org.springframework.stereotype.Service;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service("dog")
public class DogImpl2 implements IAnimal {

    @Override
    public void name() {
        System.out.println("Dog");
    }

    @Override
    public boolean isMatch(String code) {
        return "dog".equals(code);
    }
}

