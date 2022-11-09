package com.T6_Queue;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/**
 * 优先队列
 *
 * @author czy
 * @date 2022/10/17
 */
public class T_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();
        PriorityQueue<Object> queue = new PriorityQueue<>((o1, o2) ->
                o2.hashCode() - o1.hashCode()
        );
        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add((char) (i + 64));
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(queue.poll());
            }
        }).start();

//        q.add("c");
//        q.add("e");
//        q.add("a");
//        q.add("d");
//        q.add("z");
//
//        for (int i = 0; i < 5; i++) {
//            System.out.println(q.poll());
//        }

    }
}
