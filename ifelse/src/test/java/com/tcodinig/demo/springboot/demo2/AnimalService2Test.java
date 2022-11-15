package com.tcodinig.demo.springboot.demo2;

import com.tcodinig.demo.springboot.SpringbootApplicationTests;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
public class AnimalService2Test extends SpringbootApplicationTests {

    @Resource
    private AnimalService2 animalService2;

    @Test
    public void toName(){
        animalService2.toName("pig");
    }

    @Test
    public void toName1(){
        animalService2.toName1("pig");
    }
}