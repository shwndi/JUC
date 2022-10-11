package com.T_ReadSourceCode;

import com.vo.Person;

import java.util.concurrent.TimeUnit;

/**
 *
 * thread 中维护了threadlocal的threadlocalmap对象，
 * 这个map对象交给threadlocal来操作
 * 其中保存了threadlocal和value作为键值对
 *
 */
public class T_ThreadLocal {
    static ThreadLocal<Person> p = new ThreadLocal<>();
    public static void main(String[] args) {
        Thread.currentThread().setName("主线程");
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(p.get());
        },"a").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            p.set(new Person().setName("lisi"));
        },"b").start();
        p.remove();
    }
}
