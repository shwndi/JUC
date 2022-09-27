package com.T1_Test;

import java.util.concurrent.locks.LockSupport;

/**
 * 两个线程
 * A；打印1-26，B：打印A-Z
 * A、B交替打印A1B2C3……Z26
 *
 * @author czy
 * @date 2022/9/27
 */
public class T_ABBA {
    static Thread num, word;

    public static void main(String[] args) {
        num = new Thread(() -> {
            LockSupport.park();
            for (int i = 1; i < 27; i++) {
                System.out.print(i);
                LockSupport.unpark(word);
                LockSupport.park();
            }

        });
        word = new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                System.out.print((char) (i + 64));
                LockSupport.unpark(num);
                LockSupport.park();
            }
            LockSupport.unpark(num);
        });
        word.start();
        num.start();
        try {
            word.join();
            num.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
