package com.aston.basketballapp.pages.home.settings.favouriteTeam;


//Interface for when a SettingsCard is Clicked.
public interface SettingsTeamClicked {
    void cardClicked(TeamsRepo.LocalTeam team, Boolean isRemoved);
}
