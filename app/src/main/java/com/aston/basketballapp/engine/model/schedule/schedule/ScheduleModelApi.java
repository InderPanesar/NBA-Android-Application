package com.aston.basketballapp.engine.model.schedule.schedule;

import java.util.ArrayList;

public class ScheduleModelApi {
    String get;
    int results;
    ArrayList<GamesModel> response;

    public String getMessage() {
        return get;
    }

    public int getResults() {
        return results;
    }

    public ArrayList<GamesModel> getGames() {
        return response;
    }
}
