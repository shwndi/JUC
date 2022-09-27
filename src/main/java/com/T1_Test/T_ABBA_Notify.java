package com.T1_Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author czy
 * @date 2022/9/27
 */
public class T_ABBA_Notify {
    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 1; i < 27; i++) {
                    System.out.print(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "number").start();
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i < 27; i++) {
                    System.out.print((char) (i + 64));
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        }, "word").start();
    }
}
