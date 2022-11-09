package com.T_ThreadPool;

import java.util.concurrent.*;

/**
 * @author czy
 * @date 2022/11/3
 */
public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable(){

            @Override
            public String call() throws Exception {
                return "there is callable";
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(callable);
        System.out.println(submit.get());
        executorService.shutdown();
    }
}
