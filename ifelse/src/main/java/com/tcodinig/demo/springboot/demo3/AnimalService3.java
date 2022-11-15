package com.tcodinig.demo.springboot.demo3;

import org.springframework.stereotype.Service;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class AnimalService3 {

    public void toName(String code) {
        StrategyFactory.get(code).name();
    }
}

