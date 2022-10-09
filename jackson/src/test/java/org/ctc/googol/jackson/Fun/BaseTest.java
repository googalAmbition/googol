package org.ctc.googol.jackson.Fun;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import org.ctc.googol.jackson.util.IoUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    @Test
    public void gson() {
        RuntimeTypeAdapterFactory<Base> rta = RuntimeTypeAdapterFactory.of(
            Base.class);
        rta.registerSubtype(New.class);
        rta.registerSubtype(Old.class);
        Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(rta)
            .create();

        String s = IoUtil.readFileAsString("fun.json");
        JsonArray jsonArray = gson.fromJson(s, JsonArray.class);
        for (JsonElement jsonElement: jsonArray) {
            Base base = gson.fromJson(jsonElement.getAsJsonObject().toString(), Base.class);
            System.out.println(base);
        }
    }

    @Test
    public void version1Json1Test() {
        String jsonString = IoUtil.readFileAsString("fun.json");
        System.out.println(jsonString);

        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.registerSubtypes(StartEvent.class);
        // objectMapper.registerSubtypes(EndEvent.class);
        try {
            List<Base> baseElements = new ArrayList<>();
            baseElements = objectMapper.readValue(jsonString,
                new TypeReference<List<Base>>() {
                });
            for (Base baseElement: baseElements) {
                if (baseElement instanceof New) {
                    New n = (New) baseElement;
                    System.out.println(n + "===========");
                }
            }
            baseElements.forEach(x -> System.out.println(x.toString() + " " + x.getTargetType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}