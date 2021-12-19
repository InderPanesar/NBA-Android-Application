package com.aston.basicarchitecture.engine.model.teams;

public class IndividualTeamsModel {
    String city;
    String fullName;
    String teamId;
    String nickname;
    String logo;
    String shortName;
    String allStar;
    String nbaFranchise;
    LeaguesModel leagues;

    public String getCity() { return city; }
    public String getFullName() { return fullName; }
    public String getTeamId() { return teamId; }
    public String getNickname() { return nickname; }
    public String getLogo() { return logo; }
    public String getShortName() { return shortName; }
    public String getAllStars() { return allStar; }
    public String getNbaFranchise() { return nbaFranchise; }
    public LeaguesModel getLeagues() { return leagues; }

}
