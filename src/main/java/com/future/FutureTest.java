package com.future;

import com.pc.C;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用
 * 异步执行
 * 成功回调
 * 失败回调
 * @author czy
 * @date 2021/5/27
 */
public class FutureTest{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //发起一个请求
        //Void 无返回值
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"=》Void");
        });
        System.out.println("AAA");
        completableFuture.get();//获取结果
        /**
         * 有返回值的异步回调
         */
        System.out.println("有返回值的异步回调");
        CompletableFuture<Integer> completableFuture1 =  CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"=》Void");
            return 123;
        });
        System.out.println(completableFuture1.whenComplete((t,u)->{
            System.out.println("t="+t);//正常的返回结果
            System.out.println("u="+u);//错误信息
        }).exceptionally(e->{
            System.out.println(e.getMessage());//获取错误返回结果
            return 233;
        }).get());


    }
}
