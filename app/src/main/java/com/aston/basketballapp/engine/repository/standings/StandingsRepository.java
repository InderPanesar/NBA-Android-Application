package com.aston.basketballapp.engine.repository.standings;

import com.aston.basketballapp.engine.model.standings.StandingsModel;

import retrofit2.Call;

public interface StandingsRepository {
    Call<StandingsModel> getStandings();
    Call<StandingsModel> getSpecificTeamStandings(String teamId);

}
