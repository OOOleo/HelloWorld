package com.leo.test;

import org.apache.calcite.sql.*;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;

public class Test4 {
    public static void main(String[] args) {
        SqlParser sqlParser = SqlParser.create("select * from \"table\" where \"column\" > 1 limit 1");
        SqlNode sqlNode = null;
        try {
            sqlNode = sqlParser.parseQuery();
        } catch (SqlParseException e) {
            e.printStackTrace();
        }

        if(sqlNode instanceof SqlCall){
            if(sqlNode instanceof SqlBasicCall){
                SqlBasicCall basicCall = (SqlBasicCall) sqlNode;
                System.out.println(((SqlIdentifier)basicCall.operand( 0)).getSimple());
                System.out.println(((SqlNumericLiteral)basicCall.operand(1)).getValue());
                System.out.println(basicCall.getKind());
            }
            System.out.println(sqlNode.getKind()+" -> "+sqlNode.getClass());
//            SqlCall call = (SqlCall) sqlNode;
//            for(SqlNode node: call.getOperandList()){
//                parse(node);
//            }
        }
    }
}
