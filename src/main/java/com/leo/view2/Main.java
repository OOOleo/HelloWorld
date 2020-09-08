package com.leo.view2;

import com.leo.view.ViewFormation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class Main {


    //private static Logger log = Logger.getLogger(String.valueOf(Main.class));

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL1 = "jdbc:mysql://192.168.72.128:3306/calcite";
    private static final String DB_URL2 = "jdbc:mysql://192.168.72.128:3306/calcite2";
    private static final String USER = "root";
    private static final String PSWD = "123456";

    public static void main(String[] args) {
        ArrayList<String> tableNames=getColumnNames(DB_URL1,USER,PSWD,"users");
        ArrayList<String> tableNames2=getColumnNames(DB_URL2,USER,PSWD,"user_info");  //获取表列名
        Map<String, String> specification = SpecificationParse();  //规范绑定
        


    }

    public static ArrayList<String> getColumnNames(String url,String user,String passwd,String tableName){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<String> tableNames = null;
        try {
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(url, user, passwd);
            stmt = conn.createStatement();
            String sql = "select * from "+tableName;
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData metaRs = rs.getMetaData();
            int count = metaRs.getColumnCount();
            tableNames = new ArrayList<String>(count);
            for (int i = 0; i < count; i++) {
                tableNames.add(metaRs.getColumnName(i + 1));
            }

        } catch (Exception e) {
            System.out.print("DB/SQL ERROR:" + e.getMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.print("Can't close stmt/conn because of " + e.getMessage());
            }

        }
        return tableNames;
    }


    public static Map<String,String> SpecificationParse(){
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

        return map;
    }


}

