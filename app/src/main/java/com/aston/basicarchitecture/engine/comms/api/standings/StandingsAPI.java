package com.aston.basicarchitecture.engine.comms.api.standings;

import com.aston.basicarchitecture.engine.model.standings.StandingsModel;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface StandingsAPI {
    @GET("/standings/standard/2021/")
    Call<StandingsModel> getStandings();

    @GET("standings/standard/2021/teamId/{teamId}/")
    Call<StandingsModel> getStandingForTeam(@Path("teamId") String teamId);
}
