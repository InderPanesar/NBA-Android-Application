package com.aston.basketballapp.engine.comms.api.standings;

import com.aston.basketballapp.engine.model.standings.StandingsModelApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//Contains all the Api methods to make calls to get current NBA standings
public interface StandingsAPI {
    //Get The Standings information for the current NBA Season.
    @GET("/standings")
    Call<StandingsModelApi> getStandings(@Query("league") String league, @Query("season") String year);

    //Get the Standings information for a specific team
    @GET("standings")
    Call<StandingsModelApi> getStandingForTeam(@Query("league") String league, @Query("season") String year, @Query("team") String teamId);
}
