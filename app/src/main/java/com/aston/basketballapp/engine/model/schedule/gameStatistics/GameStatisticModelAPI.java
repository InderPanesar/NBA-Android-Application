package com.aston.basketballapp.engine.model.schedule.gameStatistics;

import java.util.List;

public class GameStatisticModelAPI {
    int status;
    String message;
    int results;
    List<String> filters;
    List<StatisticsModel> statistics;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getResults() {
        return results;
    }

    public List<String> getFilters() {
        return filters;
    }

    public List<StatisticsModel> getStatistics() {
        return statistics;
    }
}
