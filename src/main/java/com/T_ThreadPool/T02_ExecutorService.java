package com.T_ThreadPool;

import com.vo.Person;

import java.util.concurrent.*;

/**
 * @author czy
 * @date 2022/11/2
 */
public class T02_ExecutorService  {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(12);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(12);
        Future<?> submit1 = executorService.submit(() -> {
            System.out.println("submit first run...");
            return 1;
        });
        executorService.execute(()-> System.out.println("execute run。。。"));
        Person person = new Person();
        Future<Person> submit = executorService.submit(() -> {
            System.out.println("submit second run...");
            person.setId("123");
        }, person);

        System.out.println(submit1.get());
        System.out.println(submit.get());
    }

}
