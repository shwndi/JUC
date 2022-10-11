package com.T_ReadSourceCode;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 源码：
 * 1、当创建ReentrantLock对象时，通过构造器初始化对象的sync属性，
 * sync是个抽象类，有两种实现方式：FairSync和NonFairSync实现类
 * 两者的不同点在于tryAcquire()
 * 2、当ReentrantLock 调用lock()方法时,调用的时rl对象中中sync变量的acquire()方法
 * 这里先看非公平锁的运行流程：
 * if (!tryAcquire(arg) &&
 *             acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
 *             selfInterrupt();
 *
 * tryAcquire(arg)尝试或取锁，如果锁未被占用，设置状态和占用锁的线程，此处根据状态和线程判断可冲入，如果未获取到，则进入
 *队列，使用locksupport.park睡眠，等待其他线程释放资源，队列是有cpu控制的，调用unsave的U.park方法
 *
 **/
public class T_ReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("t1");
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("第一层锁");
            lock.lock();
            try {
                System.out.println("第二层解锁");
            } finally {
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }
//        Thread.sleep(1000);
        new Thread(() -> {
            lock.lock();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请输入\"a\"解锁");
                String s = scanner.nextLine();
                if ("a".equals(s)) {
                    lock.unlock();
                    break;
                }
            }

        }, "t3").start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> {
            System.out.println("t4获取锁");
            lock.lock();
            System.out.println("t4获取到锁了");
        }, "t4").start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> {
            System.out.println("t5获取锁");
            lock.lock();
            System.out.println("t5获取到锁了");
        }, "t5").start();
        new Thread(() -> {
            System.out.println("t6获取锁");
            lock.lock();
            System.out.println("t6获取到锁了");
        }, "t6").start();
    }
}
