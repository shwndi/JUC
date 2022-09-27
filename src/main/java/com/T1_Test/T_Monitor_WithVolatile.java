package com.T1_Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 * @date 2022/9/27
 */
public class T_Monitor_WithVolatile {
    List lists = new LinkedList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T_Monitor_WithVolatile m = new T_Monitor_WithVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                m.add(i + "");
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            while (true){
                if (m.size() == 5) {
                    break;
                }
        }
                System.out.println("结束");
    },"B").

    start();
}

}
