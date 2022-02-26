package com.aston.basketballapp.engine.model.player.stats;

public class PlayerStatistics {
    PlayerStatisticsPlayerModel player;
    PlayerStatisticsTeamModel team;
    PlayerStatisticsGameModel game;
    int points = 0;
    String pos = "0";
    String min = "0";
    int fgm = 0;
    int fga = 0;
    String fgp = "0";
    int ftm = 0;
    int fta = 0;
    String ftp = "0";
    int tpm = 0;
    int tpa = 0;
    String tpp = "0";
    int offReb = 0;
    int defReb = 0;
    int totReb = 0;
    int assists = 0;
    int pFouls = 0;
    int steals = 0;
    int turnovers = 0;
    int blocks = 0;
    String plusMinus = "0";

    public String getGameId()  { return game.id + ""; }
    public String getTeamId() { return team.id + ""; }
    public String getPoints() { return points + ""; }
    public String getPos() { return pos; }
    public String getMin() { return min; }
    public String getFgm() { return fgm + ""; }
    public String getFga() { return fga + ""; }
    public String getFgp() { return fgp; }
    public String getFtm() { return ftm + ""; }
    public String getFta() { return fta + ""; }
    public String getFtp() { return ftp; }
    public String getTpm() { return tpm + ""; }
    public String getTpa() { return tpa + ""; }
    public String getTpp() { return tpp; }
    public String getOffReb() { return offReb + ""; }
    public String getDefReb() { return defReb + ""; }
    public String getTotReb() { return totReb + ""; }
    public String getAssists() { return assists + ""; }
    public String getpFouls() { return pFouls + ""; }
    public String getSteals() { return steals + ""; }
    public String getTurnovers() { return turnovers + ""; }
    public String getBlocks() { return blocks + ""; }
    public String getPlusMinus() { return plusMinus; }
    public String getPlayerId() { return player.id + ""; }
}
