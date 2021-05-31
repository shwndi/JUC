package com.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * @author czy
 * @date 2021/5/28
 */
public class SetTest {
    public static void main(String[] args) {
        // set();
        //unSafeSet();
       // safeSet();
        safeSet2();
    }

    private static void set() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(i);
            System.out.println(set);
        }
        System.out.println(set.size());
    }

    private static void unSafeSet() {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            final int temp = i;
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(set);
        System.out.println(set.size());
    }

    private static void safeSet() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            final int temp = i;
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(set);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(set);
        System.out.println(set.size());
    }

    private static void safeSet2() {
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 30; i++) {
            final int temp = i;
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 4));
                System.out.println(set);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(set);
        System.out.println(set.size());
    }
}
