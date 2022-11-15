package com.tcoding.demo.base.stream;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import lombok.Data;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 陈天成
 * @date 2022/10/18.
 * <p>
 * 自定义collector
 */
@Data
public class Book {

    /**
     * 书名
     */
    private String name;
    /**
     * 品类
     */
    private Integer category;

    /**
     * 级别：入门，初级，。。。
     */
    private Integer level;
    /**
     * 作者
     */
    private String author;
    /**
     * 价格
     */
    private Double price;

    @Data
    public static class Group {

        private Integer level;

        private List<BookInfo> books;

        @Data
        public static class BookInfo {

            private String name;
            private Double price;
            private String author;
        }
    }

    public static class MyCollector implements Collector<Book, Table<Integer, Integer, List<Group.BookInfo>>, Map<Integer, List<Group>>> {

        /**
         * 初始化中间容器
         */
        @Override
        public Supplier<Table<Integer, Integer, List<Group.BookInfo>>> supplier() {
            System.out.println("supplier");
            return HashBasedTable::create;
        }

        /**
         * 输入放入到中间容器
         */
        @Override
        public BiConsumer<Table<Integer, Integer, List<Group.BookInfo>>, Book> accumulator() {
            System.out.println("accumulator");
            return (table, book) -> {
                System.out.println("accumulator-->BiConsumer");
                List<Group.BookInfo> books = table.get(book.getCategory(), book.getLevel());
                if (books == null) {
                    books = new ArrayList<>();
                }
                Group.BookInfo info = new Group.BookInfo();
                info.setAuthor(book.getAuthor());
                info.setName(book.getName());
                info.setPrice(book.getPrice());
                books.add(info);
                table.put(book.getCategory(), book.getLevel(), books);
            };
        }

        /**
         * 重复项合并
         */
        @Override
        public BinaryOperator<Table<Integer, Integer, List<Group.BookInfo>>> combiner() {
            System.out.println("combiner");
            return (a, b) -> {
                System.out.println("combiner-->BinaryOperator");
                return Stream.concat(a.cellSet().stream(), b.cellSet().stream())
                    .collect(Tables.toTable(Table.Cell::getRowKey,
                        Table.Cell::getColumnKey,
                        Table.Cell::getValue,
                        (x, y) -> {
                            x.addAll(y);
                            return x;
                        }, HashBasedTable::create));
            };
        }

        /**
         * 容器输出
         */
        @Override
        public Function<Table<Integer, Integer, List<Group.BookInfo>>, Map<Integer, List<Group>>> finisher() {
            System.out.println("finisher");
            return (table) -> {
                System.out.println("finisher-->Function");
                Map<Integer, List<Group>> result = new HashMap<>();
                table.columnMap().forEach((key, value) -> {
                    List<Group> groups = value.entrySet().stream()
                        .map(t -> {
                            Group group = new Group();
                            group.setBooks(t.getValue());
                            group.setLevel(t.getKey());
                            return group;
                        }).collect(Collectors.toList());
                    result.put(key, groups);
                });
                return result;
            };
        }

        @Override
        public Set<Characteristics> characteristics() {
            System.out.println("characteristics");
            return EnumSet.of(Characteristics.CONCURRENT);
        }
    }
}

