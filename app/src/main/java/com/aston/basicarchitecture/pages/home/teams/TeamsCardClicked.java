package com.aston.basicarchitecture.pages.home.teams;

import android.view.View;

import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;

public interface TeamsCardClicked {
    public void cardClicked(View v, IndividualTeamsModel teamsModel);
}
