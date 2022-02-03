package com.aston.basicarchitecture.pages.home.players.detail;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aston.basicarchitecture.engine.model.player.stats.PlayerStatistics;
import com.aston.basicarchitecture.engine.model.player.stats.PlayerStatsModel;
import com.aston.basicarchitecture.engine.model.schedule.game.GameModel;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;
import com.aston.basicarchitecture.engine.repository.players.PlayersRepository;
import com.aston.basicarchitecture.engine.repository.schedule.ScheduleRepository;
import com.aston.basicarchitecture.engine.repository.teams.TeamsRepository;
import com.aston.basicarchitecture.utils.AppConsts;
import com.aston.basicarchitecture.utils.livedata.StateMutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class PlayersDetailViewModel extends ViewModel {

    ArrayList<SinglePlayerStatsAdapter> statistics = new ArrayList<>();


    PlayersRepository repository;

    @Inject
    PlayersDetailViewModel(@Named("PlayersRepository") PlayersRepository exampleRepository) {
        repository = exampleRepository;
    }



    StateMutableLiveData<ArrayList<SinglePlayerStatsAdapter>> getPlayerGameStats(String playerId, SharedPreferences pref) {
        statistics = new ArrayList<>();
        List<Integer> integers = getSharedPreferences(pref);
        List<String> categories = new ArrayList<>();
        Integer integer = -1;
        integers.removeAll(Collections.singleton(integer));
        for(Integer i : integers) {
            if(i != -1) {
                String value = returnStatisticTopic(i);
                if(!value.equals("n/a")) { categories.add(value); }
            }
        }


        StateMutableLiveData<ArrayList<SinglePlayerStatsAdapter>> data = new StateMutableLiveData<>();

        if(integers.size() == 0) {
            data.postSuccess(null);
            return data;
        }

        repository.getPlayerStats(playerId).enqueue(new Callback<PlayerStatsModel>() {
            @Override
            public void onResponse(Call<PlayerStatsModel> call, Response<PlayerStatsModel> response) {
                if (!response.isSuccessful()) {
                    data.postError(null);
                } else {
                    PlayerStatsModel model = response.body();
                    ArrayList<PlayerStatistics> _statistics = model.getApi().getStatistics();
                    Collections.reverse(_statistics);
                    for(int i = 0; i < 5; i++) {
                        List<String> _values = new ArrayList<>();
                        if(integers.size() == 1 && _statistics.size() > 0) {
                            _values.add(returnStatistic(integers.get(0), _statistics.get(i)));
                        }
                        else if(integers.size() == 2 && _statistics.size() > 1) {
                            _values.add(returnStatistic(integers.get(0), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(1), _statistics.get(i)));
                        }
                        else if(integers.size() == 3 && _statistics.size() > 2) {
                            _values.add(returnStatistic(integers.get(0), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(1), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(2), _statistics.get(i)));
                        }
                        else if(integers.size() == 4 && _statistics.size() > 3) {
                            _values.add(returnStatistic(integers.get(0), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(1), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(2), _statistics.get(i)));
                            _values.add(returnStatistic(integers.get(3), _statistics.get(i)));
                        }

                        statistics.add(new SinglePlayerStatsAdapter(categories, _values));
                    }
                    data.postSuccess(statistics);
                }

            }

            @Override
            public void onFailure(Call<PlayerStatsModel> call, Throwable t) {
                data.postError(t);

            }

        });
        return data;
    }

    public List<Integer> getSharedPreferences(SharedPreferences pref) {
        List<Integer> values = new ArrayList<>();
        values.add(pref.getInt(AppConsts.RECENT_GAMES_ONE, -1));
        values.add(pref.getInt(AppConsts.RECENT_GAMES_TWO, -1));
        values.add(pref.getInt(AppConsts.RECENT_GAMES_THREE, -1));
        values.add(pref.getInt(AppConsts.RECENT_GAMES_FOUR, -1));
        return values;
    }

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

}


