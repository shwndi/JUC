package com.T_ThreadPool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author czy
 * @date 2022/11/7
 */
public class T10_ScheduledPool {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.schedule(()->{
//        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
                System.out.println(Thread.currentThread().getName() +
                        "第" + atomicInteger.getAndIncrement() + "次");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },  500, TimeUnit.MILLISECONDS);
//        cycleTask(atomicInteger, service);
    }

    private static void cycleTask(AtomicInteger atomicInteger, ScheduledExecutorService service) {
        service.scheduleWithFixedDelay(()->{
//        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5000));
                System.out.println(Thread.currentThread().getName() +
                        "第" + atomicInteger.getAndIncrement() + "次");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
}
