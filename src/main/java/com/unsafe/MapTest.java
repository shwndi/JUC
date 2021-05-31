package com.unsafe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author czy
 * @date 2021/5/28
 */
public class MapTest {
    public static void main(String[] args) {
        //testMap();
//        safeMap();
//        safeMap2();
        safeMap3();
    }


    private static void testMap() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();

        }
    }

    private static void safeMap3() {
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        System.out.println(map.size());
    }

    private static void safeMap() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                concurrentHashMap.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(concurrentHashMap);
            }, String.valueOf(i)).start();
        }
    }

    private static void safeMap2() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        IntStream.range(0, 30).parallel().forEach(i -> {
            concurrentHashMap.put(i + "", UUID.randomUUID().toString().substring(0, 5));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(concurrentHashMap);
        });

        System.out.println(concurrentHashMap);
        System.out.println(concurrentHashMap.size());
    }

}
