package com.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author czy
 * @date 2022/9/26
 */
public class T_CyclicBarrier {
    static CyclicBarrier barrier;


    public static void main(String[] args) throws InterruptedException {
        barrier = new CyclicBarrier(11, () -> {
            System.out.println("满了,发车");


        });

        for (int i = 0; i < 10; i++) {
           new Thread(() -> {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               try {
                            barrier.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

            }).start();
        }

    }
}
