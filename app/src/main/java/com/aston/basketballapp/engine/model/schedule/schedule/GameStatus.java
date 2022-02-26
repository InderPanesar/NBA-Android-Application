package com.aston.basketballapp.engine.model.schedule.schedule;

import com.google.gson.annotations.SerializedName;

class GameStatus {
    boolean halftime;

    @SerializedName("long")
    String status;
}
