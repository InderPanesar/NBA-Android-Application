package com.aston.basicarchitecture.engine.repository.schedule;

import com.aston.basicarchitecture.engine.model.schedule.GamesModel;
import com.aston.basicarchitecture.engine.model.schedule.ScheduleModel;
import com.aston.basicarchitecture.engine.model.schedule.game.GameModel;
import com.aston.basicarchitecture.engine.model.schedule.gameStatistics.GameStatisticModel;
import com.aston.basicarchitecture.engine.model.schedule.gameStatistics.GameStatisticModelAPI;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;

import java.util.List;

import retrofit2.Call;

public interface ScheduleRepository {
    Call<ScheduleModel> getGames(String conference);
    Call<GameModel> getGameDetails(String gameId);
    Call<GameStatisticModel> getGameStatisticDetails(String gameId);

}
