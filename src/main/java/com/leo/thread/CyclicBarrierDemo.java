package com.leo.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("都到了");
            }
        });
        for (int i = 0; i < 3; i++) {
            Runnable runnable= () -> {
                try {
                    Thread.sleep((long) (Math.random()*10000));
                    System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点1，当前已有" + cb.getNumberWaiting() + "个已到达，正在等候");
                    cb.await();

                    Thread.sleep((long) (Math.random()*10000));
                    System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点2，当前已有" + cb.getNumberWaiting() + "个已到达，正在等候");
                    cb.await();

                    Thread.sleep((long) (Math.random()*10000));
                    System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合地点3，当前已有" + cb.getNumberWaiting() + "个已到达，正在等候");
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
