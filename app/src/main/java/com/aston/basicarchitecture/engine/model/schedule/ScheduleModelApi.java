package com.aston.basicarchitecture.engine.model.schedule;

import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;

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
