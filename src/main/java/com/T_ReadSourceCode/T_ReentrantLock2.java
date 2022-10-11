package com.T_ReadSourceCode;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T_ReentrantLock2 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"加锁");
            lock.lock();
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            while (true){
                if ("a".equals(s)){
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName()+"解锁");
                    break;
                }
            }
        },"t1").start();
        TimeUnit.SECONDS.sleep(5);
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "加锁");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "得到锁");
        }, "t2");
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"加锁");
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"得到锁");
        },"t3").start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"加锁");
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"得到锁");
        },"t4").start();
        t2.stop();
    }
    static class T2 extends Thread{
        ReentrantLock lock;
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName() + "加锁");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "得到锁");
        }
    }
}
