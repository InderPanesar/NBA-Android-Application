package com.aston.basketballapp.engine.repository.schedule;

import com.aston.basketballapp.engine.model.schedule.ScheduleModel;
import com.aston.basketballapp.engine.model.schedule.game.GameModel;
import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModel;

import retrofit2.Call;

//Schedule Repository to make calls to the API
public interface ScheduleRepository {
    Call<ScheduleModel> getGames(String date);
    Call<GameModel> getGameDetails(String gameId);
    Call<GameStatisticModel> getGameStatisticDetails(String gameId);

}
