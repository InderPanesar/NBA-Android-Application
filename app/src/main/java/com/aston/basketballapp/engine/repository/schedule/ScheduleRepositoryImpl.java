package com.aston.basketballapp.engine.repository.schedule;

import com.aston.basketballapp.engine.comms.api.schedule.ScheduleAPI;
import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModelAPI;
import com.aston.basketballapp.engine.model.schedule.schedule.ScheduleModelApi;

import retrofit2.Call;
import retrofit2.Retrofit;

//Definition to make calls to get ScheduleAPI
public class ScheduleRepositoryImpl implements ScheduleRepository {

    Retrofit retrofit;

    public ScheduleRepositoryImpl(Retrofit _retrofit) {
        retrofit = _retrofit;
    }

    @Override
    public Call<ScheduleModelApi> getGames(String date) {
        ScheduleAPI api = retrofit.create(ScheduleAPI.class);
        return api.getGames(date);
    }

    @Override
    public Call<GameStatisticModelAPI> getGameStatisticDetails(String gameId) {
        ScheduleAPI api = retrofit.create(ScheduleAPI.class);
        return api.getGameStatistics(gameId);
    }
}
