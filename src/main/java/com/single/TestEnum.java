package com.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author czy
 * @date 2021/5/31
 */
public enum TestEnum {
    AA
}
class A{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        TestEnum aa = TestEnum.AA;
        System.out.println(aa);
        Constructor<TestEnum> constructor = TestEnum.class.getDeclaredConstructor(String.class, int.class);
        Field[] declaredFields = TestEnum.class.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            declaredFields[i].setAccessible(true);
        }
        constructor.setAccessible(true);
        TestEnum bbb = constructor.newInstance("BBB", 2);
        System.out.println(bbb);
    }
}
