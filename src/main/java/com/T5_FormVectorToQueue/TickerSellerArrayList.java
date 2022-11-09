package com.T5_FormVectorToQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author czy
 * @date 2022/10/17
 */
public class TickerSellerArrayList {

    static List<String> tickets = new ArrayList<>();

    static AtomicInteger num = new AtomicInteger(0);

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号： " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            final int j = i;
            Thread thread = new Thread(() -> {
                while (tickets.size() > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "销售了--" + tickets.remove(0));
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
