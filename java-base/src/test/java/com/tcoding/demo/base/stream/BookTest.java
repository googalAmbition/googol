package com.tcoding.demo.base.stream;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author 陈天成
 * @date 2022/10/19.
 */
public class BookTest {

    private List<Book> books;
    private Gson gson;

    @Before
    public void before() {
        gson = new Gson();
        InputStream inputStream = this.getClass().getResourceAsStream("/book.json");
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            String json = buffer.lines().collect(Collectors.joining());
            Gson gson = new Gson();
            TypeToken<List<Book>> typeToken = new TypeToken<>() { };
            books = gson.fromJson(json, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void myCollectorTest() {
        Map<Integer, List<Book.Group>> collect = books.stream().collect(new Book.MyCollector());
        System.out.println(gson.toJson(collect));
    }

    @Test
    public void groupingBy(){
        // 每个category对应的book详情
        Map<Integer, List<Book>> category2Books = books.stream().collect(Collectors.groupingBy(Book::getCategory));
        System.out.println(category2Books);
        // 每个category book数量
        Map<Integer, Long> collect = books.stream().collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
        System.out.println(collect);
        // 每个category book价格总和
        Map<Integer, Double> collect1 =
            books.stream().collect(Collectors.groupingBy(Book::getCategory, Collectors.summingDouble(Book::getPrice)));
        System.out.println(collect1);
        // 按category排序，最贵book
        TreeMap<Integer, Optional<Book>> collect2 = books.stream().collect(Collectors.groupingBy(Book::getCategory, TreeMap::new,
            Collectors.maxBy(Comparator.comparingDouble(Book::getPrice))));
        System.out.println(collect2);
    }
}