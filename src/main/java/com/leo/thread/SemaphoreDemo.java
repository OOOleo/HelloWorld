package com.leo.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s.acquire();   //获取信号量
                    System.out.println("T1 running...");
                    Thread.sleep(2000);
                    System.out.println("T1 running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s.release();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s.acquire();   //获取信号量
                    System.out.println("T2 running...");
                    Thread.sleep(2000);
                    System.out.println("T2 running...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s.release();
                }
            }
        }).start();
    }
}
