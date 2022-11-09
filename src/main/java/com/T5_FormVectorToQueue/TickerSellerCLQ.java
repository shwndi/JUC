package com.T5_FormVectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author czy
 * @date 2022/10/17
 */
public class TickerSellerCLQ {
    static Queue<String> tickers = new ConcurrentLinkedQueue<>();
    static AtomicInteger num = new AtomicInteger(0);

    static {
        for (int i = 0; i < 1000; i++) {
            tickers.add("票编号: " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                while (tickers.size() > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String s = tickers.poll();
                    if (s == null) {
                        break;
                    }
                    System.out.println(s);
                    num.getAndIncrement();
                }
            });
            list.add(thread);
        }
        for (int i = 0; i < 10; i++) {
            Thread thread = list.get(i);
            thread.start();
            thread.join();
        }
        System.out.println(num.get());
    }
}
