package com.aston.basketballapp.engine.repository.standings;


import com.aston.basketballapp.engine.comms.api.standings.StandingsAPI;
import com.aston.basketballapp.engine.model.standings.StandingsModel;

import retrofit2.Call;
import retrofit2.Retrofit;

public class StandingsRepositoryImpl implements StandingsRepository {

    Retrofit retrofit;

    public StandingsRepositoryImpl(Retrofit _retrofit) {
        retrofit = _retrofit;
    }

    @Override
    public Call<StandingsModel> getStandings() {
        StandingsAPI api = retrofit.create(StandingsAPI.class);
        return api.getStandings();
    }

    @Override
    public Call<StandingsModel> getSpecificTeamStandings(String teamId) {
        StandingsAPI api = retrofit.create(StandingsAPI.class);
        return api.getStandingForTeam(teamId);
    }
}
