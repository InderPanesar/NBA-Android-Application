package com.aston.basicarchitecture.engine.model.schedule.game;

import java.util.List;

public class GameTeam {
    String fullName;
    String teamId;
    String nickname;
    String logo;
    String shortName;
    String allStar;
    String nbaFranchise;
    Score score;
    List<Leaders> leaders;

    public String getFullName() {
        return fullName;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLogo() {
        return logo;
    }

    public String getShortName() {
        return shortName;
    }

    public String getAllStar() {
        return allStar;
    }

    public String getNbaFranchise() {
        return nbaFranchise;
    }

    public Score getScore() {
        return score;
    }

    public List<Leaders> getLeaders() {
        return leaders;
    }
}
