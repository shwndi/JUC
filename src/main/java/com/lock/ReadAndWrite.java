package com.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author czy
 * @date 2021/5/24
 */
public class ReadAndWrite {
    public static void main(String[] args) {
        CacheLock cache = new CacheLock();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.put(String.valueOf(temp), temp);
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.get(String.valueOf(temp));
            }, String.valueOf(i)).start();
        }
    }
}

class Cache {
    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String k, Object v) {
        System.out.println(Thread.currentThread().getName() + "写入前" + k);
        map.put(k, v);
        System.out.println(Thread.currentThread().getName() + "写入后");
    }

    public void get(String k) {
        Object o = map.get(k);
        System.out.println(Thread.currentThread().getName() + "读取" + k);
    }
}

class CacheLock {
    private volatile Map<String, Object> map = new HashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //独占锁（写锁）一次只能被一个线程占有
    public void put(String k, Object v) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "写入前" + k);
            map.put(k, v);
            System.out.println(Thread.currentThread().getName() + "写入后");
        } finally {
            lock.writeLock().unlock();
        }
    }

    //共享锁（读锁）多个线程可以同时占有
    public void get(String k) {
        lock.readLock().lock();
        try {
            Object o = map.get(k);
            System.out.println(Thread.currentThread().getName() + "读取" + k);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}