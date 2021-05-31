package com.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author czy
 * @date 2021/5/31
 */
public class MyFuture1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步调用");
        });

        System.out.println("Game Over");
        future.get(2,TimeUnit.SECONDS);
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
            return "嘎嘎嘎";
        });
        String s = future1.get();
        System.out.println(s);
        String result = future1.whenCompleteAsync((u,e)->{
            System.out.println(u);
            System.out.println(e);
        }).exceptionally((e)->{
            e.printStackTrace();
            return e.getMessage();
        }).get();
        System.out.println(result);
    }
}
