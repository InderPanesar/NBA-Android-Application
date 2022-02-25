package com.aston.basketballapp.engine.model.player;
public class PlayerModelStandard {
    int jersey;
    boolean active;
    String pos = "";

    public String getJersey() {
        return jersey + "";
    }

    public boolean isActive() {
        return active;
    }

    public String getPos() {
        if(pos == null) {
            return "";
        }
        return pos;
    }
}
