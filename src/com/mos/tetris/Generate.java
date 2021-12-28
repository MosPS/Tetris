package com.mos.tetris;

import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class Generate {
    int[] xpoints;
    int[] ypoints;
    double centerX;
    double centerY;
    Color color;

    Generate(int x, int BLOCK_WIDTH) {
        Random random = new Random();
        switch (random.nextInt(7)) {
            case 0: // O
                xpoints = new int[] {
                        x, x + BLOCK_WIDTH * 2, x + BLOCK_WIDTH * 2, x
                };
                ypoints = new int[] {
                        25, 25, 75, 75
                };
                centerX = 175;
                centerY = 50;
                color = Color.yellow;
                break;
            case 1: // I
                xpoints = new int[] {
                        x - 50, x + BLOCK_WIDTH * 4 - 50, x + BLOCK_WIDTH * 4 - 50, x - 50
                };
                ypoints = new int[] {
                        25, 25, 50, 50
                };
                centerX = 150;
                centerY = 25;
                color = Color.cyan;
                break;
            case 2: // J
                xpoints = new int[] {
                        x - 25, x + BLOCK_WIDTH - 25, x + BLOCK_WIDTH - 25, x + BLOCK_WIDTH * 3 - 25, x + BLOCK_WIDTH * 3 - 25, x - 25
                };
                ypoints = new int[] {
                        25, 25, 50, 50, 75, 75
                };
                centerX = 150;
                centerY = 50;
                color = Color.blue;
                break;
            case 3: // L
                xpoints = new int[] {
                        x + 25, x + BLOCK_WIDTH + 25, x + BLOCK_WIDTH + 25, x - BLOCK_WIDTH * 2 + 25, x - BLOCK_WIDTH * 2  + 25, x + 25
                };
                ypoints = new int[] {
                        25, 25, 75, 75, 50, 50
                };
                centerX = 175;
                centerY = 50;
                color = Color.orange;
                break;
            case 4: // T
                xpoints = new int[] {
                        x, x + BLOCK_WIDTH, x + BLOCK_WIDTH, x + BLOCK_WIDTH * 2, x + BLOCK_WIDTH * 2, x - BLOCK_WIDTH, x - BLOCK_WIDTH, x
                };
                ypoints = new int[] {
                        25, 25, 50, 50, 75, 75, 50, 50
                };
                centerX = 150;
                centerY = 50;
                color = Color.MAGENTA;
                break;
            case 5: // S
                xpoints = new int[] {
                        x, x + BLOCK_WIDTH * 2, x + BLOCK_WIDTH * 2, x + BLOCK_WIDTH, x + BLOCK_WIDTH, x - BLOCK_WIDTH, x - BLOCK_WIDTH, x
                };
                ypoints = new int[] {
                        25, 25, 50, 50, 75, 75, 50, 50
                };
                centerX = 150;
                centerY = 50;
                color = Color.green;
                break;
            case 6: // Z
                xpoints = new int[] {
                        x - 25, x + BLOCK_WIDTH * 2 - 25, x + BLOCK_WIDTH * 2 - 25, x + BLOCK_WIDTH * 3 - 25, x + BLOCK_WIDTH * 3 - 25, x + BLOCK_WIDTH - 25, x + BLOCK_WIDTH - 25, x - 25
                };
                ypoints = new int[] {
                        25, 25, 50, 50, 75, 75, 50, 50
                };
                centerX = 150;
                centerY = 50;
                color = Color.red;
                break;
        }
    }
}
