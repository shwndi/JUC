package com.add;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 减法计数器
 * @author czy
 * @date 2021/5/22
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        AtomicInteger integer = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            new  Thread(()->{
                //数量减一
                System.out.println(Thread.currentThread().getName()+"->"+integer.getAndIncrement());
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //await等待计数器归零，向下执行
        countDownLatch.await();
        System.out.println("Game over!");
    }
}
