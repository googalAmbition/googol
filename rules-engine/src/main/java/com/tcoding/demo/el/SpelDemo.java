package com.tcoding.demo.el;

import com.tcoding.demo.Person;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

/**
 * @author 陈天成
 * @date 2022/10/8.
 */
public class SpelDemo {

    public static void main(String[] args) {
        // 构建express
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        Expression expression = parser.parseExpression("#{['a'].getName()}", ParserContext.TEMPLATE_EXPRESSION);

        // 设置参数
        context.setRootObject(Map.of("a", new Person("demo", 18)));
        // context.setVariables(Map.of("a", new Person("demo", 18)));

        // 计算
        System.out.println(expression.getValue(context));
    }
}

