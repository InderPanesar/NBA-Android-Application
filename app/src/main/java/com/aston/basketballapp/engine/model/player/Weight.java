package com.aston.basketballapp.engine.model.player;
public class Weight {
    public String pounds;
    public String kilograms;

    public String getPounds() {
        if(pounds == null) {
            return "";
        }
        return pounds;
    }

    public String getKilograms() {
        if(kilograms == null) {
            return "";
        }
        return kilograms;
    }
}
