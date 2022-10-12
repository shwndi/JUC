package com.T4_A1B2;
import java.util.concurrent.SynchronousQueue;

/**
 * @author czy
 * @date 2022/10/12
 */
public class T_BlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        /**
         * SynchronousQueue 为同步队列
         * 特性先有take方法等待阻塞着，当有put时
         * 立刻获取
         * 没有take put不能放入
         * take 和 put 必须再不同的线程，不然两个方法任意一个都会阻塞住
         * <pre>{@Code
         *  System.out.println(queue.take());
         *   queue.put(3);
         *  }</pre>
         */
        SynchronousQueue<String> queue = new SynchronousQueue<>(false);


        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                try {
                    queue.put(i+"");
                    String take = queue.take();
                    System.out.print(take+" ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"num").start();
        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                try {
                    String take = queue.take();
                    System.out.print(take);
                    queue.put(String.valueOf((char)(i+64)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"word").start();
    }
}
