package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 测试callable实现方式
 * @author czy
 * @date 2021/5/28
 */
public class CallableDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        DemoCallable callable = new DemoCallable();
//        FutureTask<String> task = new FutureTask<>(callable);
//        new Thread(task,"AAA").start();
//        String  s = task.get();
//        System.out.println(s);
        String a = null;
        FutureTask<String> target = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName()+"AAA");
        }, a);

        new Thread(target,"abc").start();
        System.out.println(target.get());;
    }
    //call()方法可以给
}
class DemoCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        return "Game Over!";
    }
}
