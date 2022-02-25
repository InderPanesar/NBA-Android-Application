package com.aston.basketballapp.engine.repository.teams;

import com.aston.basketballapp.engine.comms.api.teams.TeamsAPI;
import com.aston.basketballapp.engine.model.teams.TeamsModelAPI;

import retrofit2.Call;
import retrofit2.Retrofit;

//Definition to make calls to get TeamsRepositoryAPI
public class TeamsRepositoryImpl implements TeamsRepository {

    Retrofit retrofit;

    public TeamsRepositoryImpl(Retrofit _retrofit) {
        retrofit = _retrofit;
    }

    @Override
    public Call<TeamsModelAPI> getTeams(String conference) {
        TeamsAPI api = retrofit.create(TeamsAPI.class);
        return api.getTeams(conference);
    }

    @Override
    public Call<TeamsModelAPI> getTeam(String teamId) {
        TeamsAPI api = retrofit.create(TeamsAPI.class);
        return api.getTeam(teamId);
    }

}
