package com.example.albiferenc.dartscounterold;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Albi Ferenc on 2017.02.03..
 */
class Player {
    Player(int score){
        this.score = score;
        this.average = 0;
        scores = new ArrayList<Integer>();
    }

    private int score;      // Score at the moment
    private List<Integer> scores;    // Previous throws
    private List<Integer> checkOuts;  // List of possible checkouts
    private double average;    // Average
    private boolean isActive;   // Boolean to decide which player is active
    private boolean isDisabled;

    double calculateAverage(int round) {
        int tmp = 0;
        for (int value : this.scores) {
            tmp += value;
        }
        this.average = (double) tmp / round;
        return this.average;
    }

    int calculateScore(int currentThrow) {
        if(this.score - currentThrow < 0) {
            return Constants.BUST;
        } else if(this.score - currentThrow == 0) {
            return  Constants.WIN;
        } else {
            return this.score;
        }
    }

    void substractScore(int currentThrow) {
        this.score -= currentThrow;
    }

// Getter and setter methods
int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    String getStrScore() {
        return String.valueOf(getScore());
    }

    List getScores() {
        return scores;
    }

    void setScores(List scores) {
        this.scores = scores;
    }

    String getStrScores() {
        StringBuilder stringBuilder = new StringBuilder();
        int cntr = 0;   // Temporary counter for deciding if we need to add "," after the elements
        if(null != this.scores && !this.scores.isEmpty()) {
            for (int val : this.scores) {
                String temp = String.valueOf(val);
                stringBuilder.append(temp);
                if(cntr != this.scores.size()-1) {
                    stringBuilder.append(" , ");
                }
                cntr++;
            }
        }
        return stringBuilder.toString();
    }

    List getCheckOuts() {
        return checkOuts;
    }

    void setCheckOuts(List checkOuts) {
        this.checkOuts = checkOuts;
    }

    double getAverage() {
        return average;
    }

    void setAverage(double average) {
        this.average = average;
    }

    boolean isActive() {
        return isActive;
    }

    void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    boolean isDisabled() {
        return isDisabled;
    }

    void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
