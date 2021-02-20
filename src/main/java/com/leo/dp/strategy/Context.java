package com.leo.dp.strategy;

public class Context {
    /**
     * 上下文角色
     * 承上启下
     */
    Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contextInterface() {
        strategy.algorithmInterface();
    }


}
