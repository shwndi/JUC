package com.pc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程之间循环调用
 * A->B，B->C，C->A
 *
 * @author czy
 * @date 2021/5/20
 */
public class D {
    public static void main(String[] args) {
        DataD data = new DataD();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();
    }
}

//等待、业务、通知
class DataD {
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();
    private int number = 1;

    public void printA() {
        System.out.println(Thread.currentThread().getName() + "AAAAA");
        lock.lock();
        try {
            while (number != 1) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "printA");
            number = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        System.out.println(Thread.currentThread().getName() + "BBBBB");
        lock.lock();
        try {
            while (number != 2) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "printB");
            number = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        System.out.println(Thread.currentThread().getName() + "CCCCCC");
        lock.lock();
        try {
            while (number != 3) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "printC");
            number = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

