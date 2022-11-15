package com.tcodinig.demo.springboot.demo4;

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
public class AnimalHandlerChain implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private AnimalHandler header;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, AnimalHandler> beansOfType = applicationContext.getBeansOfType(AnimalHandler.class);
        List<AnimalHandler> handlers = new ArrayList<>(beansOfType.values());
        for (int i = 0; i < handlers.size(); i++) {
            AnimalHandler animalHandler = handlers.get(i);
            if (i != handlers.size() - 1) {
                animalHandler.setNext(handlers.get(i + 1));
            }
        }
        header = handlers.get(0);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void toName(String code) {
        header.name(code);
    }
}

