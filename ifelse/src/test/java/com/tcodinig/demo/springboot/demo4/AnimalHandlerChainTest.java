package com.tcodinig.demo.springboot.demo4;

import com.tcodinig.demo.springboot.SpringbootApplicationTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
class AnimalHandlerChainTest extends SpringbootApplicationTests {

    @Resource
    private AnimalHandlerChain animalHandlerChain;

    @Test
    public void toName(){
        animalHandlerChain.toName("pig");
    }
}