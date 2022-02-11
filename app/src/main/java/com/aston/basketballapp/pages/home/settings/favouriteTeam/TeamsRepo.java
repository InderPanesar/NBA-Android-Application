package com.aston.basketballapp.pages.home.settings.favouriteTeam;

import java.util.ArrayList;
import java.util.Locale;

public class TeamsRepo {

    ArrayList<LocalTeam> teamList = new ArrayList<>();

    public TeamsRepo() {
        teamList.add(new LocalTeam(1, "Atlanta Hawks", "https://loodibee.com/wp-content/uploads/nba-atlanta-hawks-logo.png"));
        teamList.add(new LocalTeam(2, "Boston Celtics", "https://loodibee.com/wp-content/uploads/nba-boston-celtics-logo.png"));
        teamList.add(new LocalTeam(4, "Brooklyn Nets", "https://loodibee.com/wp-content/uploads/nba-brooklyn-nets-logo.png"));
        teamList.add(new LocalTeam(5, "Charlotte Hornets", "https://loodibee.com/wp-content/uploads/nba-charlotte-hornets-logo.png"));
        teamList.add(new LocalTeam(6, "Chicago Bulls", "https://www.nba.com/stats/media/img/teams/logos/CHI_logo.svg"));
        teamList.add(new LocalTeam(7, "Cleveland Cavaliers", "https://upload.wikimedia.org/wikipedia/en/4/4b/Cleveland_Cavaliers_logo.svg"));
        teamList.add(new LocalTeam(8, "Dallas Mavericks", "https://upload.wikimedia.org/wikipedia/en/9/97/Dallas_Mavericks_logo.svg"));
        teamList.add(new LocalTeam(9, "Denver Nuggets", "https://upload.wikimedia.org/wikipedia/en/7/76/Denver_Nuggets.svg"));
        teamList.add(new LocalTeam(10, "Detroit Pistons", "https://upload.wikimedia.org/wikipedia/commons/7/7c/Pistons_logo17.svg"));
        teamList.add(new LocalTeam(11, "Golden State Warriors", "https://upload.wikimedia.org/wikipedia/en/0/01/Golden_State_Warriors_logo.svg"));
        teamList.add(new LocalTeam(14, "Houston Rockets", "https://upload.wikimedia.org/wikipedia/en/2/28/Houston_Rockets.svg"));
        teamList.add(new LocalTeam(15, "Indiana Pacers", "https://upload.wikimedia.org/wikipedia/en/1/1b/Indiana_Pacers.svg"));
        teamList.add(new LocalTeam(16, "LA Clippers", "https://upload.wikimedia.org/wikipedia/en/b/bb/Los_Angeles_Clippers_%282015%29.svg"));
        teamList.add(new LocalTeam(17, "Los Angeles Lakers", "https://upload.wikimedia.org/wikipedia/commons/3/3c/Los_Angeles_Lakers_logo.svg"));
        teamList.add(new LocalTeam(19, "Memphis Grizzlies", "https://upload.wikimedia.org/wikipedia/en/f/f1/Memphis_Grizzlies.svg"));
        teamList.add(new LocalTeam(20, "Miami Heat", "https://upload.wikimedia.org/wikipedia/en/f/fb/Miami_Heat_logo.svg"));
        teamList.add(new LocalTeam(21, "Milwaukee Bucks", "https://upload.wikimedia.org/wikipedia/en/4/4a/Milwaukee_Bucks_logo.svg"));
        teamList.add(new LocalTeam(22, "Minnesota Timberwolves", "https://upload.wikimedia.org/wikipedia/en/c/c2/Minnesota_Timberwolves_logo.svg"));
        teamList.add(new LocalTeam(23, "New Orleans Pelicans", "https://upload.wikimedia.org/wikipedia/en/0/0d/New_Orleans_Pelicans_logo.svg"));
        teamList.add(new LocalTeam(24, "New York Knicks", "https://upload.wikimedia.org/wikipedia/en/2/25/New_York_Knicks_logo.svg"));
        teamList.add(new LocalTeam(25, "Oklahoma City Thunder", "https://upload.wikimedia.org/wikipedia/en/5/5d/Oklahoma_City_Thunder.svg"));
        teamList.add(new LocalTeam(26, "Orlando Magic", "https://upload.wikimedia.org/wikipedia/en/1/10/Orlando_Magic_logo.svg"));
        teamList.add(new LocalTeam(27, "Philadelphia 76ers", "https://upload.wikimedia.org/wikipedia/en/0/0e/Philadelphia_76ers_logo.svg"));
        teamList.add(new LocalTeam(28, "Phoenix Suns", "https://upload.wikimedia.org/wikipedia/en/d/dc/Phoenix_Suns_logo.svg"));
        teamList.add(new LocalTeam(29, "Portland Trail Blazers", "https://upload.wikimedia.org/wikipedia/en/2/21/Portland_Trail_Blazers_logo.svg"));
        teamList.add(new LocalTeam(30, "Sacramento Kings", "https://upload.wikimedia.org/wikipedia/en/c/c7/SacramentoKings.svg"));
        teamList.add(new LocalTeam(31, "San Antonio Spurs", "https://upload.wikimedia.org/wikipedia/en/a/a2/San_Antonio_Spurs.svg"));
        teamList.add(new LocalTeam(38, "Toronto Raptors", "https://upload.wikimedia.org/wikipedia/en/3/36/Toronto_Raptors_logo.svg"));
        teamList.add(new LocalTeam(40, "Utah Jazz", "https://static.wikia.nocookie.net/logopedia/images/5/5e/Utah_Jazz_logo.svg/revision/latest/scale-to-width-down/300?cb=20210706151644"));
        teamList.add(new LocalTeam(41, "Washington Wizards", "https://upload.wikimedia.org/wikipedia/en/0/02/Washington_Wizards_logo.svg"));
    }

    public void setSelected(LocalTeam team) {
        for(LocalTeam t : teamList) {
            if(team.id == t.id) {
                t.isSelected = true;
            }
            else {
                t.isSelected = false;
            }
        }
    }

    public void setSelected(int id) {
        for(LocalTeam t : teamList) {
            if(id == t.id) {
                t.isSelected = true;
            }
            else {
                t.isSelected = false;
            }
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

        public Boolean getSelected() {
            return isSelected;
        }

        public LocalTeam(int _id, String _name, String _logoURL) {
            this.name = _name;
            this.id = _id;

            String[] splited = _name.split("\\s");
            StringBuilder urlExtension = new StringBuilder();
            for(String s : splited) {
                urlExtension.append(s.toLowerCase(Locale.ROOT)).append("-");
            }
            if(_name.contains("Denver")) {
                this.logoURL = "https://loodibee.com/wp-content/uploads/nba-" + urlExtension + "logo-2018.png";
            }
            else {
                this.logoURL = "https://loodibee.com/wp-content/uploads/nba-" + urlExtension + "logo.png";
            }
            isSelected = false;
        }

        public void setSelected(Boolean selected) {
            isSelected = selected;
        }

    }
}
