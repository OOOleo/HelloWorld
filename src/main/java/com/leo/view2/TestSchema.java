package com.leo.view2;

import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.Table;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TestSchema {
    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Properties info = new Properties();
        info.put("model",
                "inline:"
                        + "{\n"
                        + "  version: '1.0',\n"
                        + "  defaultSchema: 'school',\n"
                        + "  schemas: [\n"
                        + "    {\n"
                        + "      name: 'school',\n"
                        + "      type: 'custom',\n"
                        + "      factory: 'org.apache.calcite.adapter.jdbc.JdbcSchema$Factory',\n"
                        + "      operand: {\n"
                        + "        jdbcDriver: 'com.mysql.cj.jdbc.Driver',\n"
                        + "        jdbcUrl:'jdbc:mysql://192.168.72.128:3306/school',\n"
                        + "        jdbcUser: 'root',\n"
                        + "        jdbcPassword: '123456'\n"
                        + "      }\n"
                        + "    }\n"
                        + "  ]\n"
                        + "}");

        Connection connection =
                null;
        try {
            connection = DriverManager.getConnection("jdbc:calcite:", info);
            Statement statement = connection.createStatement();
            CalciteConnection calciteConnection =
                    connection.unwrap(CalciteConnection.class);

            SchemaPlus rootSchema=calciteConnection.getRootSchema();

            Class.forName("com.mysql.jdbc.Driver");
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://192.168.72.128:3306/school");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            Schema schema = JdbcSchema.create(rootSchema, "school", dataSource, null, null);
            rootSchema.add("school", schema);




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


}
