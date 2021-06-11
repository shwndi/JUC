package com.stream;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * 要求：
 * 1、ID为偶数
 * 2、年龄大于33
 * 3、用户字母转大写
 * 4、用户字母倒序
 * 5、只输出一个用户
 *
 * @author czy
 * @date 2021/5/25
 */
public class Demo1 {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 11);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 33);
        User u4 = new User(4, "d", 44);
        User u5 = new User(6, "e", 55);
        //存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        //计算交给Stream

        //filter:过过滤
        //map:操作
        //sorted:排序
        //limit:取值
        //foreach:消费
        //链式编程
        list.stream().filter(u -> {
            return u.getId() % 2 == 0;
        })
                .filter(user -> {
                    return user.getAge() > 23;
                })
                .map(user -> {
                    return user.getName().toUpperCase();
                })
                .sorted((a, b) -> {
                    return a.compareTo(b);
                })
                .limit(1)
                .forEach(System.out::println);
        /*ArrayList<Object> list1 = new ArrayList<>();
        list.stream().filter(a -> {
            if (a ==null ) {
                return a;
            }
            return false;
        }).collect(toList())*/
    }


}
