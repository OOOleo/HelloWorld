package com.leo.test;

import parsii.eval.Expression;
import parsii.eval.Function;

import java.util.List;

public class CustomFunction implements Function {
    @Override
    public int getNumberOfArguments() {
        return 1;
    }

    @Override
    public double eval(List<Expression> args) {
        double res = 0;
        for (Expression e : args) {
            res += e.evaluate();
        }
        return res;
    }

    @Override
    public boolean isNaturalFunction() {
        return false;
    }
}
