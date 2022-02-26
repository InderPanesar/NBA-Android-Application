package com.aston.basketballapp.engine.model.schedule.gameStatistics;

import java.util.List;

public class GameStatisticModelAPI {
    String get;
    int results;
    List<StatisticsModel> response;

    public String getMessage() {
        return get;
    }

    public int getResults() {
        return results;
    }

    public List<StatisticsModel> getStatistics() {
        return response;
    }
}
