package com.leo.test;

import com.leo.view2.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Test1 {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        Properties props = new Properties();
        InputStream in = Main.class.getClassLoader().getResourceAsStream("specification.properties");
        try {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Object key : props.keySet()) {
            String str = props.getProperty(key.toString());
            String[] ss = str.split(",");
            for (int i = 0; i < ss.length; i++) {
                map.put(ss[i], key.toString());
            }
        }

        for (String s : map.keySet()) {
            System.out.println(s+" : "+map.get(s));
        }

    }
}
