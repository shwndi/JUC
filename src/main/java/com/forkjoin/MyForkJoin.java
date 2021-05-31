package com.forkjoin;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * * 如何使用ForkJoin
 * * 1、forkJoinPool通过它来执行
 * * 2、计算任务 forkjoinpool.execute(ForkJopinTask task)
 * * ForkJoinTask 方法
 * * 1)CountedCompleter
 * * 2)RecursiveAction:递归事件，无返回值
 * * 3)RecursiveTask：递归任务，有返回值
 *
 * @author czy
 * @date 2021/5/31
 */
public class MyForkJoin extends RecursiveTask<Long> {
    private long start;
    private long end;
    private long middle = 10000L;

    public MyForkJoin(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < middle) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long temp = (end + start)/ 2;
            MyForkJoin myForkJoin1 = new MyForkJoin(start, temp);
            myForkJoin1.fork();
            MyForkJoin myForkJoin2 = new MyForkJoin(temp + 1, end);
            myForkJoin2.fork();
            return myForkJoin1.join() + myForkJoin2.join();
        }
    }

}
