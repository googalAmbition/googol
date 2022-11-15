package com.tcodinig.demo.springboot.demo4;

import org.springframework.stereotype.Service;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class PigHandlerImpl extends AnimalHandler {

    @Override
    public void name(String code) {
        if (code().equals(code)) {
            System.out.println("Pig");
        } else {
            next.name(code);
        }
    }

    @Override
    public String code() {
        return "pig";
    }
}

