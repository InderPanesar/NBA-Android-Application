package com.aston.basicarchitecture.engine.repository.schedule;

import com.aston.basicarchitecture.engine.model.schedule.GamesModel;
import com.aston.basicarchitecture.engine.model.schedule.ScheduleModel;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;

import java.util.List;

import retrofit2.Call;

public interface ScheduleRepository {
    Call<ScheduleModel> getGames(String conference);
}