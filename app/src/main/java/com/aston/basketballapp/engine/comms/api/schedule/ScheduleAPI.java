package com.aston.basketballapp.engine.comms.api.schedule;

import com.aston.basketballapp.engine.model.schedule.ScheduleModel;
import com.aston.basketballapp.engine.model.schedule.game.GameModel;
import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ScheduleAPI {

    @GET("games/date/{date}/")
    Call<ScheduleModel> getGames(@Path("date") String date);

    @GET("gameDetails/{gameId}/")
    Call<GameModel> getGameDetails(@Path("gameId") String gameId);

    @GET("/statistics/games/gameId/{gameId}/")
    Call<GameStatisticModel> getGameStatistics(@Path("gameId") String gameId);
}
