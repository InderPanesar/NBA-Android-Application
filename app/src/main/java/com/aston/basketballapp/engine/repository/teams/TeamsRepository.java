package com.aston.basketballapp.engine.repository.teams;

import com.aston.basketballapp.engine.model.teams.TeamsModelAPI;

import retrofit2.Call;

//Teams Repository to make calls to the API
public interface TeamsRepository {
    Call<TeamsModelAPI> getTeams(String conference);
    Call<TeamsModelAPI> getTeam(String teamId);

}
