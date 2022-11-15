package com.tcodinig.demo.springboot.demo5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
class AnimalEnumTest {

    @Test
    public void toName(){
        AnimalEnum.toName("pig");
    }
}