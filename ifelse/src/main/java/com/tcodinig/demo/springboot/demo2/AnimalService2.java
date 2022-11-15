package com.tcodinig.demo.springboot.demo2;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * @author 陈天成
 * @date 2022/11/15.
 */
@Service
public class AnimalService2 {

    @Resource
    private List<IAnimal> animals;

    @Resource
    private Map<String, IAnimal> animalMap;

    public void toName(String code) {
        for (IAnimal animal: animals) {
            if (animal.isMatch(code)) {
                animal.name();
            }
        }
    }

    public void toName1(String code){
        animalMap.get(code).name();
    }


}


