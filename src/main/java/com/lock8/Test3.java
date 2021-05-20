package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 各用各的锁，拿了两把锁
 *
 * @author czy
 * @date 2021/5/20
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        Phone3 phoneA = new Phone3();
        Phone3 phoneB = new Phone3();

        //锁的存在
        new Thread(()->{phoneA.sendSms();},"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{phoneB.call();},"B").start();
    }
}

class Phone3{
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

}