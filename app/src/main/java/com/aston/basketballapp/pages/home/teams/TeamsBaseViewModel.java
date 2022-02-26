package com.aston.basketballapp.pages.home.teams;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.engine.model.teams.IndividualTeamsModel;
import com.aston.basketballapp.engine.model.teams.TeamsModelAPI;
import com.aston.basketballapp.engine.repository.teams.TeamsRepository;
import com.aston.basketballapp.utils.livedata.StateMutableLiveData;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Named;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class TeamsBaseViewModel extends ViewModel {

    //TeamRepo to make API Calls
    TeamsRepository repository;
    //Current Selected Conference.
    String currentConference = "east";


    @Inject
    public TeamsBaseViewModel(@Named("TeamsRepository") TeamsRepository exampleRepository) {
        repository = exampleRepository;
    }

    //Make call to API to get Teams.
    public StateMutableLiveData<ArrayList<IndividualTeamsModel>> getTeams() {
        StateMutableLiveData<ArrayList<IndividualTeamsModel>> data = new StateMutableLiveData<>();
        data.postValueLoading();
        repository.getTeams(currentConference).enqueue(new Callback<TeamsModelAPI>() {
            @Override
            public void onResponse(@NonNull Call<TeamsModelAPI> call, @NonNull Response<TeamsModelAPI> response) {
              if(!response.isSuccessful()) {
                  data.postValueError(null);
              }
              else {
                  TeamsModelAPI model = response.body();
                  if(model != null) {
                      ArrayList<IndividualTeamsModel> teams = model.getTeams();
                      ArrayList<IndividualTeamsModel> filteredTeams = new ArrayList<>();
                      for(IndividualTeamsModel team : teams) {
                          if(team.isNbaFranchise() && !team.getLogo().equals("")) {
                              filteredTeams.add(team);
                          }
                      }
                      data.postValueSuccess(filteredTeams);
                  }
              }

            }

            @Override
            public void onFailure(@NonNull Call<TeamsModelAPI> call, @NonNull Throwable t) {
                data.postValueError(t);
            }

        });
        return data;
    }
}
