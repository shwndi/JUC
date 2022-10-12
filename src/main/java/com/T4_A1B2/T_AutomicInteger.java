package com.T4_A1B2;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author czy
 * @date 2022/10/12
 */
public class T_AutomicInteger {
   static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                while (integer.get()!=0) {
                }
                System.out.print((char)(i+64));
                integer.set(1);
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                while (integer.get()!=1){
                }
                System.out.print(i);
                integer.set(0);
            }
        }).start();
        System.in.read();
    }
}
