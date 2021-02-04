package com.leo.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlternatePrintDemo2 {
    Lock lock = new ReentrantLock();
    Condition letter = lock.newCondition();
    Condition digit = lock.newCondition();
    volatile int flag = 1;

    @Test
    public void test() {
        new Thread(() -> {

            for (int i = 1; i <= 26; i++) {
                lock.lock();
                try {
                    while (flag != 0) {
                        digit.await();
                    }
                    System.out.print(i);
                    flag = 1;
                    letter.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }).start();


        new Thread(() -> {

            for (int i = 1; i <= 26; i++) {
                lock.lock();
                try {
                    while (flag != 1) {
                        letter.await();
                    }
                    char ch = (char) ('A' + i - 1);
                    System.out.print(ch);
                    flag = 0;
                    digit.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();


    }


}
