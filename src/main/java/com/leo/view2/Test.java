package com.leo.view2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Test {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String url = "jdbc:mysql://192.168.72.128:3306/school";
        //String[] types = { "TABLE" };// 数组变量types
        String tt, tp;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, "root", "123456");
        System.out.println("ok!连接成功!!!!!");
        DatabaseMetaData dmd = con.getMetaData();
        // 获取表的相关信息(包括用户建立的表和系统表)
        ResultSet rs = dmd.getTables("school", null, null, new String[]{"TABLE"});
        /*
         *  // 获取表,视图相关信息(包括用户建立的表和系统表,以及所有视图)
         *
         * ResultSet rs=dmd.getTables(null,null,null,null);
         *
         */

        while (rs.next()) {
            tt = rs.getString("TABLE_NAME");
            tp = rs.getString("TABLE_TYPE");
            System.out.println(" 表的名称 " + tt + "   表的类型 " + tp);
        }
        // 关闭连接
        con.close();

    }

}