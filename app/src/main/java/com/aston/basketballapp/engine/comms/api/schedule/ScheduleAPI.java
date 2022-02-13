package com.aston.basketballapp.engine.comms.api.schedule;

import com.aston.basketballapp.engine.model.schedule.ScheduleModel;
import com.aston.basketballapp.engine.model.schedule.game.GameModel;
import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Contains all the Api methods to make calls to get schedule specific information
public interface ScheduleAPI {

    //Get all games for a specific date
    @GET("games/date/{date}/")
    Call<ScheduleModel> getGames(@Path("date") String date);

    //Get details for a specific game
    @GET("gameDetails/{gameId}/")
    Call<GameModel> getGameDetails(@Path("gameId") String gameId);

    //Get statistics for a specific game
    @GET("/statistics/games/gameId/{gameId}/")
    Call<GameStatisticModel> getGameStatistics(@Path("gameId") String gameId);
}
