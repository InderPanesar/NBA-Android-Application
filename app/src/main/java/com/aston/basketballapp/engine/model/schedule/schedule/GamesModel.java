package com.aston.basketballapp.engine.model.schedule.schedule;

import java.util.Date;

public class GamesModel {

    int id;
    ScheduleDateModel date;
    GameStatus status;
    SchedulePeriodModel periods;
    ScheduleModelArena arena;
    ScheduleTeamsModel teams;
    ScheduleTeamsScoreModel scores;



    public String getGameId() { return id + ""; }
    public String getStartTimeUTC() { return date.start; }
    public String getCurrentPeriod() { return periods.current + ""; }
    public boolean isDone() { return periods.endOfPeriod; }

    public String getHalftime() {
        if(status.halftime) {
            return "1";
        }
        return "0";
    }
    public String getStatusGame() { return status.status; }
    public TeamScheduleModel getvTeam() { return teams.visitors; }
    public String getvTeamScore() { return scores.visitors.points + ""; }
    public TeamScheduleModel gethTeam() { return teams.home; }
    public String gethTeamScore() { return scores.home.points + ""; }

}
