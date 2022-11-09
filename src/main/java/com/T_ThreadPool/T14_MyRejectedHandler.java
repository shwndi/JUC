package com.T_ThreadPool;

import com.pc.A;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author czy
 * @date 2022/11/8
 */
public class T14_MyRejectedHandler {
    static AtomicInteger a=new AtomicInteger(0);
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor service = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new MyHandler());
        CountDownLatch downLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(a.getAndIncrement());
                downLatch.countDown();
            });
        }
        System.out.println("结束");
        System.in.read();

    }
    static class MyHandler implements RejectedExecutionHandler{
            static AtomicInteger a = new AtomicInteger(0);
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (executor.getQueue().size()<100) {
                System.out.println(a.getAndIncrement());
            }
        }
    }
}
