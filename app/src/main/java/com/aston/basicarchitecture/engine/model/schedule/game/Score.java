package com.aston.basicarchitecture.engine.model.schedule.game;

import java.util.List;

public class Score {
    String win;
    String loss;
    String seriesWin;
    String seriesLoss;
    List<String> lineScore;
    String points;

    public String getWin() {
        return win;
    }

    public String getLoss() {
        return loss;
    }

    public String getSeriesWin() {
        return seriesWin;
    }

    public String getSeriesLoss() {
        return seriesLoss;
    }

    public List<String> getLineScore() {
        return lineScore;
    }

    public String getPoints() {
        return points;
    }
}
