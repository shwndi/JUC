package com.myvolatile;

import java.util.concurrent.TimeUnit;

/**
 * Java memory model
 *
 * @author czy
 * @date 2021/5/31
 */
public class JMMDemo {
    //不加volatile的时候，A线程获取了a="大鹌鹑";main线程修改了a的值，A线程不晓得。叫做线程不可见。
//    private static volatile String a = "大鹌鹑";
    private static  String a = "大鹌鹑";
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
                while(a.equals("大鹌鹑")){
                }
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
            a="小猫咪器";
        System.out.println(a    );
    }
}
