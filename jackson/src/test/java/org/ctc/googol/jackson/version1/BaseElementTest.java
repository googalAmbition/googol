package org.ctc.googol.jackson.version1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ctc.googol.jackson.util.IoUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseElementTest {

    @Test
    public void version1Json1Test() {
        String jsonString = IoUtil.readFileAsString("json1.json");
        System.out.println(jsonString);

        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.registerSubtypes(StartEvent.class);
        // objectMapper.registerSubtypes(EndEvent.class);
        try {
            List<BaseElement> baseElements = new ArrayList<>();
            baseElements = objectMapper.readValue(jsonString,
                new TypeReference<List<BaseElement>>() {
                });
            baseElements.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void version1Json2Test() {
        String json = IoUtil.readFileAsString("json2.json");
        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        try {
            StartEvent startEvent = (StartEvent) mapper.readValue(json, BaseElement.class);
            System.out.println(startEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
