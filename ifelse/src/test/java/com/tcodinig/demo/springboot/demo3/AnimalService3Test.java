package com.tcodinig.demo.springboot.demo3;

import com.tcodinig.demo.springboot.SpringbootApplicationTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
class AnimalService3Test extends SpringbootApplicationTests {

    @Resource
    private AnimalService3 animalService3;

    @Test
    public void toName(){
        animalService3.toName("cat");
    }
}