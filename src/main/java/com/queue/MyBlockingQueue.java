package com.queue;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列,添加延迟，限制队列中的数量
 *
 * @author czy
 * @date 2021/5/30
 */
public class MyBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        BlockingQueue<String> s = new ArrayBlockingQueue<>(3);
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    System.out.println(
                            s.offer(Thread.currentThread().getName() + "==>" + temp, 2, TimeUnit.SECONDS)
                    );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("Game Over");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    System.out.println(s.poll(2,TimeUnit.SECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("Game Over");
    }

    static void test3() throws InterruptedException {
        BlockingQueue<String> s = new ArrayBlockingQueue<>(3);
        System.out.println(s.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(s.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(s.offer("c", 2, TimeUnit.SECONDS));

        System.out.println("put over");

        System.out.println(s.poll(2, TimeUnit.SECONDS));
        System.out.println(s.poll(2, TimeUnit.SECONDS));
        System.out.println(s.poll(2, TimeUnit.SECONDS));
        System.out.println("tale over");
    }

    private static void test2() throws InterruptedException {
        BlockingQueue<String> s = new ArrayBlockingQueue<>(3);
        System.out.println(s.offer("a"));
        System.out.println(s.offer("b"));
        System.out.println(s.offer("c"));
        //false
        //添加失败返回false
        System.out.println(s.offer("d"));
        System.out.println("offer over");
        System.out.println(s.poll());
        System.out.println(s.poll());
        System.out.println(s.poll());
        //null :队列空了返回null
        System.out.println(s.poll());
        System.out.println("take over");
    }

    static void test1() {
        BlockingQueue<String> s = new ArrayBlockingQueue<>(3);
        System.out.println(s.add("a"));
        System.out.println(s.add("b"));
        System.out.println(s.add("c"));
        //java.lang.IllegalStateException: Queue full
        //队列满了，抛出异常
//        System.out.println(s.add("d"));
        System.out.println("add over");
        System.out.println(s.remove());
        System.out.println(s.remove());
        System.out.println(s.remove());
        //java.util.NoSuchElementException
        //队列空了 抛出异常
//        System.out.println(s.remove());
    }
}
