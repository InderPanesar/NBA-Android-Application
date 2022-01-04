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
        Log.d("VALUE", "ID: " + String.valueOf(selectedValue));
    }

    ArrayList<TeamsRepo.LocalTeam> getTeams() {
        return repo.teamList;
    }

    void addTeamPreference(int id) {
        Log.d("ADDED", "ID: " + id);
        repo.setSelected(id);
        selectedValue = id;
    }

    void removeTeamPreference() {
        Log.d("REMOVED", "ID: " + -1);
        repo.removeAllSelected();
        selectedValue = -1;
    }

    void setSharedPreferences(SharedPreferences pref) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(AppConsts.TEAM_FAVOURITE_KEY, selectedValue).apply();
        Log.d("ADDED", "ID: " + String.valueOf(selectedValue));
    }
}
