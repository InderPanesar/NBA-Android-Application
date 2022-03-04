package com.aston.basketballapp.pages.home.main;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.engine.model.standings.StandingsModelApi;
import com.aston.basketballapp.engine.model.standings.TeamStandingModel;
import com.aston.basketballapp.engine.repository.standings.StandingsRepository;
import com.aston.basketballapp.pages.home.settings.favouriteTeam.TeamsRepo;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.livedata.StateMutableLiveData;
import java.util.ArrayList;
import java.util.Arrays;
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
    String conference;



    //Create MainFragmentViewModel with StandingsRepo Injected.
    StandingsRepository repository;
    @Inject
    public MainFragmentViewModel(@Named("StandingsRepository") StandingsRepository standingsRepository) {
        repository = standingsRepository;
        conference = "east";
    }

    //Get Favourite Team information.
    public StateMutableLiveData<ArrayList<String>> getFavouriteTeamInformation(String teamId) {
        StateMutableLiveData<ArrayList<String>> data = new StateMutableLiveData<>();
        data.postValueLoading();
        repository.getSpecificTeamStandings(teamId).enqueue(new Callback<StandingsModelApi>() {
            @Override
            public void onResponse(@NonNull Call<StandingsModelApi> call, @NonNull Response<StandingsModelApi> response) {
                if(teamId.equals("-1")) {
                    data.postValueError(null);
                    return;
                }
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                } else {
                    StandingsModelApi model = response.body();

                    if(model == null) {
                        data.postValueError(null);
                    }
                    else {
                        if(model.getStandings().size() > 0) {
                            TeamStandingModel teamStandingModel = model.getStandings().get(0);
                            if (teamStandingModel != null) {
                                ArrayList<String> values = new ArrayList<>();
                                values.add(teamStandingModel.getConference().getName());
                                values.add(teamStandingModel.getConference().getRank() + "");
                                values.add(teamStandingModel.getWin().totalWins()+ "");
                                values.add(teamStandingModel.getLoss().totalWins() + "");
                                data.postValueSuccess(values);
                            }
                            else {
                                data.postValueError(null);
                            }
                        }
                        else {
                            data.postValueError(null);
                        }

                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<StandingsModelApi> call, @NonNull Throwable t) {
                data.postValueError(t);
            }

        });
        return data;
    }

    //Get the information for the schedule.
    public StateMutableLiveData<ArrayList<TeamStandingModel>> getSchedule() {
        StateMutableLiveData<ArrayList<TeamStandingModel>> data = new StateMutableLiveData<>();
        data.postValueLoading();
        repository.getStandings().enqueue(new Callback<StandingsModelApi>() {
            @Override
            public void onResponse(@NonNull Call<StandingsModelApi> call, @NonNull Response<StandingsModelApi> response) {
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                } else {
                    StandingsModelApi model = response.body();
                    if(model == null) {
                        data.postValueError(null);
                    }
                    else {
                        List<TeamStandingModel> teamStandingModel = model.getStandings();

                        ArrayList<TeamStandingModel> temp_StandingModel = new ArrayList<>();
                        for(TeamStandingModel teamModel : teamStandingModel) {
                            if (teamModel.getConference().getName().equals(conference)) {
                                temp_StandingModel.add(teamModel);
                            }
                        }

                        temp_StandingModel.sort((o1, o2) -> {
                            Integer rank1 = Integer.parseInt(o1.getConference().getRank());
                            Integer rank2 = Integer.parseInt(o2.getConference().getRank());
                            // compare two instance of `Score` and return `int` as result.
                            return rank1.compareTo(rank2);
                        });
                        data.postValueSuccess(temp_StandingModel);
                    }



                }

            }

            @Override
            public void onFailure(@NonNull Call<StandingsModelApi> call, @NonNull Throwable t) {
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

    public String getRecordStringForTeam(TeamStandingModel team) {
        return team.getWin().totalWins() + " - " + team.getLoss().totalWins();
    }





}
