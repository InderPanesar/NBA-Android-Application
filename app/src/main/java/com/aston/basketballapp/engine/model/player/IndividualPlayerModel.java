package com.aston.basketballapp.engine.model.player;

import com.google.gson.annotations.SerializedName;

public class IndividualPlayerModel {
    int id;
    String firstname;
    String lastname;
    PlayerModelBirth birth;
    PlayerModelNba nba;
    @SerializedName("height")
    Height height;
    Weight weight;
    String college;
    String affiliation;
    PlayerModelLeagues leagues;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public PlayerModelBirth getBirth() {
        return birth;
    }

    public PlayerModelNba getNba() {
        return nba;
    }

    public Height getHeight() {
        return height;
    }

    public Weight getWeight() {
        return weight;
    }

    public String getCollege() {
        return college;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public PlayerModelLeagues getLeagues() {
        return leagues;
    }
}
