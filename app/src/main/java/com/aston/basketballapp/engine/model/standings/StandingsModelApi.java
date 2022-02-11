package com.aston.basketballapp.engine.model.standings;

import java.util.List;

public class StandingsModelApi {

    int status;
    String message;
    int results;
    List<String> filters;
    List<TeamStandingModel> standings;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getResults() {
        return results;
    }

    public List<String> getFilters() {
        return filters;
    }

    public List<TeamStandingModel> getStandings() {
        return standings;
    }
}
