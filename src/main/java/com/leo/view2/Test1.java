package com.leo.view2;

import com.sun.org.apache.xpath.internal.jaxp.JAXPVariableStack;
import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.plan.hep.HepPlanner;
import org.apache.calcite.plan.hep.HepProgram;
import org.apache.calcite.plan.hep.HepProgramBuilder;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.rel.rules.SubQueryRemoveRule;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.Properties;

public class Test1 {


    public static void main(String[] args) {
        SqlParser.ConfigBuilder configBuilder = SqlParser.configBuilder();
        configBuilder.setUnquotedCasing(Casing.UNCHANGED);

        String sql = "select * from student";
        SqlParser sqlParser = SqlParser.create(sql, configBuilder.build());
        try {
            SqlNode sqlNode=sqlParser.parseQuery();
            SqlParser.Config config = configBuilder.build();
            Planner planner = Frameworks.getPlanner((FrameworkConfig) config);
            SqlNode node=planner.validate(sqlNode);
            RelRoot relRoot = planner.rel(node);

            RelNode project = relRoot.project();
            final HepProgram program = new HepProgramBuilder().addRuleInstance(SubQueryRemoveRule.PROJECT).addRuleInstance(SubQueryRemoveRule.FILTER).addRuleInstance(SubQueryRemoveRule.JOIN).build();

            HepPlanner prePlanner = new HepPlanner(program);

            prePlanner.setRoot(project);
            RelNode relNode = prePlanner.findBestExp();

        } catch (SqlParseException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (RelConversionException e) {
            e.printStackTrace();
        }


        try {
            Class.forName("org.apache.calcite.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Properties info = new Properties();
        info.setProperty("lex", "JAVA");
        try {
            Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
            CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
            SchemaPlus rootSchema = calciteConnection.getRootSchema();

            Class.forName("com.mysql.jdbc.Driver");
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://192.168.72.128:3306/school");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            Schema schema = JdbcSchema.create(rootSchema, "school", dataSource, null, null);
            rootSchema.add("school", schema);

            Statement statement = calciteConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student");
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
