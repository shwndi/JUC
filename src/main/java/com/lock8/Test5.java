package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 各用各的锁，拿了两把锁
 * 有延迟才能玩出来
 *
 * @author czy
 * @date 2021/5/20
 */
public class Test5{
    public static void main(String[] args) throws InterruptedException {
        Phone5 phoneA = new Phone5();
        Phone5 phoneB = new Phone5();
        //锁的存在
        new Thread(()->{phoneA.sendSms();},"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{phoneB.call();},"B").start();
    }
}

class Phone5{

    //锁的类，static 静态方法
    //类加载就有了，所得是Class,不同实例使用Class模板的锁
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送短息");
    }
    public static synchronized void call(){
        System.out.println("打电话");
    }

}