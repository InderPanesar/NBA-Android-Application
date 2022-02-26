package com.aston.basketballapp.engine.model.teams;
public class IndividualTeamsModel {
    int id;
    String name;
    String nickname;
    String code;
    String city;
    String logo;
    boolean allStar;
    boolean nbaFranchise;
    TeamsLeagueModel leagues;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getLogo() {
        if(logo == null) {
            return "";
        }
        return logo;
    }

    public boolean isAllStar() {
        return allStar;
    }

    public boolean isNbaFranchise() {
        return nbaFranchise;
    }

    public TeamsLeagueModel getLeagues() {
        return leagues;
    }
}
