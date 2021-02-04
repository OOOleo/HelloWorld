package com.leo.thread;

public class AlternatePrintDemo {



    public static void main(String[] args) {


        Object lock = new Object();

        new Thread(()->{
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(i);

                    lock.notify();
                }
            }

        }).start();


        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    char ch = (char) ('A' + i - 1);
                    System.out.print(ch);

                    lock.notify();

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }).start();


    }
}
