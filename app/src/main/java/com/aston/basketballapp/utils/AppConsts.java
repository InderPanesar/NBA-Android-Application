package com.aston.basketballapp.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;

//Contains values and methods which are used globally throughout the application
public class AppConsts {

    //These strings are the appropriate key values for shared preferences to store data locally
    public static String TEAM_FAVOURITE_KEY = "TEAMFAVOURITEID";
    public static String RECENT_GAMES_ONE = "recentGamesPlayerProfileOne";
    public static String RECENT_GAMES_TWO = "recentGamesPlayerProfileTwo";
    public static String RECENT_GAMES_THREE = "recentGamesPlayerProfileThree";
    public static String RECENT_GAMES_FOUR = "recentGamesPlayerProfileFour";
    public static String VALUES_SET = "valuesSet";


    //URL image from the API for Wizards is not transparent and this provides corrected URL
    public static String URLImageCorrector(String URL) {
        String incorrectWizardsAPIImage = "https://upload.wikimedia.org/wikipedia/fr/archive/d/d6/20161212034849%21Wizards2015.png";
        String correctedWizardsAPIImage = "https://upload.wikimedia.org/wikipedia/en/thumb/0/02/Washington_Wizards_logo.svg/1200px-Washington_Wizards_logo.svg.png";
        if(URL == null) {
            return null;
        }
        if(URL.equals(incorrectWizardsAPIImage)) {
            return correctedWizardsAPIImage;
        }
        return URL;
    }

    //Used to verify activities.
    public static void verifyActivity(Activity activity) {
        if(null == activity) {
            throw new IllegalStateException("Activity doesn't exist, check if fragment is attached to parent activity");
        }
    }

    //Used to verify context
    public static void verifyContext(Context context) {
        if(null == context) {
            throw new IllegalStateException("Context doesn't exist.");
        }
    }
    //Used to verify context
    public static void verifyArguments(Bundle bundle) {
        if(null == bundle) {
            throw new IllegalStateException("Arguments doesn't exist.");
        }
    }




}
