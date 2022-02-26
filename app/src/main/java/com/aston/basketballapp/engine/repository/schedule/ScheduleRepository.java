package com.aston.basketballapp.engine.repository.schedule;

import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModelAPI;
import com.aston.basketballapp.engine.model.schedule.schedule.ScheduleModelApi;

import retrofit2.Call;

//Schedule Repository to make calls to the API
public interface ScheduleRepository {
    Call<ScheduleModelApi> getGames(String date);
    Call<GameStatisticModelAPI> getGameStatisticDetails(String gameId);

}
