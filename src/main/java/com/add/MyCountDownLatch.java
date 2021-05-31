package com.add;

import jdk.management.resource.internal.inst.ThreadRMHooks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 减法计数器：
 * 1、new CountDownLatch（100）初始化
 * 2、o.countDown()做减法每次减一
 * 3、o.await()等待归0，主线程才能继续跑，否则等待着。
 * @author czy
 * @date 2021/5/28
 */
public class MyCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(100);
        AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < 110; i++) {
            new Thread(()->{
                integer.getAndIncrement();
                System.out.println(Thread.currentThread().getName()+"==>"+integer);
                count.countDown();
            }).start();
        }
        count.await();
        System.out.println("Game Over!");
        System.out.println(integer);
    }
}
