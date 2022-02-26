package com.aston.basketballapp.engine.model.standings;
public class TeamStandingModel {
    String league;
    int season;
    StandingTeamModel team;
    TeamStandingConference conference;
    TeamStandingDivision division;
    TeamStandingWin win;
    TeamStandingWin loss;
    String gamesBehind;
    int streak;
    boolean winStreak;
    String tieBreakerPoints;

    public String getLeague() {
        return league;
    }

    public int getSeason() {
        return season;
    }

    public StandingTeamModel getTeam() {
        return team;
    }

    public TeamStandingConference getConference() {
        return conference;
    }

    public TeamStandingDivision getDivision() {
        return division;
    }

    public TeamStandingWin getWin() {
        return win;
    }

    public TeamStandingWin getLoss() {
        return loss;
    }

    public String getGamesBehind() {
        return gamesBehind;
    }

    public int getStreak() {
        return streak;
    }

    public boolean isWinStreak() {
        return winStreak;
    }
}
