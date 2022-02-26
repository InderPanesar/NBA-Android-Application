package com.aston.basketballapp.engine.repository.standings;

import com.aston.basketballapp.engine.model.standings.StandingsModelApi;

import retrofit2.Call;

//Standings Repository to make calls to the API
public interface StandingsRepository {
    Call<StandingsModelApi> getStandings();
    Call<StandingsModelApi> getSpecificTeamStandings(String teamId);

}
