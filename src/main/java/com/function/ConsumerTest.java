package com.function;

import com.pc.C;

import java.util.function.Consumer;

/**
 * @author czy
 * @date 2021/5/25
 */
public class ConsumerTest {
    public static void main(String[] args) {
        Consumer consumer = new Consumer<String>() {
            @Override
            public void accept(String a) {
                System.out.println(a+"consumer");
            }
        };
        consumer.accept("AAAA");
        Consumer lambda = a->{
            System.out.println(a+"afakjh");
        };
        lambda.accept("lambda->");
    }
}
