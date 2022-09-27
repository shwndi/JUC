package com.add;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 类似于countdownLatch和cyclicBarrier
 *
 * @author czy
 * @date 2022/9/26
 */
public class T_Phaser {
    static Random r = new Random();
    static MarriagePhaser marriagePhaser = new MarriagePhaser();

    static void sleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        marriagePhaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            final int num = i;
            new Thread(new Person(i + "客人")).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            sleep(1000);
            System.out.printf("%s 到达现场\n", name);
            marriagePhaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            sleep(1000);
            System.out.printf("%s 吃完了\n", name);
            marriagePhaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            sleep(1000);
            System.out.printf("%s 离开\n", name);
            marriagePhaser.arriveAndAwaitAdvance();
        }

        public void hug() {
            if (name.equals("新郎") || name.equals("新娘")) {
                sleep(1000);
                System.out.printf("%s 洞房\n", name);
            } else {
                marriagePhaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

    private static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了");
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了");
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了");
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束");
                    System.out.println();
                    return true;
                default:
                    return true;
            }
        }
    }
}
