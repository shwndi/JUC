package com.core.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author czy
 * @date 2022/5/23
 */
public class BallComponent extends JPanel {
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEGIGT =350;
    private  java .util.List<Ball> balls = new ArrayList<>();
    public void add(Ball b){
        balls.add(b);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball ball : balls) {
            g2.fill(ball.getShape());
        }

    }
    public Dimension getPreferredSide(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEGIGT);
    }

}
