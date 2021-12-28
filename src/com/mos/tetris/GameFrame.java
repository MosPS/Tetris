package com.mos.tetris;

import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel panel;
    MenuPanel menu;
    Score score;
    Container contentPane;

    GameFrame() {
        panel = new GamePanel();
        menu = new MenuPanel(panel);
        score = new Score();
        contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(menu, BorderLayout.WEST);
        contentPane.add(score, BorderLayout.EAST);
        setTitle("Tetris");
        setResizable(false);
        setBackground(Color.gray);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}

