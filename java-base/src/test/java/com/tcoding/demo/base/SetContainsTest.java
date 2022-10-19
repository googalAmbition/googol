package com.tcoding.demo.base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetContainsTest {

    private Set<String> setA = new HashSet<>();

    private Set<String> setB = new HashSet<>();

    @Before
    public void initSet() {
        setA.add("A");
        setA.add("B");
        setA.add("C");
        setA.add("D");
        setA.add("E");

        setB.add("A");
        setB.add("B");
        setB.add("C");
        // setB.add("F");
    }

    @Test
    public void containAllTest() {
        Assert.assertTrue(
            setA.containsAll(setB));
    }

    @Test
    public void boolTest(){
        System.out.println(Boolean.valueOf(null));
    }
}