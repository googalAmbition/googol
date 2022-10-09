package org.ctc.googol.jackson.version2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Stopwatch;
import org.ctc.googol.jackson.util.IoUtil;
import org.junit.Test;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class BaseElementTest {

    @Test
    public void test() {
        Reflections reflections = new Reflections("org.ctc.googol.jackson.version2");
        Stopwatch started = Stopwatch.createStarted();
        Set<Class<? extends BaseElement>> event = reflections.getSubTypesOf(BaseElement.class);

        System.out.println(started);
        event.forEach(System.out::println);
    }

    @Test
    public void version2Json1Test() {
        Reflections reflections = new Reflections("org.ctc.googol.jackson.version2");
        Set<Class<? extends BaseElement>> classSet = reflections.getSubTypesOf(BaseElement.class);
        ObjectMapper mapper = new ObjectMapper();
        classSet.forEach(mapper::registerSubtypes);
        String json = IoUtil.readFileAsString("json1.json");
        try {
            List<BaseElement> result = mapper.readValue(json,
                new TypeReference<List<BaseElement>>() {
                });
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void version2Json2Test() {
        Reflections reflections = new Reflections("org.ctc.googol.jackson.version2");
        Set<Class<? extends BaseElement>> classSet = reflections.getSubTypesOf(BaseElement.class);
        ObjectMapper mapper = new ObjectMapper();
        classSet.forEach(mapper::registerSubtypes);
        String json = IoUtil.readFileAsString("json2.json");
        try {
            StartEvent startEvent = (StartEvent) mapper.readValue(json, BaseElement.class);
            System.out.println(startEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}