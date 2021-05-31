package com.forkjoin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author czy
 * @date 2021/5/31
 */
public class MyTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();
//        test2();
//        test3();
        BigInteger big = new BigInteger("932356074711512064");
        BigInteger val = new BigInteger(String.valueOf(Long.MIN_VALUE));
        BigInteger val1 = new BigInteger(String.valueOf(Long.MAX_VALUE));
        big.subtract(val)
                .add(val1);
        System.out.println("932356074711512064");
        System.out.println(val);
        System.out.println(val1);
        System.out.println(big);

    }

    private static void test3() {
        long l = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0, 1000_0000_0000L).parallel().reduce(0, Long::sum);
        System.out.println(System.currentTimeMillis()-l);
        System.out.println(sum);
    }

    private static void test2() throws InterruptedException, ExecutionException {
        long l = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        MyForkJoin forkJoin = new MyForkJoin(0L, 1000_0000_0000L);
        ForkJoinTask<Long> submit = pool.submit(forkJoin);
        long sum = submit.get();
        System.out.println(System.currentTimeMillis()-l);
        System.out.println(sum);
    }

    private static void test1() {
        long sum = 0L;
        long l = System.currentTimeMillis();
        for (long i = 0; i <= 1000_0000_0000L; i++) {
            sum+=i;
        }
        System.out.println(System.currentTimeMillis()-l);
        System.out.println(sum);
    }
}
