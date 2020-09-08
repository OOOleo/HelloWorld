package com.leo.test;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.type.SqlTypeName;

/**
 * sql查询语句基本结构
 * select [select选项] 字段列表 [字段别名] /* from 数据源 [where条件子句] [group by子句] [having子句] [order by子句] [limit子句]
 */
public class Test6 {

    private SqlSelect sqlSelect;
    private static String fromSource;


    public static void main(String[] args) {
        SqlParser.Config config = SqlParser.configBuilder()
                .setLex(Lex.MYSQL)
                .build();

        SqlParser sqlParser = SqlParser.create("select avg(logout_time - login_time)  from log_model where eduction='本科' and level=1 group by dept_id",config);
        //SqlParser sqlParser = SqlParser.create("select * from log_model where eduction='本科' and level=1 group by dept_id",config);
        //SqlParser sqlParser = SqlParser.create("select * from log_model",config);

        SqlNode sqlNode = null;

        try {
            sqlNode = sqlParser.parseStmt();
        } catch (SqlParseException e) {
            e.printStackTrace();
        }
        //select [select选项] 字段列表 [字段别名] /* from 数据源 [where条件子句] [group by子句] [having子句] [order by子句] [limit子句]

        if (SqlKind.SELECT.equals(sqlNode.getKind())) {
            SqlSelect sqlSelect = (SqlSelect) sqlNode;
            SqlNode from = sqlSelect.getFrom();
            SqlNode where = sqlSelect.getWhere();
            SqlNodeList sqlNodes = sqlSelect.getSelectList();
            SqlNodeList sqlNodeList = sqlSelect.getGroup();   //获取group by后字段
            SqlNode having = sqlSelect.getHaving();
            SqlNodeList orderList = sqlSelect.getOrderList();

            /**
             * 获取from数据源   即表名
             */

            if (SqlKind.IDENTIFIER.equals(from.getKind())) {
                fromSource = from.getKind().toString();
            }

            /**
             *  select选项   1.正常项   2.*   3.函数
             *  字段可能来源于多表
             */

            for (SqlNode s : sqlNodes.getList()) {
                if (SqlKind.IDENTIFIER.equals(s.getKind())) {
                    System.out.println(s.toString());
                }
            }

            /**
             * where条件语句    涉及到比较   比较运算符
             * 多条件  涉及到    AND   OR
             * 同   having
             */

            SqlBasicCall sqlwhereBasicCall = (SqlBasicCall) where;
            int count = sqlwhereBasicCall.operandCount();
            SqlKind tempKind = where.getKind();
            System.out.println(where.toString());
            for (SqlNode sqlNode1 : sqlwhereBasicCall.operands) {

            }







        }

    }


    public static void processNode(SqlNode sqlNode) {

    }
}
