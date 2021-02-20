package com.leo.dp.factory;

public class Main {
    public static void main(String[] args) {
        AbstractFactory miFactory = new XiaoMiFactory();
        AbstractFactory appleFactory = new AppleFactory();
        PC miPC = miFactory.makePC();
        miPC.make();
        Phone miPhone = miFactory.makePhone();
        miPhone.make();
        PC mac = appleFactory.makePC();
        mac.make();
        Phone iPhone = appleFactory.makePhone();
        iPhone.make();
    }
}
