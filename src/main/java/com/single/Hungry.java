package com.single;

/**
 * 饿汉式单例
 * @author czy
 * @date 2021/5/31
 */
public class Hungry {

    private final static Hungry HUNGRY = new Hungry();
    //可能会浪费空间
    private Hungry(){
        System.out.println(Thread.currentThread().getName()+"创建饿汉式单例");
    }
    public static Hungry getInstance(){
        return HUNGRY;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()->{LazyMan.getInstance();}).start();
        }
    }
}
