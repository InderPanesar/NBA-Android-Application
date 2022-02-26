package com.aston.basketballapp.engine.model.standings;
public class TeamStandingConference {
    String name;
    int rank;
    int win;
    int loss;

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank + "";
    }

    public String getWin() {
        return win + "";
    }

    public String getLoss() {
        return loss + "";
    }
}
