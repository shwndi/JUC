package com.future;

/**
 * @author czy
 * @date 2021/5/28
 */
public class ThreadTest {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(1121);
        }).start();
        System.out.println("214");
    }
}
