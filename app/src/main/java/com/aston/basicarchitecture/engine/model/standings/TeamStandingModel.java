package com.aston.basicarchitecture.engine.model.standings;

public class TeamStandingModel {

    String league;
    String teamId;
    String win;
    String loss;
    String gamesBehind;
    String lastTenWin;
    String lastTenLoss;
    String streak;
    String seasonYear;
    TeamConferenceModel conference;
    TeamDivisionModel division;
    String winPercentage;
    String lossPercentage;
    TeamDetailsModel home;
    TeamDetailsModel away;
    String winStreak;
    String tieBreakerPoints;

    public String getLeague() {
        return league;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getWin() {
        return win;
    }

    public String getLoss() {
        return loss;
    }

    public String getGamesBehind() {
        return gamesBehind;
    }

    public String getLastTenWin() {
        return lastTenWin;
    }

    public String getLastTenLoss() {
        return lastTenLoss;
    }

    public String getStreak() {
        return streak;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public TeamConferenceModel getConference() {
        return conference;
    }

    public TeamDivisionModel getDivision() {
        return division;
    }

    public String getWinPercentage() {
        return winPercentage;
    }

    public String getLossPercentage() {
        return lossPercentage;
    }

    public TeamDetailsModel getHome() {
        return home;
    }

    public TeamDetailsModel getAway() {
        return away;
    }

    public String getWinStreak() {
        return winStreak;
    }

    public String getTieBreakerPoints() {
        return tieBreakerPoints;
    }
}
