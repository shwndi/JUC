package com.T_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author czy
 * @date 2022/11/3
 */
public class T05_00_ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {

            int finalI = i;
            threadPool.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+ " = " +finalI);
            });
        }
        System.out.println(threadPool);

        threadPool.shutdown();
        System.out.println(threadPool.isTerminated());
        System.out.println(threadPool.isShutdown());
        System.out.println(threadPool);

        Thread.sleep(5000);
        System.out.println(threadPool.isTerminated());
        System.out.println(threadPool.isShutdown());
        System.out.println(threadPool);
    }
}
