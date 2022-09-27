package com.T2_Test;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author czy
 * @date 2022/9/27
 */
public class T_ProducerCosumer_Condition<T> {
    final private LinkedList<T> lists = new LinkedList();
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    /**
     * condition 的本质是等待对列
     */
    private Condition producer = lock.newCondition();
    private Condition cosumer = lock.newCondition();

    public void put(T t) {
        lock.lock();
        try {
            while (count == MAX) {
                producer.await();
            }
            lists.add(t);
            count++;
            cosumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        lock.lock();
        try {
            while (count == 0) {
                cosumer.await();
            }
            t = lists.removeFirst();
            count--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }
    public  int getCount(){
        return count;
    }
    public static void main(String[] args) {
        T_ProducerCosumer_Condition<String> pc = new T_ProducerCosumer_Condition<>();
        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                for (int k = 0; k < 5; k++) {
                    System.out.println(Thread.currentThread().getName()+"拿出"+pc.get()+"  count="+ pc.getCount());
                }
            }, "消费者" + i).start();
        }
        for (int i = 0; i < 2; i++) {

            new Thread(() -> {
                for (int j = 0; j <12; j++) {
                    pc.put(String.valueOf(j));
                    System.out.println(Thread.currentThread().getName()+"放入"+"  count="+ pc.getCount());
                }
            }, "生产者" + i).start();
        }
    }
}
