package com.leo.view;

import com.leo.test.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

public class ViewFormation {

    private static Logger log = Logger.getLogger(String.valueOf(Test.class));

    public static void main(String[] args) {
        Database db1 = getDatabase("jdbc.properties", 1);
        Database db2 = getDatabase("jdbc.properties", 2);

        System.out.println(db1);
        System.out.println(db2);
        Connection conn1 = null;
        Connection conn2 = null;
        Statement stmt = null;
        try {
            conn1 = DatabaseConnect.getConnection(db1.getDriver(), db1.getUrl(), db1.getUser(), db1.getPasswd());
            conn2 = DatabaseConnect.getConnection(db2.getDriver(), db2.getUrl(), db2.getUser(), db2.getPasswd());

            stmt = conn1.createStatement();
            String sql = "select * from users";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs.getString(0)+" "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            stmt.close();
            conn1.close();
            conn2.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    private static Database getDatabase(String filePath,int index) {
        Database db = new Database();
        Properties prop = new Properties();
        InputStream in = ViewFormation.class.getClassLoader().getResourceAsStream(filePath);
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        db.setDriver(prop.getProperty("db"+index+".dirver"));
        db.setUrl(prop.getProperty("db"+index+".url"));
        db.setUser(prop.getProperty("db"+index+".username"));
        db.setPasswd(prop.getProperty("db"+index+".passwd"));
        return db;
    }

}
