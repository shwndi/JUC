package com.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 * @date 2021/5/25
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                //不处理，并抛出异常
                new ThreadPoolExecutor.AbortPolicy()
        );
        executor.execute(()->{
            System.out.println("a");
        });
        TimeUnit.SECONDS.sleep(1);
        int activeCount = executor.getActiveCount();
        System.out.println(activeCount);

        executor.execute(()->{
            System.out.println("a");
        });
        TimeUnit.SECONDS.sleep(1);
        activeCount = executor.getActiveCount();
        System.out.println(activeCount);

        executor.execute(()->{
            System.out.println("a");
        });
        TimeUnit.SECONDS.sleep(1);
        activeCount = executor.getActiveCount();
        System.out.println(activeCount);

        try {
            for (int i = 1; i < 100; i++) {
                TimeUnit.MICROSECONDS.sleep(1);
                executor.execute(()->{
                    System.out.println(Thread.currentThread().getName() + "ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
