package com.aston.basicarchitecture.engine.model.schedule.game;

import java.util.List;

public class GameModelApi {

    int status;
    String message;
    int results;
    List<String> filters;
    List<Game> game;

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

    public List<Game> getGame() {
        return game;
    }
}
