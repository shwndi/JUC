package com.single;

/**
 * 单例模式的核心是构造器私有
 * 静态内部类、
 * 不安全的
 * @author czy
 * @date 2021/5/31
 */
public class Holder {
    private Holder(){}
    public static  Holder getInstance(){
        return InnerClass.HOLDER;
    }
    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }
}
