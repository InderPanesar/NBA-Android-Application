package com.aston.basketballapp.engine.model.player.stats;

import java.util.ArrayList;

public class PlayerStatsModelApi {

    String get;
    int results;
    ArrayList<PlayerStatistics> response = new ArrayList<>();

    public String getMessage() {
        return get;
    }

    public int getResults() {
        return results;
    }

    public ArrayList<PlayerStatistics> getStatistics() {
        return response;
    }
}
