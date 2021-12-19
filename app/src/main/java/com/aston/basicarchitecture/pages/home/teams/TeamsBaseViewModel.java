package com.aston.basicarchitecture.pages.home.teams;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;
import com.aston.basicarchitecture.engine.repository.ExampleRepository;
import com.aston.basicarchitecture.engine.repository.teams.TeamsRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class TeamsBaseViewModel extends ViewModel {

    TeamsRepository repository;
    @Inject
    TeamsBaseViewModel(@Named("TeamsRepository") TeamsRepository exampleRepository) {
        repository = exampleRepository;
    }

    LiveData<ArrayList<IndividualTeamsModel>> getTeams(String conference) {

        MutableLiveData<ArrayList<IndividualTeamsModel>> data = new MutableLiveData<>();
        Log.d("HIT", "GOT LIST");
        repository.getTeams(conference).enqueue(new Callback<TeamsModel>() {
            @Override
            public void onResponse(Call<TeamsModel> call, Response<TeamsModel> response) {
              if(!response.isSuccessful()) {
                  //data.postValue("Code: " + response.code());
                  Log.d("UNSUCCESSFUL CALL", "" + response.code());
                  return;
              }
              else {
                  TeamsModel model = response.body();
                  Log.d("SUCCESSFUL CALL", "" + model.getApi().getStatus());
                  ArrayList<IndividualTeamsModel> teams = model.getApi().getTeams();
                  ArrayList<IndividualTeamsModel> filteredTeams = new ArrayList<>();
                  for(IndividualTeamsModel team : teams) {
                      if(team.getNbaFranchise().equals("1") && !team.getLogo().equals("")) {
                          filteredTeams.add(team);
                      }
                  }
                  data.postValue(filteredTeams);
              }

            }

            @Override
            public void onFailure(Call<TeamsModel> call, Throwable t) {
                //Do Something here!
                Log.d("UNSUCCESSFUL CALL", "" + t.getLocalizedMessage());

            }

        });
        return data;
    }
}
