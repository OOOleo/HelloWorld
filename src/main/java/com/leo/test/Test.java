package com.leo.test;

import java.sql.*;
import java.text.MessageFormat;
import java.util.logging.Logger;

public class Test {


        private static Logger log = Logger.getLogger(String.valueOf(Test.class));

        private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String DB_URL1 = "jdbc:mysql://192.168.10.89:3306/calcite";
        private static final String DB_URL2 = "jdbc:mysql://192.168.10.89:3306/calcite2";
        private static final String USER = "root";
        private static final String PSWD = "123456";

        public static void main(String[] args) {
            Connection conn = null;
            Connection conn2 = null;
            Statement stmt = null;

            try {
                Class.forName(JDBC_DRIVER).newInstance();
                conn = DriverManager.getConnection(DB_URL1, USER, PSWD);
                conn2 = DriverManager.getConnection(DB_URL2, USER, PSWD);

                stmt = conn2.createStatement();
                String sql = "select * from user_info ";
                ResultSet rs = stmt.executeQuery(sql);

                int index = 0;
                while (rs.next()) {
                    index++;

                    String id = rs.getString("user_id");
                    String name = rs.getString("level");
                    String age = rs.getString("education");
                    String cdate = rs.getString("age");

                    String raw = "#{0},{1},{2},{3},{4}";
                    Object[] arr = { index, id, name, age, cdate };
                    String outStr = MessageFormat.format(raw, arr);
                    log.info(outStr);
                }
            } catch (Exception e) {
                System.out.print("DB/SQL ERROR:" + e.getMessage());
            } finally {
                try {
                    stmt.close();
                    conn.close();
                    conn2.close();
                } catch (SQLException e) {
                    System.out.print("Can't close stmt/conn because of " + e.getMessage());
                }

            }
        }
    }

