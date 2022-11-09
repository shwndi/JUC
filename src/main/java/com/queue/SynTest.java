package com.queue;

import java.util.IdentityHashMap;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 *
 * @author czy
 * @date 2021/5/25
 */
public class SynTest {
    public static void main(String[] args) {
        //同步队列
        BlockingQueue<String> queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "put 1");
                queue.put("1");
                System.out.println(Thread.currentThread().getName() + "put 2");
                queue.put("2");
                System.out.println(Thread.currentThread().getName() + "put 3");
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(() -> {
            try {
//                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "get"+queue.take());
//                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "get"+queue.take());
//                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "get"+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
