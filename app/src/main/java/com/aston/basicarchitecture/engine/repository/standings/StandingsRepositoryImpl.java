package com.aston.basicarchitecture.engine.repository.standings;


import com.aston.basicarchitecture.engine.comms.api.standings.StandingsAPI;
import com.aston.basicarchitecture.engine.model.standings.StandingsModel;

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
}
