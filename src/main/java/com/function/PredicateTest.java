package com.function;

import java.util.function.Predicate;

/**
 * Predicate<T>
 *  boolean test(T t)
 *
 * @author czy
 * @date 2021/5/25
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate p = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };
        System.out.println(p.test("sd"));
        //lambda
        Predicate<String> predicate = s-> {
            return false;
        };
        System.out.println(p.test("lambda"));
    }
}
