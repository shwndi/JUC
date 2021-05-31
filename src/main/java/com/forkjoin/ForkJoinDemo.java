package com.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 如何使用ForkJoin
 * 1、forkJoinPool通过它来执行
 * 2、计算任务 forkjoinpool.execute(ForkJopinTask task)
 * ForkJoinTask 方法
 * 1)CountedCompleter
 * 2)RecursiveAction:递归事件，无返回值
 * 3)RecursiveTask：递归任务，有返回值
 *
 * @author czy
 * @date 2021/5/25
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private long start;
    private long end;
    //
    private long temp = 10000L;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();//拆分任务，压入队列
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            //合并结果
            return task1.join() + task2.join();
        }
    }
}
