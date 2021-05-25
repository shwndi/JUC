package com.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * 原生的线程池
 *
 * @author czy
 * @date 2021/5/25
 */
public class Demo1 {
    public static void main(String[] args) {
        ExecutorService pool=null;
        //单一线程
        pool = Executors.newSingleThreadExecutor();
        //可扩容的
        pool = Executors.newCachedThreadPool();
        //固定长度
        pool = Executors.newFixedThreadPool(5);
        try {
            for (int i = 0; i < 10; i++) {
                pool.execute(()->{
                    System.out.println(Thread.currentThread().getName() +"OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //线程池使用完，程序结束，要关闭
            pool.shutdown();
        }
    }
}
