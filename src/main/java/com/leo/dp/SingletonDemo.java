package com.leo.dp;

public class SingletonDemo {
    /**
     * 枚举单例
     */
    private SingletonDemo(){}

    //定义静态枚举类
    static enum SingletonEnum{
        INSTANCE;
        private SingletonDemo demo;

        private SingletonEnum() {
            demo = new SingletonDemo();
        }

        public SingletonDemo getInstance() {
            return demo;
        }
    }

    public static SingletonDemo getInstance() {
        return SingletonEnum.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        SingletonDemo demo1 = SingletonDemo.getInstance();
        SingletonDemo demo2 = SingletonDemo.getInstance();
        System.out.println(demo1==demo2);
    }
}
