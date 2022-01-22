package com.aston.basicarchitecture.pages.home.main;

import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.aston.basicarchitecture.engine.model.ExampleModel;
import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;
import com.aston.basicarchitecture.engine.model.player.PlayerModel;
import com.aston.basicarchitecture.engine.model.standings.StandingsModel;
import com.aston.basicarchitecture.engine.model.standings.TeamStandingModel;
import com.aston.basicarchitecture.engine.repository.ExampleRepository;
import com.aston.basicarchitecture.engine.repository.schedule.ScheduleRepository;
import com.aston.basicarchitecture.engine.repository.standings.StandingsRepository;
import com.aston.basicarchitecture.pages.home.settings.favouriteTeam.TeamsRepo;
import com.aston.basicarchitecture.utils.AppConsts;
import com.aston.basicarchitecture.utils.livedata.StateMutableLiveData;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ExampleViewModel extends ViewModel {

    StandingsRepository repository;
    @Inject
    ExampleViewModel(@Named("StandingsRepository") StandingsRepository standingsRepository) {
        repository = standingsRepository;
    }

    StateMutableLiveData<ArrayList<String>> getPlayers(String teamId) {
        StateMutableLiveData<ArrayList<String>> data = new StateMutableLiveData<>();
        data.postLoading();
        repository.getSpecificTeamStandings(teamId).enqueue(new Callback<StandingsModel>() {
            @Override
            public void onResponse(Call<StandingsModel> call, Response<StandingsModel> response) {
                if (!response.isSuccessful()) {
                    data.postError(null);
                    return;
                } else {
                    StandingsModel model = response.body();
                    TeamStandingModel teamStandingModel = model.getApi().getStandings().get(0);
                    ArrayList<String> values = new ArrayList<>();
                    values.add(teamStandingModel.getConference().getName());
                    values.add(teamStandingModel.getConference().getRank());
                    values.add(teamStandingModel.getConference().getWin());
                    values.add(teamStandingModel.getConference().getLoss());
                    data.postSuccess(values);
                }

            }

            @Override
            public void onFailure(Call<StandingsModel> call, Throwable t) {
                data.postError(t);

            }

        });
        return data;
    }

    StateMutableLiveData<ArrayList<TeamStandingModel>> getSchedule(String conference) {
        StateMutableLiveData<ArrayList<TeamStandingModel>> data = new StateMutableLiveData<>();
        data.postLoading();
        repository.getStandings().enqueue(new Callback<StandingsModel>() {
            @Override
            public void onResponse(Call<StandingsModel> call, Response<StandingsModel> response) {
                if (!response.isSuccessful()) {
                    data.postError(null);
                    return;
                } else {
                    StandingsModel model = response.body();
                    List<TeamStandingModel> teamStandingModel = model.getApi().getStandings();

                    ArrayList<TeamStandingModel> temp_StandingModel = new ArrayList<>();
                    for(TeamStandingModel teamModel : teamStandingModel) {
                        if (teamModel.getConference().getName().equals(conference)) {
                            temp_StandingModel.add(teamModel);
                        }
                    }

                    Collections.sort(temp_StandingModel, new Comparator<TeamStandingModel>() {
                        public int compare(TeamStandingModel o1, TeamStandingModel o2) {
                            Integer rank1 = Integer.parseInt(o1.getConference().getRank());
                            Integer rank2 = Integer.parseInt(o2.getConference().getRank());
                            // compare two instance of `Score` and return `int` as result.
                            return rank1.compareTo(rank2);
                        }
                    });
                    data.postSuccess(temp_StandingModel);


                }

            }

            @Override
            public void onFailure(Call<StandingsModel> call, Throwable t) {
                data.postError(t);

            }

        });
        return data;
    }

    public String getFavouriteTeamLink(SharedPreferences pref) {
        int selectedValue = pref.getInt(AppConsts.TEAM_FAVOURITE_KEY, -1);
        TeamsRepo repo = new TeamsRepo();
        for(TeamsRepo.LocalTeam team : repo.getTeamList()) {
            if(team.getId() == selectedValue) {
                return team.getLogoURL();
            }
        }
        return "";
    }

    public String getFavouriteTeamName(SharedPreferences pref) {
        int selectedValue = pref.getInt(AppConsts.TEAM_FAVOURITE_KEY, -1);
        TeamsRepo repo = new TeamsRepo();
        for(TeamsRepo.LocalTeam team : repo.getTeamList()) {
            if(team.getId() == selectedValue) {
                return team.getName();
            }
        }
        return "";
    }

    public String getFavouriteId(SharedPreferences pref) {
        int selectedValue = pref.getInt(AppConsts.TEAM_FAVOURITE_KEY, -1);
        return selectedValue + "";
    }

    public String getTeamName(String teamID) {
        TeamsRepo repo = new TeamsRepo();
        for(TeamsRepo.LocalTeam team :  repo.getTeamList()) {
            if(team.getId() == Integer.parseInt(teamID)) {
                return team.getName();
            }
        }
        return "";
    }

    public String getTeamLogo(String teamID) {
        TeamsRepo repo = new TeamsRepo();
        Log.d("TEAMID", teamID);
        for(TeamsRepo.LocalTeam team :  repo.getTeamList()) {
            if(team.getId() == Integer.parseInt(teamID)) {
                return team.getLogoURL();
            }
        }
        return "";
    }





}
