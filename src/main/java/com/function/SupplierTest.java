package com.function;

import java.util.function.Supplier;

/**
 * @author czy
 * @date 2021/5/25
 */
public class SupplierTest {
    public static void main(String[] args) {
        Supplier supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Supplier";
            }
        };
        System.out.println(supplier.get());
        Supplier lam = ()->{return "lambda";};
        System.out.println(lam.get());
    }
}
