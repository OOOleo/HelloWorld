package com.leo.test;

import parsii.eval.*;
import parsii.tokenizer.ParseException;

public class Test10 {


    public static void main(String[] args) {
        Scope scope = Scope.create();
        configScope(scope);

        try {
            getParserConfig();
            Expression expr = Parser.parse("SIN(PI/2)", scope);
            System.out.println(expr.evaluate());

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void getParserConfig() {
        Parser.registerFunction("SIN", Functions.SIN);
        Parser.registerFunction("COS", Functions.COS);
        Parser.registerFunction("TAN", Functions.TAN);
        Parser.registerFunction("SINH", Functions.SINH);
        Parser.registerFunction("COSH", Functions.COSH);
        Parser.registerFunction("TANH", Functions.TANH);
        Parser.registerFunction("ASIN", Functions.ASIN);
        Parser.registerFunction("ACOS", Functions.ACOS);
        Parser.registerFunction("ATAN", Functions.ATAN);
        Parser.registerFunction("ATAN2", Functions.ATAN2);
        Parser.registerFunction("DEG", Functions.DEG);
        Parser.registerFunction("RAD", Functions.RAD);
        Parser.registerFunction("ABS", Functions.ABS);
        Parser.registerFunction("ROUND", Functions.ROUND);
        Parser.registerFunction("CEIL", Functions.CEIL);
        Parser.registerFunction("FLOOR", Functions.FLOOR);
        Parser.registerFunction("EXP", Functions.EXP);
        Parser.registerFunction("LN", Functions.LN);
        Parser.registerFunction("LOG", Functions.LOG);
        Parser.registerFunction("SQRT", Functions.SQRT);
        Parser.registerFunction("MIN", Functions.MIN);
        Parser.registerFunction("MAX", Functions.MAX);
        Parser.registerFunction("RND", Functions.RND);
        Parser.registerFunction("SIGN", Functions.SIGN);
    }


    //表达式常量设置  可扩展
    public static void configScope(Scope scope) {
        scope.getVariable("PI").makeConstant(Math.PI);
    }



}

