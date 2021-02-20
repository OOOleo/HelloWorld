package com.leo.dp.observer;

public class NBAObserver extends Observer {

    public NBAObserver(String name, Subject subject) {
        super(name, subject);
    }

    @Override
    public void update() {
        System.out.println(subject.getAction()+"\n"+name+"关闭NBA，继续工作");
    }
}
