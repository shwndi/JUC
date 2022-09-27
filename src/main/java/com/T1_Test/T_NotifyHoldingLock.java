package com.T1_Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 注意一定要唤醒wait() ，否则一直在等待
 *
 * @author czy
 * @date 2022/9/27
 */
public class T_NotifyHoldingLock {
    /*volatile*/ List lists = new LinkedList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2000; i++) {
            m();
        }
    }

    private static void m() {
        T_NotifyHoldingLock m = new T_NotifyHoldingLock();
        Object o = new Object();
        Thread b = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println("启动");
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结束");
                o.notify();
            }

        }, "B");

        Thread a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (o) {
                    m.add(i);
                    System.out.println(i);
                    if (m.size() == 5) {
                        o.notify();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "A");
        a.start();
        b.start();
//        try {
//            b.join();
//            a.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
