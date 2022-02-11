package com.aston.basketballapp.pages.home.teams;

import android.view.View;

import com.aston.basketballapp.engine.model.teams.IndividualTeamsModel;

public interface TeamsCardClicked {
    public void cardClicked(View v, IndividualTeamsModel teamsModel);
}
