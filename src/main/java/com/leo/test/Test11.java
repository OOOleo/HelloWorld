package com.leo.test;


import java.util.HashMap;
import java.util.Map;

public class Test11 {
    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        map1.put("1", "1");
        map1.put("2", "2");
        map1.put("3", "3");

        map2.put("a", "a");
        map2.put("b", "b");
        map2.put("c", "c");

        map1.putAll(map2);


        System.out.println(map1);
    }
}
