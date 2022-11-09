package com.queue;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;

/**
 * @author czy
 * @date 2022/10/27
 */
public class CycleArrayQueue {
    private int[] arr;
    private int front; // 队头指针(索引值)
    private int rear; // 队尾指针(索引值)
    private int capacity; // 队列容量=数组长度
    private static final int MIN_CAPACITY = 3;
    private Object object;

    public CycleArrayQueue(int capacity) {
        if (capacity <= MIN_CAPACITY) {
            capacity = MIN_CAPACITY;
        }
        arr = new int[capacity];
        this.capacity = capacity;
        rear = 0;
        front = 0;
    }

    @Override
    public String toString() {
        return "CycleArrayQueue{" +
                "arr=" + Arrays.toString(arr) +
                ", front=" + front +
                ", rear=" + rear +
                ", capacity=" + capacity +
                '}';
    }

    public void put(int num) throws InterruptedException {
        synchronized (object) {
            front++;
            if (front >= capacity) {
                front = 0;
            }
            arr[front] = num;
        }
        object.notifyAll();
        object.wait();
    }

    public int get() throws InterruptedException {
        object.wait();
        synchronized (object) {
            while (front == rear) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            front++;
            return arr[front + 1];
        }
    }

    public static void main(String[] args) throws IOException {
//        CycleArrayQueue cycleArrayQueue = new CycleArrayQueue(20);
//        new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                try {
//                    cycleArrayQueue.put(i);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                LockSupport.park();
//            }
//        }).start();
//        new Thread(() -> {
//            while (true) {
//                System.out.println(cycleArrayQueue.get());
//                LockSupport.unpark();
//            }
//        }).start();
//        System.in.read();
    }
}
