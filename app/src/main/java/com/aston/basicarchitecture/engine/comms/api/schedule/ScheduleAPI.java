package com.aston.basicarchitecture.engine.comms.api.schedule;

import com.aston.basicarchitecture.engine.model.schedule.ScheduleModel;
import com.aston.basicarchitecture.engine.model.schedule.game.GameModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ScheduleAPI {

    @GET("games/date/{date}/")
    Call<ScheduleModel> getGames(@Path("date") String date);

    @GET("gameDetails/{gameId}/")
    Call<GameModel> getGameDetails(@Path("gameId") String gameId);
}
