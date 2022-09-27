package com.core.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author czy
 * @date 2022/5/23
 */
public class BounceFrame extends JFrame{
    private BallComponent comp;
    public static final int STEPS = 10000;
    public static final int DELAY = 3;

    public BounceFrame() {
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
        try {
            Ball ball = new Ball();
            comp.add(ball);
            for (int i = 0; i <= STEPS; i++) {
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {

        }
    }
}

