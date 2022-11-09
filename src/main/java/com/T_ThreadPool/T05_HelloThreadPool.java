package com.T_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 * @date 2022/11/3
 */
public class T05_HelloThreadPool {
    static class Task implements Runnable{
        private int i;
        public Task(int i){
            this.i= i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"task"+i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i <8; i++) {
            threadPoolExecutor.execute(new Task(i));
        }
        System.out.println(threadPoolExecutor.getQueue());
        threadPoolExecutor.execute(new Task(100));
    }
}
