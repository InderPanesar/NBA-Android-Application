package com.aston.basketballapp.engine.model.teams;
public class TeamsLeagueModel {
    TeamsStandardModel standard;
    TeamsStandardModel vegas;
    TeamsStandardModel utah;
    TeamsStandardModel sacramento;

    public TeamsStandardModel getStandard() {
        return standard;
    }

    public TeamsStandardModel getVegas() {
        return vegas;
    }

    public TeamsStandardModel getUtah() {
        return utah;
    }

    public TeamsStandardModel getSacramento() {
        return sacramento;
    }
}
