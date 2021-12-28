package com.mos.tetris;

import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 300;
    static final int GAME_HEIGHT = 550;
    static final int BLOCK_WIDTH = 25;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    Graphics graphics;
    Block block;
    BlockFrame blockframe;
    Falled falled;
    Thread gameThread;

    GamePanel() {
        newBlock();
        newFalled();
        newBlockFrame();
        setVisible(true);
        setFocusable(true);
        setPreferredSize(SCREEN_SIZE);
        addKeyListener(new AL());
    }

    public void paint(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                Score.addTime();
                down();
                check();
                repaint();
                gameOver();
                delta--;
            }
        }
    }

    public void newBlock() {
        block = new Block(GAME_WIDTH, GAME_HEIGHT, BLOCK_WIDTH);
    }

    public void newBlockFrame() {
        blockframe = new BlockFrame(GAME_WIDTH, GAME_HEIGHT, BLOCK_WIDTH);
    }

    public void newFalled() {
        falled = new Falled(GAME_WIDTH, GAME_HEIGHT);
    }

    public void gameOver() {
        for (Color x: falled.gridBlocks[20]) {
            if (x != null) {
                falled.gameOver();
                block.gameOver();
                MenuPanel.gameOver();
                Score.gameOver();
                gameThread.stop();
            }
        }
    }

    public void draw(Graphics g) {
        block.draw(g);
        blockframe.draw(g);
        falled.draw(g);

        g.setColor(Color.darkGray);
        for (int i = 0; i < GAME_HEIGHT / BLOCK_WIDTH; i++) {
            g.drawLine(i * BLOCK_WIDTH + BLOCK_WIDTH, 0 + BLOCK_WIDTH, i * BLOCK_WIDTH + BLOCK_WIDTH, GAME_HEIGHT - BLOCK_WIDTH); //HEIGHT
            g.drawLine(0 + BLOCK_WIDTH, i * BLOCK_WIDTH + BLOCK_WIDTH, GAME_WIDTH - BLOCK_WIDTH, i * BLOCK_WIDTH + BLOCK_WIDTH); //WIDTH
        }
        g.fillRect(5 * 25, GAME_HEIGHT - 5 * 25, 25, -25);
    }

    public void down() {
        block.down();
    }

    public void check() {
        falled.checkLine();
        if (falled.checkIntersects(block)) {
            falled.addFalledBlocks(block);
            newBlock();
        }
        int loopTime = 0;
        for (int x: block.polygon.xpoints) {
            if (x < 25) {
                block.polygon.translate(25 - x, 0);
                block.generate.centerX += 25 - x;
                break;
            }
            if (x > GAME_WIDTH - BLOCK_WIDTH) {
                block.polygon.translate(GAME_WIDTH - BLOCK_WIDTH - x, 0);
                block.generate.centerX += GAME_WIDTH - BLOCK_WIDTH - x;
                break;
            }
            if (block.polygon.ypoints[loopTime] > GAME_HEIGHT - BLOCK_WIDTH) {
                falled.addFalledBlocks(block);
                newBlock();
                break;
            }
            loopTime++;
        }
    }

    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            block.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            block.keyReleased(e);
        }
    }
}
