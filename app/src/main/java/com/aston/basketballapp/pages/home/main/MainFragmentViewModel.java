package com.aston.basketballapp.pages.home.main;

import android.content.SharedPreferences;
import androidx.lifecycle.ViewModel;
import com.aston.basketballapp.engine.model.standings.StandingsModel;
import com.aston.basketballapp.engine.model.standings.TeamStandingModel;
import com.aston.basketballapp.engine.repository.standings.StandingsRepository;
import com.aston.basketballapp.pages.home.settings.favouriteTeam.TeamsRepo;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.livedata.StateMutableLiveData;
import java.util.ArrayList;
import java.util.Arrays;
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
public class MainFragmentViewModel extends ViewModel {

    List<String> headers = Arrays.asList("Seed", "Logo", "Team", "Record");


    StandingsRepository repository;
    @Inject
    public MainFragmentViewModel(@Named("StandingsRepository") StandingsRepository standingsRepository) {
        repository = standingsRepository;
    }

    StateMutableLiveData<ArrayList<String>> getPlayers(String teamId) {
        StateMutableLiveData<ArrayList<String>> data = new StateMutableLiveData<>();
        data.postValueLoading();
        repository.getSpecificTeamStandings(teamId).enqueue(new Callback<StandingsModel>() {
            @Override
            public void onResponse(Call<StandingsModel> call, Response<StandingsModel> response) {
                if(teamId.equals("-1")) {
                    data.postValueError(null);
                    return;
                }
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                    return;
                } else {
                    StandingsModel model = response.body();
                    TeamStandingModel teamStandingModel = model.getApi().getStandings().get(0);
                    ArrayList<String> values = new ArrayList<>();
                    values.add(teamStandingModel.getConference().getName());
                    values.add(teamStandingModel.getConference().getRank());
                    values.add(teamStandingModel.getConference().getWin());
                    values.add(teamStandingModel.getConference().getLoss());
                    data.postValueSuccess(values);
                }

            }

            @Override
            public void onFailure(Call<StandingsModel> call, Throwable t) {
                data.postValueError(t);

            }

        });
        return data;
    }

    StateMutableLiveData<ArrayList<TeamStandingModel>> getSchedule(String conference) {
        StateMutableLiveData<ArrayList<TeamStandingModel>> data = new StateMutableLiveData<>();
        data.postValueLoading();
        repository.getStandings().enqueue(new Callback<StandingsModel>() {
            @Override
            public void onResponse(Call<StandingsModel> call, Response<StandingsModel> response) {
                if (!response.isSuccessful()) {
                    data.postValueError(null);
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
                    data.postValueSuccess(temp_StandingModel);


                }

            }

            @Override
            public void onFailure(Call<StandingsModel> call, Throwable t) {
                data.postValueError(t);

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
        for(TeamsRepo.LocalTeam team :  repo.getTeamList()) {
            if(team.getId() == Integer.parseInt(teamID)) {
                return team.getLogoURL();
            }
        }
        return "";
    }





}
