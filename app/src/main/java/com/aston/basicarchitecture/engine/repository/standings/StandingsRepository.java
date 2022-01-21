package com.aston.basicarchitecture.engine.repository.standings;

import com.aston.basicarchitecture.engine.model.standings.StandingsModel;

import retrofit2.Call;

public interface StandingsRepository {
    Call<StandingsModel> getStandings();
    Call<StandingsModel> getSpecificTeamStandings(String teamId);

}
