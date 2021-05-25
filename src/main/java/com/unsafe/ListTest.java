package com.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author czy
 * @date 2021/5/21
 */
public class ListTest {
    public static void main(String[] args) {
        //list();
        //listThread();
        safeList();
    }



    //单线程是安全的
    private static void list() {
        List<String> list = Arrays.asList("123", "2234", "123");
    }

    //线程不安全
    //ConcurrentModificationException:并发修改异常
    private static void listThread() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 解决方案：
     * 1、List<String > vector = new Vector<>();
     * 2、List<String> list = Collections.synchronizedList();
     * 3、List<String> list = new CopyOnWriteArrayList<>();
     */
    //CopyOnWrite 写入时复制，COW 计算机程序设计的一种优化策略
    //多个线程调用的时候，list，读取的时候，固定的，写入(覆盖)
    //在写入的时候避免覆盖，造成数据问题
    //读写分离
    //CopyOnWriteArrayList() 比Vector NB在哪?效率高
    private static void safeList() {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
