package com.add;

import java.util.concurrent.Exchanger;

/**
 * @author czy
 * @date 2022/9/26
 */
public class T_Exchange {
    public static void main(String[] args) {
        Exchanger<Person> exchanger = new Exchanger<>();
        new Thread(() -> {
            Person person = new Person("张三");
            try {
                person = exchanger.exchange(person);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread-"+Thread.currentThread().getName()+":"+person);
        },"张三").start();
        new Thread(() -> {
            Person person = new Person("李四");
            try {
                person = exchanger.exchange(person);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread-"+Thread.currentThread().getName()+":"+person);
        },"李四").start();
    }

    private static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
