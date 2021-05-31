package com.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 加法器
 * 1、初始值，和任务函数
 * 2、 每开启一个线程，机会自增1
 * 3、直到达到初始值后停下；同时执行预设的线程。
 * 注：不会影响CyclicBarrier之外的程序执行。
 *
 * @author czy
 * @date 2021/5/28
 */
public class MyCyclicBarrier {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        AtomicInteger integerA = new AtomicInteger(0);
        AtomicInteger integerB = new AtomicInteger(0);
        CyclicBarrier barrier = new CyclicBarrier(10,()->{
             System.out.println("Game Over!");
        });
        for (int i = 0; i < 11; i++) {
            new Thread(()->{
                integerA.getAndIncrement();
                System.out.println(Thread.currentThread().getName()+"==>A"+integerA);
                System.out.println("A  "+barrier.getNumberWaiting());
                System.out.println("A  "+barrier.getParties());
            }).start();
        }
        System.out.println("--------AAA-------------");
        for (int i = 0; i < 11; i++) {
            new Thread(()->{
                integerB.getAndIncrement();
                System.out.println(Thread.currentThread().getName()+"==>B"+integerB);
                System.out.println("B  "+barrier.getNumberWaiting());
                System.out.println("B  "+barrier.getParties());
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("B  "+barrier.getNumberWaiting());
            }).start();
        }

        System.out.println("---------BBB------------");
    }
}
