package com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 *
 * @author czy
 * @date 2021/6/3
 */
public class SpinLock {

    /**
     * 没有初始化对象为null
     * initialValue
     */
    static AtomicReference<Integer> atomicReference1 = new AtomicReference(1);
    static AtomicReference<Thread> atomicReference2 = new AtomicReference();

    //加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "==>myLock");
        //当线程存在的时候自旋
        while (!atomicReference2.compareAndSet(null, thread)) {
        }
    }

    //解锁
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "==>myUnLock");
        //当线程不存在的时候 自旋
        while (atomicReference2.compareAndSet(thread, null)) {
        }
    }

    public static void main(String[] args) {
        SpinLock lock = new SpinLock();
        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.myUnLock();
            }
        },"T1").start();
        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.myUnLock();
            }
        },"T2").start();

    }
}
