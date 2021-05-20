package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 1、现发短信后打电话
 * 是因为锁的问题
 *
 * @author czy
 * @date 2021/5/20
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();

        //锁的存在
        new Thread(()->{phone.sendSms();},"A").start();
        TimeUnit.MILLISECONDS.sleep(1000);
        new Thread(()->{phone.call();},"B").start();
    }
}
class Phone{

    //synchronized锁的对象是方法的调用者，即实例
    //两个方法使用的是同一个锁，谁先拿到谁执行
    public synchronized void sendSms(){

        System.out.println("发送短息");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}