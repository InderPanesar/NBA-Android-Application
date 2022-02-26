package com.aston.basketballapp.engine.model.standings;

import com.aston.basketballapp.engine.repository.standings.StandingsRepository;

import java.util.List;

public class StandingsModelApi {

    String get;
    int results;
    List<TeamStandingModel> response;

    public String getMessage() {
        return get;
    }

    public int getResults() {
        return results;
    }

    public List<TeamStandingModel> getStandings() {
        return response;
    }
}
