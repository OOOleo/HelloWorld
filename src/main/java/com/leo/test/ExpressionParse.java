package com.leo.test;

import cn.hutool.core.collection.ListUtil;
import parsii.eval.Expression;
import parsii.eval.Parser;
import parsii.eval.Scope;
import parsii.eval.Variable;
import parsii.tokenizer.ParseException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

public class ExpressionParse {

    public static void main(String[] args) {
        String baseExpr = "10 * 2 + 6 / (3 - 1)";
        calByNashorn(baseExpr);
        calByParsII(baseExpr);
    }

    /**
     * Nashorn计算
     * @param expression
     */
    public static void calByNashorn(String expression) {
        long start = System.currentTimeMillis();
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
        int a = 18;
        String result = null;
        try {
            for (int i = 0; i < 10000; i++) {
                StringBuilder sb = new StringBuilder(expression);
                String calExp = sb.append("+").append(a).append("+").append(i).toString();
                for (int j = 0; j < 10; j++) {
                     result =String.valueOf(scriptEngine.eval(calExp));
                }
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Nashorn:"+ (end - start));
    }



    /**
     * ParsII计算
     * @param expression
     */
    public static void calByParsII(String expression) {
        long start = System.currentTimeMillis();
        Scope scope = Scope.create();
        Variable a = scope.getVariable("a");
        double result;
        try {

            for (int i = 0; i < 10000; i++) {
                StringBuilder sb = new StringBuilder(expression);
                String calExp = sb.append("+").append("a").append("+").append(i).toString();
                Expression expr = Parser.parse(calExp, scope);
                for (int j = 0; j < 10; j++) {
                    result = expr.evaluate();
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("ParsII:"+ (end - start));
    }
}
