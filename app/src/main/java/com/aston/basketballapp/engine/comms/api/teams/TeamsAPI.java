package com.aston.basketballapp.engine.comms.api.teams;

import com.aston.basketballapp.engine.model.teams.TeamsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Contains all the Api methods to make calls to get current Teams
public interface TeamsAPI {
    //Get Teams information within a specific conference
    @GET("teams/confName/{conference}/")
    Call<TeamsModel> getTeams(@Path("conference") String conference);

    //Get specific Teams information
    @GET("teams/teamId/{teamId}/")
    Call<TeamsModel> getTeam(@Path("teamId") String teamId);
}
