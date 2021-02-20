package com.leo.dp.decorate;

import org.junit.jupiter.api.Test;

public class DecorateDemo {

    public void print(Coffee coffee) {
        System.out.println("cost:" + coffee.getCost());
        System.out.println("ingredients:" + coffee.getIngredients());
        System.out.println("===================");
    }

    @Test
    public void  test() {
        Coffee c = new SimpleCoffee();
        print(c);

        c = new WithSugar(c);
        print(c);

        c = new WithMilk(c);
        print(c);

    }
}
