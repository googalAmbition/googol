package com.tcodinig.demo.springboot.demo1;

import com.tcodinig.demo.springboot.IAnimal;
import org.springframework.stereotype.Service;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
@AnimalCode(value = "dog", name = "狗")
public class DogImpl1 implements IAnimal {

    @Override
    public void name() {
        System.out.println("Dog");
    }
}

