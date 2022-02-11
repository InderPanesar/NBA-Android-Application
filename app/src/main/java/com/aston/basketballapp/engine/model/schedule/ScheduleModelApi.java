package com.aston.basketballapp.engine.model.schedule;

import java.util.ArrayList;

public class ScheduleModelApi {
    int status;
    String message;
    int results;
    ArrayList<String> filters = new ArrayList<>();
    ArrayList<GamesModel> games = new ArrayList<>();

    public float getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public float getResults() {
        return results;
    }

    public ArrayList<GamesModel> getGames() {
        return games;
    }
}
