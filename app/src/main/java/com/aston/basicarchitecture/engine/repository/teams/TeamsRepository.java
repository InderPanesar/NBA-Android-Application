package com.aston.basicarchitecture.engine.repository.teams;

import com.aston.basicarchitecture.engine.model.teams.TeamsModel;
import retrofit2.Call;

public interface TeamsRepository {
    Call<TeamsModel> getTeams(String conference);
    Call<TeamsModel> getTeam(String teamId);

}
