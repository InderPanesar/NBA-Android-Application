package com.aston.basketballapp.pages.home.teams;

import androidx.lifecycle.ViewModel;
import com.aston.basketballapp.engine.model.teams.IndividualTeamsModel;
import com.aston.basketballapp.engine.model.teams.TeamsModel;
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

    TeamsRepository repository;
    String currentConference = "east";


    @Inject
    TeamsBaseViewModel(@Named("TeamsRepository") TeamsRepository exampleRepository) {
        repository = exampleRepository;
    }

    StateMutableLiveData<ArrayList<IndividualTeamsModel>> getTeams() {

        StateMutableLiveData<ArrayList<IndividualTeamsModel>> data = new StateMutableLiveData<>();
        data.postValueLoading();
        repository.getTeams(currentConference).enqueue(new Callback<TeamsModel>() {
            @Override
            public void onResponse(Call<TeamsModel> call, Response<TeamsModel> response) {
              if(!response.isSuccessful()) {
                  data.postValueError(null);
              }
              else {
                  TeamsModel model = response.body();
                  ArrayList<IndividualTeamsModel> teams = model.getApi().getTeams();
                  ArrayList<IndividualTeamsModel> filteredTeams = new ArrayList<>();
                  for(IndividualTeamsModel team : teams) {
                      if(team.getNbaFranchise().equals("1") && !team.getLogo().equals("")) {
                          filteredTeams.add(team);
                      }
                  }
                  data.postValueSuccess(filteredTeams);
              }

            }

            @Override
            public void onFailure(Call<TeamsModel> call, Throwable t) {
                //Do Something here!
                data.postValueError(t);
            }

        });
        return data;
    }
}
