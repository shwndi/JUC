package com.T_ThreadPool;

import java.util.concurrent.Executor;

/**
 * @author czy
 * @date 2022/11/2
 */
public class T01_MyExecutor implements Executor {
    public static void main(String[] args) {
        new T01_MyExecutor().execute(()->{
            System.out.println("this is my Executor");
        });
    }

    @Override
    public void execute(Runnable command) {
        System.out.println("begin");
        command.run();
        System.out.println("end");
    }
}
