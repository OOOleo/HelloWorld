package com.leo.dp.observer;

public class Client {
    public static void main(String[] args) {
        Secretary secretary = new Secretary();
        StockObserver observer = new StockObserver("adam", secretary);
        NBAObserver observer1 = new NBAObserver("tom", secretary);

        secretary.attach(observer);
        secretary.attach(observer1);

        secretary.setAction("BOSS 回来了！");
        secretary.notifyObservers();
    }
}
