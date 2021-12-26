package com.aston.basicarchitecture.engine.model.schedule;

public class TeamScheduleModel {
    String teamId;
    String shortName;
    String fullName;
    String nickName;
    String logo;
    ScoreModel score;

    public String getTeamId() { return teamId; }
    public String getShortName() { return shortName; }
    public String getFullName() { return fullName; }
    public String getNickName() { return nickName; }
    public String getLogo() { return logo; }
    public ScoreModel getScore() { return score; }
}
