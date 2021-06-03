package com.cas;

import com.pc.A;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author czy
 * @date 2021/6/2
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(99);
        System.out.println(atomicInteger);
        atomicInteger.compareAndSet(99,100);
        System.out.println(atomicInteger);
        atomicInteger.compareAndSet(100,99);
        System.out.println(atomicInteger);
        System.out.println(atomicInteger.getAndIncrement());
    }
}
