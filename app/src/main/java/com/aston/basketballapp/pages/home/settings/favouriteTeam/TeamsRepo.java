package com.aston.basketballapp.pages.home.settings.favouriteTeam;

import java.util.ArrayList;
import java.util.Locale;

//LocalRepo which contains all Team Information with LogoURL, Team Name and ID to allow get basic team Logos and information.
public class TeamsRepo {

    ArrayList<LocalTeam> teamList = new ArrayList<>();

    public TeamsRepo() {
        teamList.add(new LocalTeam(1, "Atlanta Hawks"));
        teamList.add(new LocalTeam(2, "Boston Celtics"));
        teamList.add(new LocalTeam(4, "Brooklyn Nets"));
        teamList.add(new LocalTeam(5, "Charlotte Hornets"));
        teamList.add(new LocalTeam(6, "Chicago Bulls"));
        teamList.add(new LocalTeam(7, "Cleveland Cavaliers"));
        teamList.add(new LocalTeam(8, "Dallas Mavericks"));
        teamList.add(new LocalTeam(9, "Denver Nuggets"));
        teamList.add(new LocalTeam(10, "Detroit Pistons"));
        teamList.add(new LocalTeam(11, "Golden State Warriors"));
        teamList.add(new LocalTeam(14, "Houston Rockets"));
        teamList.add(new LocalTeam(15, "Indiana Pacers"));
        teamList.add(new LocalTeam(16, "LA Clippers"));
        teamList.add(new LocalTeam(17, "Los Angeles Lakers"));
        teamList.add(new LocalTeam(19, "Memphis Grizzlies"));
        teamList.add(new LocalTeam(20, "Miami Heat"));
        teamList.add(new LocalTeam(21, "Milwaukee Bucks"));
        teamList.add(new LocalTeam(22, "Minnesota Timberwolves"));
        teamList.add(new LocalTeam(23, "New Orleans Pelicans"));
        teamList.add(new LocalTeam(24, "New York Knicks"));
        teamList.add(new LocalTeam(25, "Oklahoma City Thunder"));
        teamList.add(new LocalTeam(26, "Orlando Magic"));
        teamList.add(new LocalTeam(27, "Philadelphia 76ers"));
        teamList.add(new LocalTeam(28, "Phoenix Suns"));
        teamList.add(new LocalTeam(29, "Portland Trail Blazers"));
        teamList.add(new LocalTeam(30, "Sacramento Kings"));
        teamList.add(new LocalTeam(31, "San Antonio Spurs"));
        teamList.add(new LocalTeam(38, "Toronto Raptors"));
        teamList.add(new LocalTeam(40, "Utah Jazz"));
        teamList.add(new LocalTeam(41, "Washington Wizards"));
    }

    public void setSelected(int id) {
        for(LocalTeam t : teamList) {
            t.isSelected = id == t.id;
        }
    }

    public void removeAllSelected() {
        for(LocalTeam t : teamList) {
            t.isSelected = false;
        }
    }

    public  ArrayList<LocalTeam> getTeamList() {
        return teamList;
    }

    public static class LocalTeam {
        String name;
        int id;
        String logoURL;
        Boolean isSelected;


        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public String getLogoURL() {
            return logoURL;
        }

        public LocalTeam(int _id, String _name) {
            this.name = _name;
            this.id = _id;

            String[] split = _name.split("\\s");
            StringBuilder urlExtension = new StringBuilder();
            for(String s : split) {
                urlExtension.append(s.toLowerCase(Locale.ROOT)).append("-");
            }
            //Set Logo using
            if(_name.contains("Denver")) {
                this.logoURL = "https://loodibee.com/wp-content/uploads/nba-" + urlExtension + "logo-2018.png";
            }
            else {
                this.logoURL = "https://loodibee.com/wp-content/uploads/nba-" + urlExtension + "logo.png";
            }
            isSelected = false;
        }

    }
}
