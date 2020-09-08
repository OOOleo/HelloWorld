package com.leo.test;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

/**
 * sql查询语句基本结构
 * select [select选项] 字段列表 [字段别名] /* from 数据源 [where条件子句] [group by子句] [having子句] [order by子句] [limit子句]
 */
public class Test3 {

    private SqlSelect sqlSelect;

    public static void main(String[] args) {
        SqlParser.Config config = SqlParser.configBuilder()
                .setLex(Lex.MYSQL)
                .build();

        // sqlParser = SqlParser.create("select avg(logout_time - login_time)  from log_model where eduction='本科' and level=1 group by dept_id",config);
        SqlParser sqlParser = SqlParser.create("select * from log_model where eduction='本科' and level=1 group by dept_id",config);

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
            //System.out.println(sqlNodes);
            SqlNodeList sqlNodeList = sqlSelect.getGroup();   //获取group by后字段
            SqlNode having = sqlSelect.getHaving();
            SqlNodeList orderList = sqlSelect.getOrderList();

            //System.out.println(sqlNodeList);

            System.out.println(sqlNodes.size());


            //标识符
            if (SqlKind.IDENTIFIER.equals(from.getKind())) {
                System.out.println(from.toString());
            }


            if (SqlKind.AND.equals(where.getKind())) {
                SqlBasicCall sqlBasicCall= (SqlBasicCall) where;
                for (SqlNode sqlNode1 : sqlBasicCall.operands) {
                    if (SqlKind.EQUALS.equals(sqlNode1.getKind())) {
                        System.out.println(sqlNode1.toString());
                    }
                }
            }





            for (SqlNode x : sqlNodes.getList()) {
                System.out.println("AVG:  "+SqlKind.AVG.equals(x.getKind()));
                if (SqlKind.OTHER_FUNCTION.equals(x.getKind())) {
                    SqlBasicCall sqlBasicCall= (SqlBasicCall) x;
                    for (SqlNode sqlNode1 : sqlBasicCall.operands) {
                        if (SqlKind.MINUS.equals(sqlNode1.getKind())) {
                            SqlBasicCall sqlBasicCall1 = (SqlBasicCall) sqlNode1;
                            for (SqlNode ss : sqlBasicCall1.operands) {
                                if (SqlKind.IDENTIFIER.equals(ss.getKind())) {
                                    System.out.println(ss.toString());
                                }
                            }
                        }
                    }
                }
            }

            for (SqlNode s : sqlNodeList.getList()) {
                System.out.println(s.getKind());
                if (SqlKind.IDENTIFIER.equals(s.getKind())) {
                    System.out.println(s.toString());
                }
            }

        }

    }
}
