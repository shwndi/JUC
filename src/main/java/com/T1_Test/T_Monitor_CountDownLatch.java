package com.T1_Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author czy
 * @date 2022/9/27
 */
public class T_Monitor_CountDownLatch {
    /*volatile*/ List lists = new LinkedList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        T_Monitor_CountDownLatch m = new T_Monitor_CountDownLatch();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                m.add(i);
                System.out.println(i);
                if (m.size()==5){
                    latch.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }).start();
        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束");
            latch2.countDown();
        }).start();
    }

}
