package com.leo.view2;

import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.*;

import java.sql.*;
import java.util.Properties;

public class QueryMysql {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
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
                DriverManager.getConnection("jdbc:calcite:", info);
        // must print "directory ... not found" to stdout, but not fail
        Statement statement = connection.createStatement();
        CalciteConnection calciteConnection =
                connection.unwrap(CalciteConnection.class);

        SchemaPlus schemaPlus=calciteConnection.getRootSchema();



        final FrameworkConfig config = Frameworks.newConfigBuilder()
                .parserConfig(SqlParser.Config.DEFAULT)
                .defaultSchema(schemaPlus)
                .build();
        Planner planner = Frameworks.getPlanner(config);
        String sql="select * from \"school\".\"student\"";

        try {
            SqlNode parse = planner.parse(sql);
            SqlNode validate = planner.validate(parse);

            RelRoot relRoot = planner.rel(validate);
            System.out.println(RelOptUtil.toString(relRoot.rel));
        } catch (SqlParseException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (RelConversionException e) {
            e.printStackTrace();
        }



        ResultSet resultSet =
                statement.executeQuery(sql);

        ResultSet tables =
                connection.getMetaData().getTables(null, null, null, null);

        final StringBuilder buf = new StringBuilder();
        while (resultSet.next()) {
            int n = resultSet.getMetaData().getColumnCount();
            for (int i = 1; i <= n; i++) {
                buf.append(i > 1 ? "; " : "")
                        .append(resultSet.getMetaData().getColumnLabel(i))
                        .append("=")
                        .append(resultSet.getObject(i));
            }
            System.out.println(buf.toString());
            buf.setLength(0);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
