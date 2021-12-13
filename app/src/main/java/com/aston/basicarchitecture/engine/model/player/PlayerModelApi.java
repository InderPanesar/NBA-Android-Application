package com.aston.basicarchitecture.engine.model.player;

import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;

import java.util.ArrayList;

public class PlayerModelApi {
    int status;
    String message;
    int results;
    ArrayList<String> filters = new ArrayList<>();
    ArrayList<IndividualPlayerModel> players = new ArrayList<>();

    public float getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public float getResults() {
        return results;
    }

    public ArrayList<IndividualPlayerModel> getPlayers() {
        return players;
    }
}
