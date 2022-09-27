package com.core.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author czy
 * @date 2022/5/23
 */
public class BounceFrameRunable extends JFrame {
    private AtomicInteger n =new AtomicInteger(0);
    private BallComponent comp;
    public static final int STEPS = 10000;
    public static final int DELAY = 20;

    public BounceFrameRunable() {
        setTitle("球");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Close", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    public void addBall() {
       // for (int j = 0; j < 10; j++) {
        int t = n.incrementAndGet();
        System.out.println("Thread :"+t);
        Runnable r = () -> {
                Ball ball = new Ball();
                ball.setX(Math.random() * 10);
                ball.setY(Math.random() * 10);
                ball.setDx(Math.random() * 10);
                ball.setDy(Math.random() * 10);
                comp.add(ball);
                try {
                    for (int i = 0; i <= STEPS; i++) {
                        ball.move(comp.getBounds());
                        comp.paint(comp.getGraphics());
                        Thread.sleep(DELAY);
                    }
                } catch (InterruptedException e) {
                    System.out.println(  Thread.currentThread().getName());
                }
            };
            Thread thread = new Thread(r);
            thread.setName("Thread:"+n);
            thread.start();
        }
 //   }
}
