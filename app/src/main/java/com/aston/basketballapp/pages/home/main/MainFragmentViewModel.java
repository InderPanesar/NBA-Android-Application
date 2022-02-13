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

//HiltViewModel for MainBaseFragment
@HiltViewModel
public class MainFragmentViewModel extends ViewModel {

    //Headers to the table
    List<String> headers = Arrays.asList("Seed", "Logo", "Team", "Record");
    //State of the scheduleFragment
    String conference = "east";



    //Create MainFragmentViewModel with StandingsRepo Injected.
    StandingsRepository repository;
    @Inject
    public MainFragmentViewModel(@Named("StandingsRepository") StandingsRepository standingsRepository) {
        repository = standingsRepository;
    }

    //Get Favourite Team information.
    StateMutableLiveData<ArrayList<String>> getFavouriteTeamInformation(String teamId) {
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

    //Get the information for the schedule.
    StateMutableLiveData<ArrayList<TeamStandingModel>> getSchedule() {
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

    //Get Favourite Team Image URL Link.
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

    //Get Favourite Team Name.
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

    //Get Favourite Team ID.
    public String getFavouriteId(SharedPreferences pref) {
        int selectedValue = pref.getInt(AppConsts.TEAM_FAVOURITE_KEY, -1);
        return selectedValue + "";
    }

    //Get Team Name for specific TeamID
    public String getTeamName(String teamID) {
        TeamsRepo repo = new TeamsRepo();
        for(TeamsRepo.LocalTeam team :  repo.getTeamList()) {
            if(team.getId() == Integer.parseInt(teamID)) {
                return team.getName();
            }
        }
        return "";
    }

    //Get Team Logo for specific TeamID
    public String getTeamLogo(String teamID) {
        TeamsRepo repo = new TeamsRepo();
        for(TeamsRepo.LocalTeam team :  repo.getTeamList()) {
            if(team.getId() == Integer.parseInt(teamID)) {
                return team.getLogoURL();
            }
        }
        return "";
    }

    //Get Conference String for Favourite Team
    public String getConferenceString(ArrayList<String> data) {
        String conferenceString = "";

        if(data.get(0).equals("east")) {
            conferenceString = conferenceString + "Eastern Conference : ";
        }
        else {
            conferenceString = conferenceString + "Western Conference : ";
        }
        conferenceString = conferenceString + data.get(1);
        return conferenceString;
    }

    //Get Record String for Favourite Team
    public String getRecordString(ArrayList<String> data) {
        String recordString = "Record : ";
        recordString = recordString + data.get(2) + " - " + data.get(3);
        return recordString;
    }





}
