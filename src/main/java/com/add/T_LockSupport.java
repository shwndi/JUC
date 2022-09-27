package com.add;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程停止启动控制
 * @author czy
 * @date 2022/9/27
 */
public class T_LockSupport {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        //LockSupport.unpark(thread);  //提前放行
        TimeUnit.SECONDS.sleep(8);
        System.out.println("8 second later");
        LockSupport.unpark(thread);
    }
}
