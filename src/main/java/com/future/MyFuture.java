package com.future;

/**
 * @author czy
 * @date 2021/5/28
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步执行
 * 成功回调
 * 失败回调
 */
public class MyFuture{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Void表示无返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"runAsync+>Void");
        });
        System.out.println("Game Over");
        future.get();//等待然后返回阻塞执行结果，可以设置时间。
        System.out.println("-------------------------String--------------------------");
        CompletableFuture<String> futureString = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"runAsync+>String");
            int i = 10 / 0;
            return "Game Over";
        });
        String s = futureString.get();
        System.out.println(s);
        String result = futureString.whenComplete((u,t)->{
            System.out.println("u=>"+u);
            System.out.println("t=>"+t);
        }).exceptionally(e->{
            e.getStackTrace();
            try {
                return futureString.get();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (ExecutionException executionException) {
                executionException.printStackTrace();
            }
            return e.getMessage();
        }).get();

        System.out.println(result);
    }
}
