package com.aston.basicarchitecture.pages.home.teams.dialog;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;
import com.aston.basicarchitecture.engine.model.player.PlayerModel;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;
import com.aston.basicarchitecture.engine.repository.players.PlayersRepository;
import com.aston.basicarchitecture.utils.livedata.StateMutableLiveData;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class TeamDialogViewModel extends ViewModel {

    PlayersRepository repository;
    @Inject
    TeamDialogViewModel(@Named("PlayersRepository") PlayersRepository exampleRepository) {
        repository = exampleRepository;
    }

    StateMutableLiveData<ArrayList<IndividualPlayerModel>> getPlayers(String teamId) {

        StateMutableLiveData<ArrayList<IndividualPlayerModel>> data = new StateMutableLiveData<>();
        data.postLoading();
        repository.getPlayers(teamId).enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                if (!response.isSuccessful()) {
                    data.postError(null);
                    return;
                } else {
                    PlayerModel model = response.body();
                    ArrayList<IndividualPlayerModel> players = model.getApi().getPlayers();
                    ArrayList<IndividualPlayerModel> filteredPlayers = new ArrayList<>();
                    for(IndividualPlayerModel player : players) {
                        if(player.getLeagues() != null) {
                            if(player.getLeagues().getNBADetails() != null) {
                                if(player.getLeagues().getNBADetails().getActive().equals("1")) {
                                    if(!player.getHeightInMetres().equals("") || !player.getWeightInKilometers().equals("")) {
                                        filteredPlayers.add(player);
                                    }
                                }
                            }
                        }
                    }
                    data.postSuccess(filteredPlayers);
                }

            }

            @Override
            public void onFailure(Call<PlayerModel> call, Throwable t) {
                data.postError(t);

            }

        });
        return data;
    }
}
