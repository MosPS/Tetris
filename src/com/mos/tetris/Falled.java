package com.mos.tetris;

import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class Falled {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    static Color[][] gridBlocks;
    int xGrid;
    int yGrid;

    Falled(int GAME_WIDTH, int GAME_HEIGHT) {
        this.GAME_WIDTH = GAME_WIDTH;
        this.GAME_HEIGHT = GAME_HEIGHT;
        xGrid = GAME_WIDTH / 25;
        yGrid = GAME_HEIGHT / 25;
        gridBlocks = new Color[yGrid][xGrid];
    }

    public void addFalledBlocks(Block block) {
        int loopTimeY = 0;
        int loopTimeX = 0;
        for (Color[] yGrid: gridBlocks) {
            loopTimeX = 0;
            for (Color xGrid: yGrid) {
                if (block.polygon.contains(12.5 + loopTimeX * 25, this.GAME_HEIGHT - (loopTimeY * 25))) {
                    gridBlocks[loopTimeY][loopTimeX] = block.generate.color;
                }
                loopTimeX++;
            }
            loopTimeY++;
        }
    }

    public void removeFalledBlocks(int count) {
        boolean loopCheck = false;
        int loopTimeX = 0;
        int loopTimeY = 0;
        yLoop: for (Color[] yGrid: gridBlocks) {
            loopTimeX = 0;
            for (Color xGrid: yGrid) {
                if (loopTimeY == count) {
                    gridBlocks[loopTimeY][loopTimeX] = null;
                    if (loopCheck == false && yGrid.length - 1 == loopTimeX) {
                        loopCheck = true;
                        loopTimeY++;
                        continue yLoop;
                    }
                }
                if (loopCheck) {
                    gridBlocks[loopTimeY - 1][loopTimeX] = gridBlocks[loopTimeY][loopTimeX];
                    gridBlocks[loopTimeY][loopTimeX] = null;
                }
                loopTimeX++;
            }
            loopTimeY++;
        }
    }

    public static boolean checkIntersects(Block block) {
        boolean b = false;
        int loopTimeY = 0;
        int loopTimeX = 0;
        for (Color[] yGrid: gridBlocks) {
            loopTimeX = 0;
            for (Color xGrid: yGrid) {
                if (xGrid != null) {
                    if (block.polygon.contains(12.5 + loopTimeX * 25, GAME_HEIGHT - (25 + loopTimeY * 25))) {
                        b = true;
                    }
                }
                loopTimeX++;
            }
            loopTimeY++;
        }
        return b;
    }

    public void checkLine() {
        int lineCount = 0;
        int loopTimeY = 0;
        int loopTimeX = 0;
        for (Color[] yGrid: gridBlocks) {
            loopTimeX = 0;
            lineCount = 0;
            for (Color xGrid: yGrid) {
                if (xGrid != null) {
                    lineCount++;
                }
                if ((yGrid.length - 2) == lineCount) {
                    removeFalledBlocks(loopTimeY);
                    Score.addScore(100);
                    Score.addLineScore();
                    break;
                }
                loopTimeX++;
            }
            loopTimeY++;
        }
    }

    public void gameOver() {
        gridBlocks = new Color[yGrid][xGrid];
    }

    public void draw(Graphics g) {
        int loopTimeY = 0;
        int loopTimeX = 0;
        for (Color[] yGrid: gridBlocks) {
            loopTimeX = 0;
            for (Color xGrid: yGrid) {
                if (xGrid != null) {
                    g.setColor(xGrid);
                    g.fillRect(loopTimeX * 25, GAME_HEIGHT - (loopTimeY + 1) * 25, 25, 25);
                }
                loopTimeX++;
            }
            loopTimeY++;
        }
    }
}
