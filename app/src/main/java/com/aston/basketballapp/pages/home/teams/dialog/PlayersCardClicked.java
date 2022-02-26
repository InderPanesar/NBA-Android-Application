package com.aston.basketballapp.pages.home.teams.dialog;

import android.view.View;

import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;

//Interface for when a PlayerCard is Clicked.
public interface PlayersCardClicked {
    void cardClicked(View v, IndividualPlayerModel teamsModel);
}
