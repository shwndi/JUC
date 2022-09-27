package com.T1_Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 * @date 2022/9/27
 */
public class T_Monitro_Sync {
    List lists =new ArrayList();
    public synchronized void add(Object o){
        lists.add(o);
    }
    public synchronized int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        T_Monitro_Sync m = new T_Monitro_Sync();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                m.add(i);
                System.out.println(i);
                if (m.size()==5){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"A").start();
        new Thread(() -> {
            while (true){
                if (m.size()==5){
                    System.out.println("B 结束");
                    break;
                }
            }
        },"B").start();
    }
}
