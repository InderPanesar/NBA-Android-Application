package com.aston.basicarchitecture.engine.comms.api.teams;

import com.aston.basicarchitecture.engine.model.ExampleModel;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface TeamsAPI {
    @GET("teams/confName/{conference}/")
    Call<TeamsModel> getTeams(@Path("conference") String conference);

    @GET("teams/teamId/{teamId}/")
    Call<TeamsModel> getTeam(@Path("teamId") String teamId);
}
