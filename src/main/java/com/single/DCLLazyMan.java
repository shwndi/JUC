package com.single;

/**
 * 双重检测锁模式，懒汉式单例 DCL懒汉式
 * @author czy
 * @date 2021/5/31
 */
public class DCLLazyMan {
    private DCLLazyMan(){
        System.out.println(Thread.currentThread().getName()+"创建懒汉式单例");
    }
    private volatile static DCLLazyMan dclLazyMan;
    public static DCLLazyMan getInstance(){
        if (dclLazyMan==null){
            synchronized (DCLLazyMan.class) {
                if (dclLazyMan == null) {
                    dclLazyMan = new DCLLazyMan();//不是原子性操作
                    //1、分配内存空间
                    //2、执行构造方法，初始化对象
                    //3、把这个对象执行空间
                    //指令重排序会发生对象为空的现象。不安全
                }
            }
        }
        return dclLazyMan;
    }

    public static void main(String[] args) {
        //懒汉单例在多线程下是失败的
        for (int i = 0; i < 10; i++) {
            new Thread(()->{DCLLazyMan.getInstance();}).start();
        }
    }
}
