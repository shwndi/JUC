package com.function;

import java.util.function.Function;

/**
 *
 *  Function<T, R>
 *  R apply(T t);
 *
 * FunctionalInterface
 * @author czy
 * @date 2021/5/25
 */
public class FunctionTest {
    public static void main(String[] args) {
        //匿名内部类
        Function<String, String> function = new Function<String, String>() {

            @Override
            public String apply(String s) {
                return s;
            }
        };
        System.out.println(function.apply("function.apply()"));
        //lambda实现
        Function<String,String> functionL  = (s)->{
            return s;
        };
        System.out.println(functionL.apply("lambda"));
    }
}
