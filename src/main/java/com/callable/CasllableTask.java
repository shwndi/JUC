package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author czy
 * @date 2021/5/22
 */
public class CasllableTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        FutureTask<String> task = new FutureTask<>(myThread);
        new Thread(task,"a").start();
        //get方法会产生阻塞，放到最后处理
        //或者使用异步处理
        String s = task.get();
        System.out.println(s);
//        new Thread(new FutureTask<String>(()->{
//            System.out.println("Callable");
//            return "Callable";}),"b").start();

    }
}
class MyThread implements Callable<String>{


    @Override
    public String call() throws Exception {
        System.out.println("Callable");
        return "Callable";
    }
}
