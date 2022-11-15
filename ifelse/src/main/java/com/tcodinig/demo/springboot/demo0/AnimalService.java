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
    private CatImpl catImpl;

    @Resource
    private DogImpl dogImpl;

    @Resource
    private PigImpl pigImpl;

    public void toName(String code) {
        if ("pig".equals(code)) {
            pigImpl.name();
        } else if ("dog".equals(code)) {
            dogImpl.name();
        } else if ("cat".equals(code)) {
            catImpl.name();
        } else {
            // default
        }
    }
}

