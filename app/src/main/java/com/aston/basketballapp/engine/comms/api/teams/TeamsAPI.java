package com.aston.basketballapp.engine.comms.api.teams;

import com.aston.basketballapp.engine.model.teams.TeamsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TeamsAPI {
    @GET("teams/confName/{conference}/")
    Call<TeamsModel> getTeams(@Path("conference") String conference);

    @GET("teams/teamId/{teamId}/")
    Call<TeamsModel> getTeam(@Path("teamId") String teamId);
}
