package com.mos.tetris;

import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {
    static JLabel labelTime;
    static JLabel labelScore;
    static JLabel labelLine;
    static int timeTick = 0;
    static int timeSecond = 0;
    static int timeMinute = 0;
    static int timeHour = 0;
    static int score = 0;
    static int line = 0;

    Score() {
        Dimension SCREEN_SIZE = new Dimension(100, 200);
        setVisible(true);
        setFocusable(false);
        setPreferredSize(SCREEN_SIZE);
        labelTime = new JLabel(String.valueOf(timeHour / 10) + String.valueOf(timeHour % 10) + ":" + String.valueOf(timeMinute / 10) + String.valueOf(timeMinute % 10) + ":" + String.valueOf(timeSecond / 10) + String.valueOf(timeSecond % 10));
        labelScore = new JLabel("スコア: " + String.valueOf(score));
        labelLine = new JLabel("ライン数: " + String.valueOf(line));
        add(labelTime);
        add(labelScore);
        //add(labelLine);
    }

    public static void addTime() {
        timeTick++;
        if (timeTick == 1) {
            timeSecond++;
            timeTick = 0;
        }
        if (timeSecond == 60) {
            timeMinute++;
            timeSecond = 0;
        }
        if (timeMinute == 60) {
            timeHour++;
            timeMinute = 0;
        }
        labelTime.setText(String.valueOf(timeHour / 10) + String.valueOf(timeHour % 10) + ":" + String.valueOf(timeMinute / 10) + String.valueOf(timeMinute % 10) + ":" + String.valueOf(timeSecond / 10) + String.valueOf(timeSecond % 10));
    }

    public static void addScore(int score) {
        Score.score += score;
        labelScore.setText(String.valueOf("スコア: " + Score.score));
    }

    public static void addLineScore() {
        line++;
        labelLine.setText(String.valueOf("ライン数: " + String.valueOf(line)));
    }

    public static void gameOver() {
        timeTick = 0;
        timeSecond = 0;
        timeMinute = 0;
        timeHour = 0;
        score = 0;
        line = 0;
        labelTime.setText(String.valueOf(timeHour / 10) + String.valueOf(timeHour % 10) + ":" + String.valueOf(timeMinute / 10) + String.valueOf(timeMinute % 10) + ":" + String.valueOf(timeSecond / 10) + String.valueOf(timeSecond % 10));
        labelScore.setText(String.valueOf("スコア: " + Score.score));
        labelLine.setText(String.valueOf("ライン数: " + String.valueOf(line)));
    }
}
