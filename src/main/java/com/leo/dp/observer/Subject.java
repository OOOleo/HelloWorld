package com.leo.dp.observer;

public interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();

    void setAction(String action);

    String getAction();
}
