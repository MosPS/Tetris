package com.mos.tetris;

import  java.awt.*;
import  java.awt.event.*;
import  java.util.*;
import javax.swing.*;

public class Block{
    int xVelocity;
    int yVelocity;
    int speedNormal = 1;
    int speedBoost = 25;
    boolean wait = false;
    double angle = 90 * Math.PI / 180;
    Polygon polygon;
    Generate generate;
    Graphics2D g2;

    Block(int GAME_WIDTH, int GAME_HEIGHT, int BLOCK_WIDTH) {
        generate = new Generate(GAME_WIDTH / 2, BLOCK_WIDTH);
        polygon = new Polygon(generate.xpoints, generate.ypoints, generate.xpoints.length);
        yVelocity = speedNormal;
    }

    public void gameOver() {
        polygon = new Polygon();
    }

    public void draw(Graphics g) {
        g.setColor(generate.color);
        g2 = (Graphics2D)g;
        g2.fill(polygon);
    }

    public void down() {
        if (wait == false) {
            polygon.translate(0, yVelocity);
            generate.centerY += yVelocity;
        }
    }

    public void rotate() {
        wait = true; //必要か確認
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        int loopTime = 0;
        for (int x: polygon.xpoints) {
            polygon.xpoints[loopTime] = (int) Math.round((generate.centerX + ((double) (x) - generate.centerX) * cosAngle - ((double) (polygon.ypoints[loopTime]) - generate.centerY) * sinAngle));
            polygon.ypoints[loopTime] = (int) Math.round((generate.centerY + ((double) (polygon.ypoints[loopTime]) - generate.centerY) * cosAngle + ((double) (x) - generate.centerX) * sinAngle));
            loopTime++;
        }
        polygon = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
        wait = false;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (wait == false) {
                xVelocity = -25;
                polygon.translate(xVelocity, 0);
                generate.centerX += xVelocity;
            }
            if (Falled.checkIntersects(this)) {
                polygon.translate(-xVelocity, 0);
                generate.centerX -= xVelocity;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (wait == false) {
                xVelocity = 25;
                polygon.translate(xVelocity, 0);
                generate.centerX += xVelocity;
            }
            if (Falled.checkIntersects(this)) {
                polygon.translate(-xVelocity, 0);
                generate.centerX -= xVelocity;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (wait == false) {
                yVelocity = speedBoost;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (wait == false) {
                rotate();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (wait == false) {
                yVelocity = speedNormal;
            }
        }
    }
}
