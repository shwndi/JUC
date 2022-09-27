package com.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 包装类要注意引用问题
 *
 * @author czy
 * @date 2021/6/2
 */
public class Demo {
    static AtomicStampedReference<Integer> atomicStamped = new AtomicStampedReference<>(1, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicStamped.getStamp();
            System.out.println("a1=>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("a2=>"+atomicStamped.compareAndSet(1, 2,
                    atomicStamped.getStamp(), atomicStamped.getStamp() + 1));

            System.out.println("a3=>" + atomicStamped.getStamp());

            System.out.println("a4=>" +atomicStamped.compareAndSet(2, 1,
                    atomicStamped.getStamp(), atomicStamped.getStamp() + 1));

            System.out.println("a5=>" + atomicStamped.getStamp());
        }, "A").start();
        new Thread(() -> {
            int stamp = atomicStamped.getStamp();
            System.out.println("b1=>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("b2=>" +atomicStamped.compareAndSet(1, 6,
                    stamp, stamp + 1));

            System.out.println("b3=>" + atomicStamped.getStamp());
        }, "B").start();
    }
}
