package com.leo.dp.decorate;

public class WithMilk extends CoffeeDecorator {
    /**
     * 初始化咖啡对象的引用
     *
     * @param coffee
     * @return
     */
    public WithMilk(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", " + "milk";
    }

}
