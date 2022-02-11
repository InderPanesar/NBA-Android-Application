package com.aston.basketballapp.engine.model.teams;

import java.util.ArrayList;

public class TeamsModelAPI {

    int status;
    String message;
    int results;
    ArrayList<String> filters = new ArrayList<String>();;
    ArrayList<IndividualTeamsModel> teams = new ArrayList<IndividualTeamsModel>();

    public float getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public float getResults() {
        return results;
    }

    public ArrayList<IndividualTeamsModel> getTeams() {
        return teams;
    }
}
