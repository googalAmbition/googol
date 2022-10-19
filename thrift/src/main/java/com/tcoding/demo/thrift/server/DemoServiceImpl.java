package com.tcoding.demo.thrift.server;

import com.tcoding.demo.thrift.Book;
import com.tcoding.demo.thrift.DemoService;
import org.apache.thrift.TException;

import java.util.List;

/**
 * @author 陈天成
 * @date 2022/10/10.
 */
public class DemoServiceImpl implements DemoService.Iface {

    @Override
    public String getName(long id) throws TException {
        return "hello " + id;
    }

    @Override
    public List<Book> listBook() throws TException {
        Book b = new Book();
        b.setId(1L);
        b.setName("hello");
        return List.of(b);
    }
}

