package com.mos.tetris;

import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class MenuPanel extends JPanel implements ActionListener {
    static JButton jButtonStart;
    static JButton jButtonStop;
    static JButton jButtonReset;
    GamePanel panel;

    MenuPanel(GamePanel panel) {
        this.panel = panel;
        Dimension SCREEN_SIZE = new Dimension(100, 200);
        setVisible(true);
        setFocusable(false);
        setPreferredSize(SCREEN_SIZE);
        jButtonStart = new JButton("スタート");
        jButtonStop = new JButton("ストップ");
        jButtonReset = new JButton("リセット");
        jButtonStart.setFocusable(false);
        jButtonStop.setFocusable(false);
        jButtonReset.setFocusable(false);
        jButtonStart.addActionListener(this);
        jButtonStop.addActionListener(this);
        jButtonReset.addActionListener(this);
        jButtonStart.setActionCommand("Start");
        jButtonStop.setActionCommand("Stop");
        jButtonReset.setActionCommand("Reset");
        add(jButtonStart);
        add(jButtonStop);
        add(jButtonReset);
        buttonSet();
    }

    public static void buttonSet() {
        jButtonStart.setEnabled(true);
        jButtonStop.setEnabled(false);
        jButtonReset.setEnabled(false);
    }

    public static void gameOver() {
        buttonSet();
        jButtonStart.setActionCommand("reStart");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("Start")) {
            panel.gameThread = new Thread(panel);
            panel.gameThread.start();
            jButtonStop.setEnabled(true);
            jButtonReset.setEnabled(true);
            jButtonStart.setEnabled(false);
            jButtonReset.setActionCommand("Reset");
        }
        if (cmd.equals("reStart")) {
            panel.newBlock();
            panel.gameThread = new Thread(panel);
            panel.gameThread.start();
            jButtonStop.setEnabled(true);
            jButtonReset.setEnabled(true);
            jButtonStart.setEnabled(false);
            jButtonStart.setActionCommand("Start");
        }
        if (cmd.equals("Stop")) {
            panel.gameThread.stop();
            jButtonStart.setEnabled(true);
            jButtonStop.setEnabled(false);
            jButtonReset.setActionCommand("stopReset");
        }
        if (cmd.equals("Reset")) {
            jButtonReset.setEnabled(false);
            panel.falled.gridBlocks[20][0] = Color.gray;
        }
        if (cmd.equals("stopReset")) {
            panel.gameThread = new Thread(panel);
            panel.gameThread.start();
            jButtonReset.setEnabled(false);
            panel.falled.gridBlocks[20][0] = Color.gray;
        }
    }
}
