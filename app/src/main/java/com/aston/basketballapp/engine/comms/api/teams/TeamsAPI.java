package com.aston.basketballapp.engine.comms.api.teams;

import com.aston.basketballapp.engine.model.teams.TeamsModelAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//Contains all the Api methods to make calls to get current Teams
public interface TeamsAPI {
    //Get Teams information within a specific conference
    @GET("teams")
    Call<TeamsModelAPI> getTeams(@Query("conference") String conference);

    //Get specific Teams information
    @GET("teams")
    Call<TeamsModelAPI> getTeam(@Path("id") String teamId);
}
