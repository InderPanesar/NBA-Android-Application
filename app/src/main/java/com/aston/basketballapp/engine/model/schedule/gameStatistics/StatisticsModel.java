package com.aston.basketballapp.engine.model.schedule.gameStatistics;

import java.util.List;

public class StatisticsModel {

    GameStatisticsTeam team;
    List<GameStatisticsAttributes> statistics;

    public String getTeamId() {
        return team.id + "";
    }

    public String getFastBreakPoints() {
        return statistics.get(0).fastBreakPoints + "";
    }

    public String getPointsInPaint() {
        return statistics.get(0).pointsInPaint+ "";
    }

    public String getBiggestLead() {
        return statistics.get(0).biggestLead+ "";
    }

    public String getSecondChancePoints() {
        return statistics.get(0).secondChancePoints+ "";
    }

    public String getPointsOffTurnovers() { return statistics.get(0).pointsOffTurnovers+ ""; }

    public String getLongestRun() {
        return statistics.get(0).longestRun+ "";
    }

    public String getPoints() {
        return statistics.get(0).points+ "";
    }

    public String getFgm() {
        return statistics.get(0).fgm+ "";
    }

    public String getFga() {
        return statistics.get(0).fga+ "";
    }

    public String getFtm() {
        return statistics.get(0).ftm+ "";
    }

    public String getFta() {
        return statistics.get(0).fta+ "";
    }

    public String getFtp() {
        return statistics.get(0).ftp;
    }

    public String getTpm() {
        return statistics.get(0).tpm+ "";
    }

    public String getTpa() {
        return statistics.get(0).tpa+ "";
    }

    public String getTpp() {
        return statistics.get(0).tpp;
    }

    public String getOffReb() {
        return statistics.get(0).offReb+ "";
    }

    public String getDefReb() {
        return statistics.get(0).defReb+ "";
    }

    public String getTotReb() {
        return statistics.get(0).totReb+ "";
    }

    public String getAssists() {
        return statistics.get(0).assists+ "";
    }

    public String getpFouls() {
        return statistics.get(0).pFouls+ "";
    }

    public String getSteals() {
        return statistics.get(0).steals+ "";
    }

    public String getTurnovers() {
        return statistics.get(0).turnovers+ "";
    }

    public String getBlocks() {
        return statistics.get(0).blocks+ "";
    }

    public String getPlusMinus() {
        return statistics.get(0).plusMinus;
    }

    public String getMin() {
        return statistics.get(0).min;
    }
}
