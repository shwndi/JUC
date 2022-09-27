package com.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 等待n次循环后执行
 * 通过await() 计数
 *
 * @author czy
 * @date 2021/5/22
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10,()->{
            System.out.println("Game Over");
        });
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread((()->{
                System.out.println(Thread.currentThread().getName()+"=>"+temp);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            })).start();
        }
    }
}
