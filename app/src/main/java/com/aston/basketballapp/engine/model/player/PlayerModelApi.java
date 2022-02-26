package com.aston.basketballapp.engine.model.player;

import com.aston.basketballapp.engine.model.teams.IndividualTeamsModel;

import java.util.ArrayList;

public class PlayerModelApi {

    String get;
    int results;
    ArrayList<IndividualPlayerModel> response;

    public String getMessage() {
        return get;
    }

    public int getResults() {
        return results;
    }

    public ArrayList<IndividualPlayerModel> getPlayers() {
        return response;
    }

}
