package com.leo.dp.decorate;

public class WithSugar extends CoffeeDecorator {
    /**
     * 初始化咖啡对象的引用
     *
     * @param coffee
     * @return
     */
    public WithSugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 1;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", " + "sugar";
    }



}
