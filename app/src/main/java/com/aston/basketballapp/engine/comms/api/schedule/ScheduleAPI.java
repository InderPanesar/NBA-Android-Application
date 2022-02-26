package com.aston.basketballapp.engine.comms.api.schedule;

import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModelAPI;
import com.aston.basketballapp.engine.model.schedule.schedule.ScheduleModelApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//Contains all the Api methods to make calls to get schedule specific information
public interface ScheduleAPI {

    //Get all games for a specific date
    @GET("games")
    Call<ScheduleModelApi> getGames(@Query("date") String date);

    //Get statistics for a specific game
    @GET("games/statistics")
    Call<GameStatisticModelAPI> getGameStatistics(@Query("id") String gameId);
}
