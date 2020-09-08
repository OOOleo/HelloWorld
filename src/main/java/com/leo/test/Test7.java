package com.leo.test;

import com.alibaba.druid.support.json.JSONUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test7 {
    public static void main(String[] args) {
        showMap2(inputMap());
//        showMap1(inputMap());
//        showMap3(inputMap());
//        showMap4(inputMap());
    }

    public static void showMap1(Map<String, Integer> userMap) {
        for (String key : userMap.keySet()) {
            System.out.println(key + "***" + userMap.get(key));
        }
        System.out.println("-------------------------------------------");
    }

    public static void showMap2(Map<String, Integer> userMap) {
        Long start = System.currentTimeMillis();
        for (Integer i : userMap.values()) {
            //System.out.println(i);
        }
        System.out.println("-------------------------------------------");
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void showMap3(Map<String, Integer> userMap) {
        for (Map.Entry entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + "***" + entry.getValue());
        }
        System.out.println("---------------------------------------------");
    }

    public static void showMap4(Map<String, Integer> userMap) {
        Iterator<Map.Entry<String,Integer>> it = userMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            System.out.println(entry.getKey() + "***" + entry.getValue());
        }
    }



    public static Map inputMap() {
        Map<String, Integer> userMap = new HashMap<>();
        String str[] = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String key;
        Integer value;
        for (int i = 0; i < 100000; i++) {
            int m = (int)Math.random() * 10;
            key = String.valueOf(str[m] + i * 100);
            value = i;
            userMap.put(key, value);
        }
        return userMap;
    }
}
