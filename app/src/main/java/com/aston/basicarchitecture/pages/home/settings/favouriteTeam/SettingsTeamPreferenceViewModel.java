package com.aston.basicarchitecture.pages.home.settings.favouriteTeam;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;

import com.aston.basicarchitecture.utils.AppConsts;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.lifecycle.HiltViewModel;

public class SettingsTeamPreferenceViewModel extends ViewModel {

    TeamsRepo repo = new TeamsRepo();
    int selectedValue = -1;

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
