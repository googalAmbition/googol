package com.tcodinig.demo.springboot.demo0;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class AnimalService {

    @Resource
    private CatImpl cat;

    @Resource
    private DogImpl dog;

    @Resource
    private PigImpl pig;

    public void toName(String code) {
        if ("pig".equals(code)) {
            pig.name();
        } else if ("dog".equals(code)) {
            dog.name();
        } else if ("cat".equals(code)) {
            cat.name();
        } else {
            //
        }
    }
}

