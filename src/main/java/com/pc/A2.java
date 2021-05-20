package com.pc;

/**
 * 线程之间的通信问题：生产者消费者问题 等待唤醒，通知唤醒
 * 线程交替执行 A B操作同一个变量 mum = 0
 * A num+1
 * B num-1
 * 两个线程是可以的，多个就会出现虚假唤醒问题
 * if改成while
 * @author czy
 * @date 2021/5/20
 */
public class A2 {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.increment();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data.decrement();
            }
        },"D").start();
    }
}

//等待、业务、通知
class Data2{
    private int num = 0;
    //+1
    public synchronized void increment(){
        while(num!=0){
                //等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        this.notifyAll();
    }
    //-1
    public synchronized void decrement(){
        while(num==0){
            //等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        this.notifyAll();
    }
}
