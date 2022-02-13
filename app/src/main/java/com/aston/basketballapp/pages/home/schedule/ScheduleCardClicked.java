package com.aston.basketballapp.pages.home.schedule;

import android.view.View;

import com.aston.basketballapp.engine.model.schedule.GamesModel;

//Interface for when Box Score button is Clicked.
public interface ScheduleCardClicked {
    public void scheduleCardClicked(View v, GamesModel gamesModel);
}
