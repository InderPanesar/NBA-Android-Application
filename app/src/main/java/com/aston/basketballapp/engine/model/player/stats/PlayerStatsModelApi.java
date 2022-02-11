package com.aston.basketballapp.engine.model.player.stats;

import java.util.ArrayList;

public class PlayerStatsModelApi {

    int status;
    String message;
    int results;
    ArrayList<String> filters = new ArrayList<>();
    ArrayList<PlayerStatistics> statistics = new ArrayList<>();

    public float getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public float getResults() {
        return results;
    }

    public ArrayList<PlayerStatistics> getStatistics() {
        return statistics;
    }
}
