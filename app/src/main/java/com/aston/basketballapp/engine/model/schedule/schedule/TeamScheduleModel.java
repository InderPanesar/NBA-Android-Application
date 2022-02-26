package com.aston.basketballapp.engine.model.schedule.schedule;

import com.google.gson.annotations.SerializedName;

public class TeamScheduleModel {
    @SerializedName("id")
    int teamId;
    String name;
    String nickname;
    String logo;

    public String getTeamId() { return teamId + ""; }
    public String getFullName() { return name; }
    public String getNickName() { return nickname; }
    public String getLogo() { return logo; }
}
