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

    LiveData<ArrayList<IndividualPlayerModel>> getPlayers(String teamId) {

        MutableLiveData<ArrayList<IndividualPlayerModel>> data = new MutableLiveData<>();
        Log.d("HIT", "GOT PLAYER LIST");
        repository.getPlayers(teamId).enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("UNSUCCESSFUL CALL", "" + response.code());
                    return;
                } else {
                    PlayerModel model = response.body();
                    Log.d("SUCCESSFUL PLAYER CALL", "" + model.getApi().getStatus());
                    ArrayList<IndividualPlayerModel> players = model.getApi().getPlayers();
                    ArrayList<IndividualPlayerModel> filteredPlayers = new ArrayList<>();
                    for(IndividualPlayerModel player : players) {
                        if(player.getLeagues() != null) {
                            if(player.getLeagues().getNBADetails() != null) {
                                if(player.getLeagues().getNBADetails().getActive().equals("1")) {
                                    filteredPlayers.add(player);
                                }
                            }
                        }
                    }
                    data.postValue(filteredPlayers);
                }

            }

            @Override
            public void onFailure(Call<PlayerModel> call, Throwable t) {
                //Do Something here!
                Log.d("UNSUCCESSFUL CALL", "" + t.getLocalizedMessage());

            }

        });
        return data;
    }
}
