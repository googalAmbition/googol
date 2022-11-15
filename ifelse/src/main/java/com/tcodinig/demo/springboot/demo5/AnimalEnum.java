package com.tcodinig.demo.springboot.demo5;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@AllArgsConstructor
public enum AnimalEnum {

    /**
     *
     */
    DOG(1, "dog") {
        @Override
        public void names() {
            System.out.println("Dog");
        }
    }, PIG(2, "pig") {
        @Override
        public void names() {
            System.out.println("Pig");
        }
    }, CAT(3, "cat") {
        @Override
        public void names() {
            System.out.println("Cat");
        }
    };

    private int index;
    private String code;

    public abstract void names();

    public static void toName(String code) {
        Arrays.stream(AnimalEnum.values()).filter(x -> x.code.equals(code)).findFirst().orElseThrow().names();
    }
}

