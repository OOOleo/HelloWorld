package com.leo.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    private String jdbcdirver;
    private String url;
    private String user;
    private String password;

    DatabaseConnect(String jdbcdirver,String url,String user,String password){
        this.jdbcdirver = jdbcdirver;
        this.url = url;
        this.user = user;
        this.password = password;
    }


    public static Connection getConnection(String jdbcdirver, String url, String user, String password) throws IllegalAccessException, InstantiationException {
        Connection conn = null;
        try {
            Class.forName(jdbcdirver).newInstance();
            conn= DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return conn;
    }
}
