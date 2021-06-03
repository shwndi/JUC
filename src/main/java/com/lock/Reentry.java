package com.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author czy
 * @date 2021/6/2
 */
public class Reentry {
    public static void main(String[] args) {
        A a = new A();
        new Thread(()->{
            a.doRead();
        },"A1").start();
        new Thread(()->{
            a.doRead();
        },"A2").start();
        B b = new B();
        new Thread(()->{
            b.doRead();
        },"B1").start();
        new Thread(()->{
            b.doRead();
        },"B2").start();
    }
}
class A{
    public synchronized void doRead(){
        System.out.println(Thread.currentThread().getName()+"read");
        doWrite();
    }
    public synchronized void doWrite(){
        System.out.println(Thread.currentThread().getName()+"write");
    }
}
class B{
    //lock必须配对
    Lock lock = new ReentrantLock();
    public  void doRead(){
            lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"read");
            doWrite();
        } finally {
            lock.unlock();
        }
    }
    public  void doWrite(){
            lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"write");
        } finally {
            lock.unlock();
        }
    }
}