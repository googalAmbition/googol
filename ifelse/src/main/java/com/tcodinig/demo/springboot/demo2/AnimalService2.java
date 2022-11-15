package com.tcodinig.demo.springboot.demo2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class AnimalService2 implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private List<IAnimal> animals = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, IAnimal> beansOfType = applicationContext.getBeansOfType(IAnimal.class);
        beansOfType.forEach((k, v) -> animals.add(v));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void toName(String code){
        for (IAnimal animal: animals) {
            if (animal.isMatch(code)){
                animal.name();
            }
        }
    }
}


