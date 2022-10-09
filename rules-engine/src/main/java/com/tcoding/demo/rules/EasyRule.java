package com.tcoding.demo.rules;

import com.tcoding.demo.IoUtil;
import com.tcoding.demo.Person;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.JsonRuleDefinitionReader;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author 陈天成
 * @date 2022/10/8.
 */
public class EasyRule {

    public static void main(String[] args) throws Exception {
        // given
        String s = IoUtil.readFile("adult-rule.json");

        MVELRuleFactory factory = new MVELRuleFactory(new JsonRuleDefinitionReader());
        Facts facts = new Facts();
        Person demo = new Person("demo", 19);
        // Person demo = new Person("demo", 18);
        facts.put("person", demo);
        Rules rule = factory.createRules(new InputStreamReader(new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8))));
        DefaultRulesEngine engine = new DefaultRulesEngine();
        engine.fire(rule, facts);
        System.out.println(demo.isAdult());
    }
}

