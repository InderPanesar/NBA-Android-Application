package com.aston.basketballapp.pages.home.settings.favouriteTeam;


//Interface for when a SettingsCard is Clicked.
public interface SettingsTeamClicked {
    public void cardClicked(TeamsRepo.LocalTeam team, Boolean isRemoved);
}
