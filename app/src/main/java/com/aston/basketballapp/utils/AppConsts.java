package com.aston.basketballapp.utils;

//Contains values and methods which are used globally throughout the application
public class AppConsts {

    //These strings are the appropriate key values for shared preferences to store data locally
    public static String TEAM_FAVOURITE_KEY = "TEAMFAVOURITEID";
    public static String RECENT_GAMES_ONE = "recentGamesPlayerProfileOne";
    public static String RECENT_GAMES_TWO = "recentGamesPlayerProfileTwo";
    public static String RECENT_GAMES_THREE = "recentGamesPlayerProfileThree";
    public static String RECENT_GAMES_FOUR = "recentGamesPlayerProfileFour";
    //ToDo: Add this to MainActivity.java
    public static String VALUES_SET = "valuesSet";


    //URL image from the API for Wizards is not transparent and this provides corrected URL
    public static String URLImageCorrector(String URL) {
        String incorrectWizardsAPIImage = "https://upload.wikimedia.org/wikipedia/fr/archive/d/d6/20161212034849%21Wizards2015.png";
        String correctedWizardsAPIImage = "https://upload.wikimedia.org/wikipedia/en/thumb/0/02/Washington_Wizards_logo.svg/1200px-Washington_Wizards_logo.svg.png";
        if(URL.equals(incorrectWizardsAPIImage)) {
            return correctedWizardsAPIImage;
        }
        return URL;
    }

}
