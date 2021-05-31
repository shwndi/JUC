package com.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 限定数量：
 * acquire()是计数器
 * release()监控数量，叨叨后释放阻塞线程
 *
 * @author czy
 * @date 2021/5/28
 */
public class MySemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("Game Start =>"+temp);
                    System.out.println("Game Over =>"+temp);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.release();
            }).start();
        }
    }
}
