package com.tcodinig.demo.springboot.demo3;

import com.tcodinig.demo.springboot.IAnimal;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class CatImpl3 implements IAnimal {

    @PostConstruct
    public void init() {
        StrategyFactory.register("cat", this);
    }

    @Override
    public void name() {
        System.out.println("Cat");
    }
}

