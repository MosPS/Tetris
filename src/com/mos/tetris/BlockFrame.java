package com.mos.tetris;

import  java.awt.*;
import  java.awt.event.*;
import java.awt.font.GraphicAttribute;
import  java.util.*;
import javax.swing.*;

public class BlockFrame {
    Polygon polygon;
    int[] xpoints;
    int[] ypoints;
    Graphics2D g2;

    BlockFrame(int GAME_WIDTH, int GAME_HEIGHT, int BLOCK_WIDTH) {
        xpoints = new int[] {
                0, GAME_WIDTH, GAME_WIDTH, 0, 0, BLOCK_WIDTH, GAME_WIDTH - BLOCK_WIDTH, GAME_WIDTH - BLOCK_WIDTH, BLOCK_WIDTH, BLOCK_WIDTH
        };
        ypoints = new int[] {
                0, 0, GAME_HEIGHT, GAME_HEIGHT, 0, BLOCK_WIDTH, BLOCK_WIDTH, GAME_HEIGHT - BLOCK_WIDTH, GAME_HEIGHT - BLOCK_WIDTH, BLOCK_WIDTH
        };

        polygon = new Polygon(xpoints, ypoints, xpoints.length);
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g2 = (Graphics2D) g;
        g2.fill(polygon);
    }
}
