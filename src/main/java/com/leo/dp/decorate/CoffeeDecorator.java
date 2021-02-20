package com.leo.dp.decorate;

public class CoffeeDecorator implements Coffee {
    protected final Coffee decorateCoffee;
    /**
     * 初始化咖啡对象的引用
     *
     * @return
     */
    public CoffeeDecorator(Coffee coffee) {
        decorateCoffee = coffee;
    }

    @Override
    public double getCost() {
        return decorateCoffee.getCost();
    }

    @Override
    public String getIngredients() {
        return decorateCoffee.getIngredients();
    }
}
