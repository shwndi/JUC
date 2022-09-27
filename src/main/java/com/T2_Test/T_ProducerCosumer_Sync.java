package com.T2_Test;

import java.util.LinkedList;

/**
 * 当消费者消费了1个元素，调用了notiftyAll方法，线程1和线程2都唤醒了，它们会竞争锁，假设线程1拿到了锁，这时线程2继续在锁池中阻塞。
 *
 * 线程1直接到执行下面的代码增加元素，增加完后容器已经满(MAX)
 *
 * 但是线程1执行完，会释放锁，又因为线程2此时是醒着的，它将得到线程1释放的锁，继续从wait()处往下执行。如果是if，它不作判断就直接往满的容器中加元素，造成错误。如果是while，线程2往下执行时会再次进入到while判断。因为此时容器已经满了，所以线程2，再次被wait，保证了数据的安全
 *
 * @author czy
 * @date 2022/9/27
 */
public class T_ProducerCosumer_Sync<T> {
    final private LinkedList<T> lists = new LinkedList();
    final private int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (count == MAX) {//while 循环判断 if不可循环
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        count++;
        this.notifyAll();
    }

    public synchronized T get() {
        while (count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = lists.removeFirst();
        count--;
        notifyAll();
        return t;
    }
    public synchronized int getCount(){
        return count;
    }

    public static void main(String[] args) {
        T_ProducerCosumer_Sync<String> pc = new T_ProducerCosumer_Sync<>();
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
