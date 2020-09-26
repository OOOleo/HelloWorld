package com.leo.thread.concurrency1;

public class MyTest1 {
    public static void main(String[] args) {
        Object obj = new Object();
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
