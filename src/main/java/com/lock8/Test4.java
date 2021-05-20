package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 有延迟才能玩出来
 *
 * 编译器不阻止实例对静态方法的调用
 *
 * @author czy
 * @date 2021/5/20
 */
public class Test4{
    public static void main(String[] args){
        Phone4 phone4 = new Phone4();
        //锁的存在
        new Thread(()->{phone4.sendSms();},"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone4.call();},"B").start();
    }
}

class Phone4{

    //锁的类，static 静态方法
    //类加载就有了，所得是Class
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