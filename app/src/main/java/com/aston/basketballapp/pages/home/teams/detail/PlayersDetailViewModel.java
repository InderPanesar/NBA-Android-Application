package com.aston.basketballapp.pages.home.teams.detail;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.engine.model.player.stats.PlayerStatistics;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModelApi;
import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModelAPI;
import com.aston.basketballapp.engine.model.schedule.gameStatistics.StatisticsModel;
import com.aston.basketballapp.engine.repository.players.PlayersRepository;
import com.aston.basketballapp.engine.repository.schedule.ScheduleRepository;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.livedata.StateMutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class PlayersDetailViewModel extends ViewModel {

    //Generic Nba Logo URL
    final String nbaLogoURL = "https://logoeps.com/wp-content/uploads/2011/05/nba-logo-vector-01.png";
    //Statistics for each player.
    ArrayList<SinglePlayerStatsAdapter> statistics = new ArrayList<>();
    //Hold an Integer and String map for each team.
    Map<Integer, String> teams;

    PlayersRepository repository;
    ScheduleRepository scheduleRepository;

    @Inject
    public PlayersDetailViewModel(@Named("PlayersRepository") PlayersRepository exampleRepository, @Named("ScheduleRepository") ScheduleRepository _scheduleRepository) {
        repository = exampleRepository;
        scheduleRepository = _scheduleRepository;
        addTeamsToHashMap();
    }

    //Get a players data using the Id and the settings defined for how many and what attributes should be shown.
    public StateMutableLiveData<ArrayList<SinglePlayerStatsAdapter>> getPlayerGameStats(String playerId, SharedPreferences pref) {
        statistics = new ArrayList<>();
        List<Integer> integers = getSharedPreferences(pref);
        List<String> categories = new ArrayList<>();
        Integer integer = -1;
        integers.removeAll(Collections.singleton(integer));
        categories.add("Opponent");
        for(Integer i : integers) {
            if(i != -1) {
                String value = returnStatisticTopic(i);
                if(!value.equals("n/a")) { categories.add(value); }
            }
        }

        final int[] teamsAdded = {0};


        StateMutableLiveData<ArrayList<SinglePlayerStatsAdapter>> data = new StateMutableLiveData<>();

        if(integers.size() == 0) {
            data.postValueSuccess(null);
            return data;
        }


        data.postValueLoading();


        repository.getPlayerStats(playerId).enqueue(new Callback<PlayerStatsModelApi>() {
            @Override
            public void onResponse(@NonNull Call<PlayerStatsModelApi> call, @NonNull Response<PlayerStatsModelApi> response) {
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                } else {
                    PlayerStatsModelApi model = response.body();
                    ArrayList<PlayerStatistics> _statistics = model.getStatistics();
                    Collections.reverse(_statistics);
                    statistics = new ArrayList<>();
                    for(int i = 0; i < 5; i++) {
                        List<String> _values = new ArrayList<>();
                        String id = "";
                        String code = "";
                        if(i < _statistics.size()-1) {

                            id = _statistics.get(i).getGameId();
                            code = _statistics.get(i).getTeamCode();
                        }


                        if(integers.size() == 1 && _statistics.size() > 0) {
                            _values.add(id+","+code);
                            _values.add(returnStatistic(integers.get(0), _statistics.get(i)));
                        }
                        else if(integers.size() == 2 && _statistics.size() > 1) {
                            _values.add(id+","+code);
                            _values.add(returnStatistic(integers.get(0), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(1), _statistics.get(i)));
                        }
                        else if(integers.size() == 3 && _statistics.size() > 2) {
                            _values.add(id+","+code);
                            _values.add(returnStatistic(integers.get(0), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(1), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(2), _statistics.get(i)));
                        }
                        else if(integers.size() == 4 && _statistics.size() > 3) {
                            _values.add(id+","+code);
                            _values.add(returnStatistic(integers.get(0), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(1), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(2), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(3), _statistics.get(i)));
                        }
                        statistics.add(new SinglePlayerStatsAdapter(categories, _values));



                    }

                    if(statistics.isEmpty()) {
                        data.postValueSuccess(statistics);
                    }
                    System.out.println(statistics.size());

                    for (SinglePlayerStatsAdapter statistic : statistics) {
                        if(statistic.attributes.size() != 0) {
                            String[] values = statistic.attributes.get(0).split(",");
                            String id = values[0];
                            String code = values[1];
                            scheduleRepository.getGameStatisticDetails(id).enqueue(new Callback<GameStatisticModelAPI>() {
                                @Override
                                public void onResponse(Call<GameStatisticModelAPI> call, Response<GameStatisticModelAPI> response) {
                                    List<StatisticsModel> models = response.body().getStatistics();
                                    if (!code.equals(models.get(0).getTeamCode())) {
                                        statistic.attributes.set(0, "vs " + models.get(0).getTeamCode());
                                    } else {
                                        statistic.attributes.set(0, "vs " + models.get(1).getTeamCode());
                                    }
                                    teamsAdded[0]++;
                                    if (teamsAdded[0] == (statistics.size())) {
                                        data.postValueSuccess(statistics);
                                    }
                                }

                                @Override
                                public void onFailure(Call<GameStatisticModelAPI> call, Throwable t) {
                                    data.postValueError(null);
                                }
                            });
                        }
                        else {
                            data.postValueSuccess(statistics);
                            return;
                        }

                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<PlayerStatsModelApi> call, @NonNull Throwable t) {
                data.postValueError(t);
            }

        });
        return data;
    }



    //Get Each of the values in shared preferences
    public List<Integer> getSharedPreferences(SharedPreferences pref) {
        List<Integer> values = new ArrayList<>();
        values.add(pref.getInt(AppConsts.RECENT_GAMES_ONE, -1));
        values.add(pref.getInt(AppConsts.RECENT_GAMES_TWO, -1));
        values.add(pref.getInt(AppConsts.RECENT_GAMES_THREE, -1));
        values.add(pref.getInt(AppConsts.RECENT_GAMES_FOUR, -1));
        return values;
    }

    //Get corrosponding statistic for each shared preference values.
    public String returnStatistic(int categoryValue, PlayerStatistics statistics) {
        if(categoryValue == 1) { return statistics.getPoints(); }
        if(categoryValue == 2) { return statistics.getAssists(); }
        if(categoryValue == 3) { return statistics.getTotReb(); }
        if(categoryValue == 4) { return statistics.getFgp(); }
        if(categoryValue == 5) { return statistics.getSteals(); }
        if(categoryValue == 6) { return statistics.getBlocks(); }
        if(categoryValue == 7) { return statistics.getFtp(); }
        if(categoryValue == 8) { return statistics.getFtm(); }
        if(categoryValue == 9) { return statistics.getPlusMinus(); }
        return "n/a";
    }

    //Return Topic Header for each categoryValue
    public String returnStatisticTopic(int categoryValue) {
        if(categoryValue == 1) { return "PPG"; }
        if(categoryValue == 2) { return "APG"; }
        if(categoryValue == 3) { return "RPG"; }
        if(categoryValue == 4) { return "OFGP"; }
        if(categoryValue == 5) { return "Steals"; }
        if(categoryValue == 6) { return "Blocks"; }
        if(categoryValue == 7) { return "FT%"; }
        if(categoryValue == 8) { return "FTM"; }
        if(categoryValue == 9) { return "+/-"; }
        return "n/a";
    }

    //Create Local TeamID and Name HashMap.
    public void addTeamsToHashMap() {
        teams = new HashMap<>();
        teams.put(1, "Atlanta Hawks");
        teams.put(2, "Boston Celtics");
        teams.put(4, "Brooklyn Nets");
        teams.put(5, "Charlotte Hornets");
        teams.put(6, "Chicago Bulls");
        teams.put(7, "Cleveland Cavaliers");
        teams.put(8, "Dallas Mavericks");
        teams.put(9, "Denver Nuggets");
        teams.put(10, "Detroit Pistons");
        teams.put(11, "Golden State Warriors");
        teams.put(14, "Houston Rockets");
        teams.put(15, "Indiana Pacers");
        teams.put(16, "Los Angeles Clippers");
        teams.put(17, "Los Angeles Lakers");
        teams.put(19, "Memphis Grizzlies");
        teams.put(20, "Miami Heat");
        teams.put(21, "Milwaukee Bucks");
        teams.put(22, "Minnesota Timberwolves");
        teams.put(23, "New Orleans Pelicans");
        teams.put(24, "New York Knicks");
        teams.put(25, "Oklahoma City Thunder");
        teams.put(26, "Orlando Magic");
        teams.put(27, "Philadelphia 76ers");
        teams.put(28, "Phoenix Suns");
        teams.put(29, "Portland Trail Blazers");
        teams.put(30, "Sacramento Kings");
        teams.put(31, "San Antonio Spurs");
        teams.put(38, "Toronto Raptors");
        teams.put(40, "Washington Wizards");
    }

}


