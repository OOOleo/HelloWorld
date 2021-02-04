package com.leo.test;

import java.util.HashMap;
import java.util.Map;

public class Test12 {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.clear();
        System.out.println(map.size());
        System.out.println(map);

    }

}
