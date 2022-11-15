package com.tcodinig.demo.springboot.demo1;

import com.tcodinig.demo.springboot.SpringbootApplicationTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
class AnimalService1Test extends SpringbootApplicationTests {

    @Resource
    private AnimalService1 animalService1;

    @Test
    public void toName(){
        animalService1.toName("cat");
    }

}