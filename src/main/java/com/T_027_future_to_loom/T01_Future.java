package com.T_027_future_to_loom;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author czy
 * @date 2022/11/8
 */
public class T01_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(() -> 8);
        Integer i = future.get();
        System.out.println(i);
    }
}
