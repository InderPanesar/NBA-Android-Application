package com.aston.basicarchitecture.pages.home.schedule;

import android.view.View;

import com.aston.basicarchitecture.engine.model.schedule.GamesModel;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;

public interface ScheduleCardClicked {
    public void scheduleCardClicked(View v, GamesModel gamesModel);
}
