package com.aston.basketballapp.engine.repository.standings;

import com.aston.basketballapp.engine.model.standings.StandingsModel;

import retrofit2.Call;

//Standings Repository to make calls to the API
public interface StandingsRepository {
    Call<StandingsModel> getStandings();
    Call<StandingsModel> getSpecificTeamStandings(String teamId);

}
