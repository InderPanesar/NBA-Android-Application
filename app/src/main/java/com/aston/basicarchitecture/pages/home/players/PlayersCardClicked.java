package com.aston.basicarchitecture.pages.home.players;

import android.view.View;

import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;

public interface PlayersCardClicked {
    public void cardClicked(View v, IndividualPlayerModel teamsModel);
}
