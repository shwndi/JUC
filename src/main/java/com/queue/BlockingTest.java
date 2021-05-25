package com.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列:
 * 设置初始容量
 *
 * @author czy
 * @date 2021/5/25
 */

public class BlockingTest {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        try {
            test4();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * add返回添加结果：true or false
     * 超出容量返回：IllegalStateException 非法状态异常： Queue full
     * remove 返回移除结果
     * 当队列为空时返回：NoSuchElementException 无元素异常
     */
    public static void test1() {
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
//        System.out.println(queue.add("d"));
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }

    /**
     * offer放入成功返回true 否则返回false
     * poll取数据成功返回获取的值，没有返回null
     */
    public static void test2() {
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d"));
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    /**
     * 获取队首元素：当队列为空时
     *  o.element()抛出异常
     *  o.peek()返回null
     */
    public static void test3() {
        BlockingQueue queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
        System.out.println(queue.element());
        System.out.println(queue.remove());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("--------------------");
        System.out.println(queue.peek());
        System.out.println("--------------------");
        System.out.println(queue.element());

    }

    /**
     * 设置超时等待时间
     *
     * @throws InterruptedException
     */
    public static void test4() throws InterruptedException {
        BlockingQueue queue = new ArrayBlockingQueue<>(3);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d", 2,  TimeUnit.SECONDS));
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll(2,TimeUnit.SECONDS));
    }

}

