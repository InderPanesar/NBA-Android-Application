package com.aston.basketballapp.engine.model.teams;

import com.aston.basketballapp.engine.model.standings.TeamStandingModel;

import java.util.ArrayList;
import java.util.List;

public class TeamsModelAPI {

    String get;
    int results;
    ArrayList<IndividualTeamsModel> response;

    public String getMessage() {
        return get;
    }

    public int getResults() {
        return results;
    }

    public ArrayList<IndividualTeamsModel> getTeams() {
        return response;
    }




}
