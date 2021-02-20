package com.leo.thread.concurrency1;



/*
    1.当调用wait时，首先确保调用wait的方法的线程已经持有该对象的锁
    2.当调用wait后，该线程会释放掉这个对象的锁，然后进入等待状态
    3.当线程调用wait后进入等待状态时，他就可以等待其他线程调用相同的notify或notifyAll方法使得自己被唤醒
    4.一旦这个线程被其他线程唤醒后，该线程就会与其他线程一同开始竞争这个对象的锁（公平竞争） 只有当该线程获取到了这个
      对象的锁后，线程才会继续往下执行
    5.调用wait方法的代码片段需要放在一个synchronized方法或块中，这样才可以确保线程在调用wait方法使已经获取到了对象的锁
    6.当调用对象的notify方法时，他会随机唤醒该对象等待集合（wait set）中任意一个线程
    7.当调用对象的notifyALL方法时，他会唤醒该对象等待集合的所有线程，这些线程被唤醒后，又会开始竞争对象的锁
    8.在某一时刻只有唯一一个线程可以拥有对象的锁
 */
public class MyTest1 {

    static int counter = 0;

    static class Operate{

    int flag = 1;
    public void plus() throws InterruptedException {
        synchronized (Operate.class) {
            if (flag != 1) {
                wait();
            }
            System.out.println(++counter+"   "+Thread.currentThread().getName());
            flag = 2;
            notify();
        }
    }

    public void minus() throws InterruptedException {
        synchronized (Operate.class) {
            if (flag != 2) {
                wait();
            }
            System.out.println(--counter+"   "+Thread.currentThread().getName());
            flag = 1;
            notify();
        }
    }

}


    public static void main(String[] args) {
        final Operate operate = new Operate();
        new Thread(() -> {
            try {
                while (true) {
                    operate.plus();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    operate.minus();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
