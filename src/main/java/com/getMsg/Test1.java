package com.getMsg;

import java.util.concurrent.Callable;

/**
 * 获取处理器信息
 * 获取线程状态信息
 *
 * @author czy
 * @date 2021/5/19
 */
public class Test1 {
    public static void main(String[] args) {
        //获取cpu的核数
        //cpu密集型，I/O密集型
        System.out.println(Runtime.getRuntime().availableProcessors());
        /**
         * 线程的六个状态：
         * run、运行，阻塞、等待、超时等待、终止
         */
        Thread.State[] values = Thread.State.values();
        for (int i = 0; i < values.length; i++) {
             System.out.println(values[i]);
        }
    }
}
