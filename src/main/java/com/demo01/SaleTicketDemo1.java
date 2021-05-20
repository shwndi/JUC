package com.demo01;

/**
 * 基本的卖票例子，多线程不安全的体现
 * <p>
 * 基本的多线程开发，公司中的开发
 * 线程就是一个单独的资源类，没有任何的附属操作！
 * 资源类：属性、方法(oop) 实现了解耦
 *
 * @author czy
 * @date 2021/5/20
 */
public class SaleTicketDemo1 {
    public static void main(String[] args) {
        //多线程操作同一个资源类，把资源类放在线程中
        Ticket ticket = new Ticket();
        //@FunctionalInterface：函数式接口，jdk 1.8 lambda表达式（）->{}
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

/**
 * 资源类OOP
 * 面向对象编程
 */
class Ticket {
    private int number = 30;

    public void sale() {
        if (number > 0) {
            System.out.println("有" + number + "张票， " + Thread.currentThread().getName() + "买了一张票，还剩" + (--number) + "张票");
        } else {
            System.out.println("票卖完了！");
        }
    }
}
