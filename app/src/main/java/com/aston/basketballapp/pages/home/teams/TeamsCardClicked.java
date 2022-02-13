package com.aston.basketballapp.pages.home.teams;

import android.view.View;

import com.aston.basketballapp.engine.model.teams.IndividualTeamsModel;

//Interface for when a TeamCard is Clicked.
public interface TeamsCardClicked {
    public void cardClicked(View v, IndividualTeamsModel teamsModel);
}
