package com.leo.test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionCalculate {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("nashorn");
        int x = 18;
        try {
            for (int i = 0; i < 1; i++) {
                String result = String.valueOf(scriptEngine.eval(String.valueOf(10 * 2 + 6 / (3 - 1)+x)));
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}