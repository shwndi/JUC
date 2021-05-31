package com.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 此线程池
 * @author czy
 * @date 2021/5/30
 */
public class MyDemo {
    public static void main(String[] args) {
        /**
         * 单一线程
         * linkblockingqueue
         * AboutPolicy
         */
        ExecutorService service = Executors.newSingleThreadExecutor();
        /**
         * 可扩容线程
         * SynchronousQueue：非公平访问策略
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         * 固定长度
         */
        ExecutorService service1 = Executors.newFixedThreadPool(4);
    }
}
