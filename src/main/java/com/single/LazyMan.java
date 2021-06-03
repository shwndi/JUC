package com.single;

/**
 * @author czy
 * @date 2021/5/31
 */
public class LazyMan {
    private LazyMan(){
        System.out.println(Thread.currentThread().getName()+"创建懒汉式单例");
    }
    private static LazyMan lazyMan;
    public static LazyMan getInstance(){
        if (lazyMan==null){
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        //懒汉单例在多线程下是失败的
        for (int i = 0; i < 10; i++) {
            new Thread(()->{LazyMan.getInstance();}).start();
        }
    }
}
