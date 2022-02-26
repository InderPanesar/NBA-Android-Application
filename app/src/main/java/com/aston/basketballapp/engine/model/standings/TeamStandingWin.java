package com.aston.basketballapp.engine.model.standings;
public class TeamStandingWin {
    int home;
    int away;
    int total;
    String percentage;
    int lastTen;

    public String totalWins() {
        return total + "";
    }
}
