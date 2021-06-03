package com.myvolatile;

import com.pc.A;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic 原子性操作
 * @author czy
 * @date 2021/5/31
 */
public class VLTDemo2 {
//    private volatile static int a= 0;
//    private static void add(){
//        a++;
//    }
    private static AtomicInteger a = new AtomicInteger();
    private static void add(){
        a.getAndIncrement();
    }
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" "+a);
    }
}
