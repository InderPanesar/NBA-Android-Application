package com.aston.basicarchitecture.engine.comms.api.standings;

import com.aston.basicarchitecture.engine.model.standings.StandingsModel;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface StandingsAPI {
    @Headers({"x-rapidapi-host:api-nba-v1.p.rapidapi.com", "x-rapidapi-key:5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424"})
    @GET("/standings/standard/2021/")
    Call<StandingsModel> getStandings();
}
