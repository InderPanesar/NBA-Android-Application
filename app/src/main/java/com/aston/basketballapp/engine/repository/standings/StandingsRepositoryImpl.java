package com.aston.basketballapp.engine.repository.standings;


import com.aston.basketballapp.engine.comms.api.standings.StandingsAPI;
import com.aston.basketballapp.engine.model.standings.StandingsModelApi;

import retrofit2.Call;
import retrofit2.Retrofit;

//Definition to make calls to get StandingsAPI
public class StandingsRepositoryImpl implements StandingsRepository {

    Retrofit retrofit;

    public StandingsRepositoryImpl(Retrofit _retrofit) {
        retrofit = _retrofit;
    }

    @Override
    public Call<StandingsModelApi> getStandings() {
        StandingsAPI api = retrofit.create(StandingsAPI.class);
        return api.getStandings("standard", "2021");
    }

    @Override
    public Call<StandingsModelApi> getSpecificTeamStandings(String teamId) {
        StandingsAPI api = retrofit.create(StandingsAPI.class);
        return api.getStandingForTeam("standard", "2021", teamId);
    }
}
