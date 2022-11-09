package com.T5_FormVectorToQueue;

import java.util.Vector;

/**
 * @author czy
 * @date 2022/10/17
 */
public class TickerSellerVector {
    static Vector<String> tickers = new Vector<>();
    static {
        for (int i = 0; i < 1000; i++) {
            tickers.add("票编号: "+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickers.size()>0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tickers.remove(0));
                }
            }).start();
        }
    }
}
