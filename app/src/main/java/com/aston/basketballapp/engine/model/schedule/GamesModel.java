package com.aston.basketballapp.engine.model.schedule;

public class GamesModel {

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
    String currentPeriod;
    String halftime;
    String endOfPeriod;
    String seasonStage;
    String statusShortGame;
    String statusGame;
    TeamScheduleModel vTeam;
    TeamScheduleModel hTeam;

    public String getSeasonYear() { return seasonYear; }
    public String getLeague() { return league; }
    public String getGameId() { return gameId; }
    public String getStartTimeUTC() { return startTimeUTC; }
    public String getEndTimeUTC() { return endTimeUTC; }
    public String getArena() { return arena; }
    public String getCity() { return city; }
    public String getCountry() { return country; }
    public String getClock() { return clock; }
    public String getGameDuration() { return gameDuration; }
    public String getCurrentPeriod() { return currentPeriod; }
    public String getHalftime() { return halftime; }
    public String getEndOfPeriod() { return endOfPeriod; }
    public String getSeasonStage() { return seasonStage; }
    public String getStatusShortGame() { return statusShortGame; }
    public String getStatusGame() { return statusGame; }
    public TeamScheduleModel getvTeam() { return vTeam; }
    public TeamScheduleModel gethTeam() { return hTeam; }
}
