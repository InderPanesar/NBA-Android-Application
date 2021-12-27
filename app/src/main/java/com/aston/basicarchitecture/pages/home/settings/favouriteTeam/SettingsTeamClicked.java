package com.aston.basicarchitecture.pages.home.settings.favouriteTeam;

import android.view.View;

public interface SettingsTeamClicked {
    public void cardClicked(View v, TeamsRepo.LocalTeam team, Boolean isRemoved);
}
