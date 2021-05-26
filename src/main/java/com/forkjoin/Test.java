package com.forkjoin;

import java.util.OptionalLong;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author czy
 * @date 2021/5/25
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //比较效率，数据越多越明显
        test1();
        test2();
        test3();
        //方法测试
        test4();
    }

    /**
     * 传统方式求和
     */
    private static void test1() {
        //传统
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (long i = 0; i <= 1000_0000_0000L; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis() - start;
        System.out.println(end);
        System.out.println(sum);
    }

    /**
     * 分治思想求和
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(0L, 1000_0000_0000L);
        ForkJoinTask<Long> submit = pool.submit(forkJoinDemo);
        Long result = submit.get();
        long end = System.currentTimeMillis() - start;
        System.out.println(end);
        System.out.println(result);
    }

    /**
     * Stream求和
     */
    private static void test3() {
        long start = System.currentTimeMillis();
        //parallel并行
        long reduce = LongStream.rangeClosed(0L, 1000_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis() - start;
        System.out.println(end);
        System.out.println(reduce);
    }

    /**
     * LongStream中的reduce()方法使用演示
     * identity参数作用在每一项数字，与之相加，
     * LongBinaryOperator参数是数之间的计算规则
     * parallel()开启并行运算
     */
    private static void test4() {
        long reduce1 = LongStream.rangeClosed(0L, 10L).parallel().reduce(-1, Long::sum);
        OptionalLong reduce2 = LongStream.rangeClosed(0L, 10L).parallel().reduce( Long::sum);

        System.out.println(reduce1);
        System.out.println(reduce2.getAsLong());
    }
}
