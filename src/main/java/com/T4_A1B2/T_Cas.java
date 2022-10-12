package com.T4_A1B2;

import java.io.IOException;

/**
 * @author czy
 * @date 2022/10/12
 */
public class T_Cas {
    static volatile boolean flag = true;

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                while (flag) {
                }
                System.out.print(i);
                flag = true;
            }
        }, "num").start();
        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                while (!flag) {
                }
                System.out.print((char) (i + 64));
                flag = false;
            }
        }, "word").start();
        System.in.read();
    }
}
