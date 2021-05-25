package com.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 * @date 2021/5/22
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"执行");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"执行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //不释放别人不能执行
                 semaphore.release();
                }
            }).start();
        }
    }
}
