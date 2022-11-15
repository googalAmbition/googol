package com.tcodinig.demo.springboot.demo1;

import com.tcodinig.demo.springboot.IAnimal;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class AnimalService1 implements ApplicationContextAware {

    private static Map<String, IAnimal> animalMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(AnimalCode.class);
        beansWithAnnotation.forEach((k, v) -> animalMap.put(v.getClass().getAnnotation(AnimalCode.class).value(), (IAnimal) v));
    }

    public void toName(String code) {
        animalMap.get(code).name();
    }
}

