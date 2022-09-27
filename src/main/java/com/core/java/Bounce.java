package com.core.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author czy
 * @date 2022/5/23
 */
public class Bounce {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}