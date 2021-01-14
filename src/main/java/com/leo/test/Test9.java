package com.leo.test;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test9 {
    public static void main1(String[] args) {
        String prop1 = "age";
        String prop2 = "age2";
        StringBuilder sb = new StringBuilder("PROP(").append(prop1).append(")").append("+").append("PROP(").append(prop2).append(")");
        String ss = sb.toString();
        Pattern p = Pattern.compile("PROP\\(\"?(.+?)\"?\\)");
        Matcher m = p.matcher(ss);
        while (m.find()) {
            System.out.println(m.group(1));
        }
    }

    public static void main(String[] args) {
        List<String> propList = new ArrayList<>();
        List<Integer> typeList = new ArrayList<>();
        StringBuilder s = new StringBuilder("|1,2|");
        String prop1 = "age";
        String prop2 = "age2";
        StringBuilder sb = new StringBuilder("PROP(").append(prop1).append(")").append("+").append("PROP(").append(prop2).append(")");
        String aa = sb.toString();
        Pattern propPattern = Pattern.compile("PROP\\(\"?(.+?)\"?\\)");
        Matcher propMatcher = propPattern.matcher(aa);
        while (propMatcher.find()) {
            propList.add(propMatcher.group(0));
        }
        propMatcher.replaceAll(propMatcher.group(1));
        System.out.println(propList);
        System.out.println(aa);
    }

}
