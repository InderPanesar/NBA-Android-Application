package com.aston.basketballapp.engine.model.schedule.game;

import java.util.List;

public class Game {

    String seasonYear;
    String league;
    String gameId;
    String startTimeUTC;
    String endTimeUTC;
    String arena;
    String city;
    String country;
    String clock;
    String gameDuration;
    String timesTied;
    String leadChanges;
    String currentPeriod;
    String halftime;
    String endOfPeriod;
    String seasonStage;
    String statusShortGame;
    String statusGame;
    GameTeam vTeam;
    GameTeam hTeam;
    List<Officials> officials;

    public String getSeasonYear() {
        return seasonYear;
    }

    public String getLeague() {
        return league;
    }

    public String getGameId() {
        return gameId;
    }

    public String getStartTimeUTC() {
        return startTimeUTC;
    }

    public String getEndTimeUTC() {
        return endTimeUTC;
    }

    public String getArena() {
        return arena;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getClock() {
        return clock;
    }

    public String getGameDuration() {
        return gameDuration;
    }

    public String getTimesTied() {
        return timesTied;
    }

    public String getLeadChanges() {
        return leadChanges;
    }

    public String getCurrentPeriod() {
        return currentPeriod;
    }

    public String getHalftime() {
        return halftime;
    }

    public String getEndOfPeriod() {
        return endOfPeriod;
    }

    public String getSeasonStage() {
        return seasonStage;
    }

    public String getStatusShortGame() {
        return statusShortGame;
    }

    public String getStatusGame() {
        return statusGame;
    }

    public GameTeam getvTeam() {
        return vTeam;
    }

    public GameTeam gethTeam() {
        return hTeam;
    }

    public List<Officials> getOfficials() {
        return officials;
    }
}
