package com.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * 读写锁：加到对象上
 *
 * @author czy
 * @date 2021/5/30
 */
public class MyReadAndWrite {
    public static void main(String[] args) {
        ObjCache<Integer, String> cache = new ObjCache<>();
//        ObjCache1<Integer, String> cache = new ObjCache1<>();

        for (int i = 0; i < 50; i++) {
            final int temp = i;
            new Thread(()-> {
                        System.out.println(temp);
                        cache.put(temp, Thread.currentThread().getName() + "O.O");
                    }
            ).start();
        }
        System.out.println("First Game Over!");

        IntStream.range(0, 50).parallel().forEach(i -> {
            String s = cache.get(i);
            System.out.println(i+"==>"+s);

        });
        System.out.println(cache);
    }
}

class  ObjCache<K, V> {
    private volatile Map<K, V> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(K k, V v) {
        lock.writeLock().lock();
        try {
            map.put(k, v);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public V get(K k) {
        lock.readLock().lock();
        V v;
        try {
            v = map.get(k);
        } finally {
            lock.readLock().unlock();
        }

        return v;
    }
}

class ObjCache1<K, V> {
    private volatile Map<K, V> map = new HashMap<>();

    public void put(K k, V v) {
            map.put(k, v);
    }

    public V get(K k) {
        return map.get(k);
    }
}
