package com.aston.basketballapp.pages.home.settings.favouriteTeam;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.utils.AppConsts;

import java.util.ArrayList;

public class SettingsTeamPreferenceViewModel extends ViewModel {

    //Collection of teams repo.
    TeamsRepo repo = new TeamsRepo();
    //Selected Team Value
    int selectedValue = -1;

    //Setting the preference for SharedPreferences
    public void setPref(SharedPreferences pref) {
        selectedValue = pref.getInt(AppConsts.TEAM_FAVOURITE_KEY, -1);
        if(selectedValue > 0) {
            addTeamPreference(selectedValue);
        }
    }

    ArrayList<TeamsRepo.LocalTeam> getTeams() {
        return repo.teamList;
    }

    void addTeamPreference(int id) {
        repo.setSelected(id);
        selectedValue = id;
    }

    void removeTeamPreference() {
        repo.removeAllSelected();
        selectedValue = -1;
    }

    public void setSharedPreferences(SharedPreferences pref) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(AppConsts.TEAM_FAVOURITE_KEY, selectedValue).apply();
    }

    public int getSelectedValue() {
        return selectedValue;
    }

}
