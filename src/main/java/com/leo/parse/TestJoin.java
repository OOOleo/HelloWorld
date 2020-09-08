package com.leo.parse;


import org.apache.calcite.sql.SqlJoin;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlSelect;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

import java.util.ArrayList;
import java.util.List;

public class TestJoin {
    public static void main(String[] args) throws SqlParseException {
        final List<String> tables = new ArrayList<>();
        String sql = "select a,b,c from tableA join tableB where tableA.a=tableB.a";
        SqlParser sqlParser = SqlParser.create(sql);
        SqlNode sqlNode = sqlParser.parseQuery();
        SqlSelect sqlSelect = (SqlSelect) sqlNode;
        SqlJoin sqlJoin = (SqlJoin) sqlSelect.getFrom();
        System.out.println(sqlJoin.getLeft());
        System.out.println(sqlJoin.getRight());

    }
}
