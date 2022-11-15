package com.tcodinig.demo.springboot.demo0;

import com.tcodinig.demo.springboot.IAnimal;
import org.springframework.stereotype.Service;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class PigImpl implements IAnimal {

    @Override
    public void name() {
        System.out.println("Pig");
    }
}

