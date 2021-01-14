package com.leo.test;

import java.util.ArrayList;
import java.util.List;

public class Test12 {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list2.add(2);
        list2.add(1);
        System.out.println(list1.equals(list2));
    }

}
