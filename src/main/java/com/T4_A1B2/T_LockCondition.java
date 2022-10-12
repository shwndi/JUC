package com.T4_A1B2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author czy
 * @date 2022/10/12
 */
public class T_LockCondition {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        new Thread(() -> {
            try {
                lock.lock();
                condition1.await();
                for (int i = 1; i < 27; i++) {
                    System.out.print(i);
                    condition1.await();
                    condition2.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 1; i < 27; i++) {
                    System.out.print((char) (i + 64));
                    condition2.await();
                    condition1.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
