package com.tcodinig.demo.springboot.demo0;

import com.tcodinig.demo.springboot.SpringbootApplicationTests;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
class AnimalServiceTest extends SpringbootApplicationTests {

    @Resource
    private AnimalService animalService;

    @Test
    public void name(){
        animalService.toName("dog");
    }
}