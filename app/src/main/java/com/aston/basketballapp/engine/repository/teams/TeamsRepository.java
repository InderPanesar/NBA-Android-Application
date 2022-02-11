package com.aston.basketballapp.engine.repository.teams;

import com.aston.basketballapp.engine.model.teams.TeamsModel;
import retrofit2.Call;

public interface TeamsRepository {
    Call<TeamsModel> getTeams(String conference);
    Call<TeamsModel> getTeam(String teamId);

}
