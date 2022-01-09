package com.aston.basicarchitecture.engine.comms.api.schedule;

import com.aston.basicarchitecture.engine.model.schedule.ScheduleModel;
import com.aston.basicarchitecture.engine.model.schedule.game.GameModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ScheduleAPI {

    @Headers({"x-rapidapi-host:api-nba-v1.p.rapidapi.com", "x-rapidapi-key:5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424"})
    @GET("games/date/{date}/")
    Call<ScheduleModel> getGames(@Path("date") String date);

    @Headers({"x-rapidapi-host:api-nba-v1.p.rapidapi.com", "x-rapidapi-key:5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424"})
    @GET("gameDetails/{gameId}/")
    Call<GameModel> getGameDetails(@Path("gameId") String gameId);
}
