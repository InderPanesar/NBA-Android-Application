package com.aston.basketballapp.engine.model.player;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;

public class Height {
    public String feets;
    public String inches;
    public String meters;

    public String getMeters() {
        if(meters == null) {
            return "";
        }
        return meters;
    }
}
