package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 1、现发短信后打电话
 * 是因为锁的问题
 *
 * @author czy
 * @date 2021/5/20
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone = new Phone2();

        //锁的存在
        new Thread(()->{phone.sendSms();},"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{phone.call();},"B").start();
        new Thread(()->{phone.hello();},"C").start();
    }
}

class Phone2{

    //synchronized锁的对象是方法的调用者
    //两个方法使用的是同一个锁，谁先拿到谁执行
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送短息");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }

    //这里没有锁，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}