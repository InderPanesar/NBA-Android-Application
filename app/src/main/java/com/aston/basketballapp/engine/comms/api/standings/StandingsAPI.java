package com.aston.basketballapp.engine.comms.api.standings;

import com.aston.basketballapp.engine.model.standings.StandingsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Contains all the Api methods to make calls to get current NBA standings
public interface StandingsAPI {
    //Get The Standings information for the current NBA Season.
    @GET("/standings/standard/2021/")
    Call<StandingsModel> getStandings();

    //Get the Standings information for a specific team
    @GET("standings/standard/2021/teamId/{teamId}/")
    Call<StandingsModel> getStandingForTeam(@Path("teamId") String teamId);
}
