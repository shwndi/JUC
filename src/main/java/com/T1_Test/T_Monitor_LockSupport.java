package com.T1_Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author czy
 * @date 2022/9/27
 */
public class T_Monitor_LockSupport {
    /*volatile*/ List lists = new LinkedList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    static Thread a, b;

    public static void main(String[] args) {
        T_Monitor_LockSupport m = new T_Monitor_LockSupport();

        a = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                m.add(i);
                System.out.println(i);
                if (m.size() == 5) {
                    LockSupport.unpark(b);
                    LockSupport.park();
                }
            }
        }, "A");
        b = new Thread(() -> {
            LockSupport.park();
            System.out.println("结束");
            LockSupport.unpark(a);
        }, "B");
        a.start();
        b.start();
    }
}
