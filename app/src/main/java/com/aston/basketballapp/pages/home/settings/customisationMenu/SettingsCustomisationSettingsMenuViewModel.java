package com.aston.basketballapp.pages.home.settings.customisationMenu;

import android.content.SharedPreferences;

import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.utils.AppConsts;

import java.util.ArrayList;
import java.util.List;

public class SettingsCustomisationSettingsMenuViewModel extends ViewModel {

    //Set Shared preferences for Recent Game Values.
    public void setSharedPreferences(SharedPreferences pref, List<Integer> values) {
        SharedPreferences.Editor editor = pref.edit();
        if(values.size() > 0) { editor.putInt(AppConsts.RECENT_GAMES_ONE, values.get(0)).apply(); }
        else {editor.putInt(AppConsts.RECENT_GAMES_ONE, -1);}

        if(values.size() > 1) { editor.putInt(AppConsts.RECENT_GAMES_TWO, values.get(1)).apply(); }
        else {editor.putInt(AppConsts.RECENT_GAMES_TWO, -1);}

        if(values.size() > 2) { editor.putInt(AppConsts.RECENT_GAMES_THREE, values.get(2)).apply(); }
        else {editor.putInt(AppConsts.RECENT_GAMES_THREE, -1);}

        if(values.size() > 3) { editor.putInt(AppConsts.RECENT_GAMES_FOUR, values.get(3)).apply(); }
        else {
            editor.putInt(AppConsts.RECENT_GAMES_FOUR, -1);
        }
        editor.apply();
    }

    //Get Shared preferences for Recent Game Values.
    public List<Integer> getSharedPreferences(SharedPreferences pref) {
        List<Integer> values = new ArrayList<>();
        values.add(pref.getInt(AppConsts.RECENT_GAMES_ONE, -1));
        values.add(pref.getInt(AppConsts.RECENT_GAMES_TWO, -1));
        values.add(pref.getInt(AppConsts.RECENT_GAMES_THREE, -1));
        values.add(pref.getInt(AppConsts.RECENT_GAMES_FOUR, -1));
        return values;
    }
}
