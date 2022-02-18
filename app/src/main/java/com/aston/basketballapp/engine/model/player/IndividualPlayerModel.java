package com.aston.basketballapp.engine.model.player;

public class IndividualPlayerModel {

    String firstName;
    String lastName;
    String teamId;
    String yearsPro;
    String collegeName;
    String country;
    String playerId;
    String dateOfBirth;
    String affiliation;
    String startNba;
    String heightInMeters;
    String weightInKilograms;
    PlayerStandardModel leagues;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getTeamId() {
        return teamId;
    }
    public String getYearsPro() {
        return yearsPro;
    }
    public String getCollegeName() {
        return collegeName;
    }
    public String getCountry() { return country; }
    public String getPlayerId() {
        return playerId;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getAffilation() {
        return affiliation;
    }
    public String getStartNBA() {
        return startNba;
    }
    public String getHeightInMetres() {
        return heightInMeters;
    }
    public String getWeightInKilograms() {
        return weightInKilograms;
    }
    public PlayerStandardModel getLeagues() {
        return leagues;
    }


}
