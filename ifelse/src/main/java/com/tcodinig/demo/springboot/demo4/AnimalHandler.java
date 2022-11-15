package com.tcodinig.demo.springboot.demo4;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
public abstract class AnimalHandler {

    @Getter
    @Setter
    protected AnimalHandler next;

    public abstract void name(String code);

    public abstract String code();
}

