package com.aston.basketballapp.pages.home.schedule;

import android.view.View;

import com.aston.basketballapp.engine.model.schedule.schedule.GamesModel;

//Interface for when Box Score button is Clicked.
public interface ScheduleCardClicked {
    void scheduleCardClicked(View v, GamesModel gamesModel);
}
