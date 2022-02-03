package com.aston.basicarchitecture.pages.home.teams;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;
import com.aston.basicarchitecture.engine.repository.teams.TeamsRepository;
import com.aston.basicarchitecture.utils.livedata.StateMutableLiveData;
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
        data.postLoading();
        repository.getTeams(currentConference).enqueue(new Callback<TeamsModel>() {
            @Override
            public void onResponse(Call<TeamsModel> call, Response<TeamsModel> response) {
              if(!response.isSuccessful()) {
                  data.postError(null);
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
                  data.postSuccess(filteredTeams);
              }

            }

            @Override
            public void onFailure(Call<TeamsModel> call, Throwable t) {
                //Do Something here!
                data.postError(t);
            }

        });
        return data;
    }
}
