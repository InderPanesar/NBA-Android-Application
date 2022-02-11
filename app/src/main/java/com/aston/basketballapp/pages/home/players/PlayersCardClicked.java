package com.aston.basketballapp.pages.home.players;

import android.view.View;

import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;

public interface PlayersCardClicked {
    public void cardClicked(View v, IndividualPlayerModel teamsModel);
}
