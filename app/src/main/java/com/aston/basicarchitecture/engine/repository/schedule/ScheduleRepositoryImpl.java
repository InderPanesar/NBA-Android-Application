package com.aston.basicarchitecture.engine.repository.schedule;

import com.aston.basicarchitecture.engine.comms.api.schedule.ScheduleAPI;
import com.aston.basicarchitecture.engine.comms.api.teams.TeamsAPI;
import com.aston.basicarchitecture.engine.model.schedule.ScheduleModel;
import com.aston.basicarchitecture.engine.model.schedule.game.GameModel;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ScheduleRepositoryImpl implements ScheduleRepository {

    Retrofit retrofit;

    public ScheduleRepositoryImpl(Retrofit _retrofit) {
        retrofit = _retrofit;
    }

    @Override
    public Call<ScheduleModel> getGames(String date) {
        ScheduleAPI api = retrofit.create(ScheduleAPI.class);
        return api.getGames(date);
    }

    @Override
    public Call<GameModel> getGameDetails(String gameId) {
        ScheduleAPI api = retrofit.create(ScheduleAPI.class);
        return api.getGameDetails(gameId);
    }
}
