package com.add;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 没有谁快谁慢，都要看数量
 *
 * @author czy
 * @date 2022/9/26
 */
public class T_longAddr {
    static long count1 = 0L;
    static AtomicLong count2 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    count2.incrementAndGet();
                }
            });
        }
        Long startime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startime);
        System.out.print("           " + count2.get());
        System.out.println("-------------------------------------------------------------");
        Object o = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    synchronized (o) {
                        count1++;
                    }
                }
            });
        }
        startime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        endTime = System.currentTimeMillis();
        System.out.print(endTime - startime);
        System.out.println("           " + count1);
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    count3.increment();
                }
            });
        }
        startime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        endTime = System.currentTimeMillis();
        System.out.print(endTime - startime);
        System.out.println("           " + count3.longValue());
    }

    private void m() throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        for (int i = 0; i < 1000; i++) {
            Thread thread;
            (thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    longAdder.increment();
                }
            })).start();
            thread.join();
        }
        System.out.println(longAdder.longValue());
    }
}
