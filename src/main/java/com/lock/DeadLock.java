package com.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author czy
 * @date 2021/6/1
 */
public class DeadLock {
    public static void main(String[] args) {
        String A= "A";
        String B ="B";
        new Thread(
            new MyThread(A,B),"T").start();
        new Thread(
            new MyThread(B,A),"D").start();
    }
}
class MyThread implements Runnable{
    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"lockA"+lockA+"get=>"+lockB);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"lockB"+lockB+"get=>"+lockA);
            }
        }
    }
}
