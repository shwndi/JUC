package com.single;

import com.sun.corba.se.spi.ior.IdentifiableContainerBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author czy
 * @date 2021/5/31
 */
public class UnSafeReflect {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        LazyMan lazyMan = LazyMan.getInstance();
        Constructor<LazyMan> constructor = LazyMan.class.getDeclaredConstructor(null);
        //无视私有的关键字
        constructor.setAccessible(true);
        LazyMan lazyMan1 = constructor.newInstance();
        System.out.println(lazyMan);
        System.out.println(lazyMan1);
    }
}
//}main创建懒汉式单例
//        main创建懒汉式单例
//com.single.LazyMan@1540e19d
//        com.single.LazyMan@677327b6

