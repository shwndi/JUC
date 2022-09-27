package com.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 * @date 2022/9/26
 */
public class T_semaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 100; i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("线程打印：" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }, "Thread " + j).start();
        }
        while (true) {
            sleep(1000);
            int i = semaphore.availablePermits();
            if (0==i) {
                semaphore.release(5);
                System.out.println();
            }
        }
    }



    private static void sleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
