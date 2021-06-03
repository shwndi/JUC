package com.unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author czy
 * @date 2021/6/3
 */
public class MyList {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            test();
        }
    }

    private static void test() {
        //声明数据源集合
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            //添加100个元素到集合中
            list.add(i);
        }
        //添加数据的集合
        List<Integer> list2 = new CopyOnWriteArrayList<>();
        //使用parallelStream的遍历方法来添加元素到新的集合
        list.parallelStream().forEach(i -> {
            list2.add(i);
        });
        //打印添加元素之后的集合长度
        System.out.println(list2.size());
    }
}
