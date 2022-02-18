package com.aston.basketballapp.pages.home.teams.dialog;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.engine.model.player.PlayerModel;
import com.aston.basketballapp.engine.repository.players.PlayersRepository;
import com.aston.basketballapp.utils.livedata.StateMutableLiveData;

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
    public TeamDialogViewModel(@Named("PlayersRepository") PlayersRepository exampleRepository) {
        repository = exampleRepository;
    }

    //Get All Players from API for specific team.
    public StateMutableLiveData<ArrayList<IndividualPlayerModel>> getPlayers(String teamId) {

        StateMutableLiveData<ArrayList<IndividualPlayerModel>> data = new StateMutableLiveData<>();
        data.postValueLoading();
        repository.getPlayers(teamId).enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(@NonNull Call<PlayerModel> call, @NonNull Response<PlayerModel> response) {
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                } else {
                    PlayerModel model = response.body();
                    ArrayList<IndividualPlayerModel> players = model.getApi().getPlayers();
                    ArrayList<IndividualPlayerModel> filteredPlayers = new ArrayList<>();
                    for(IndividualPlayerModel player : players) {
                        if(player.getLeagues() != null) {
                            if(player.getLeagues().getNBADetails() != null) {
                                if(player.getLeagues().getNBADetails().getActive().equals("1")) {
                                    if(!player.getHeightInMetres().equals("") || !player.getWeightInKilograms().equals("")) {
                                        filteredPlayers.add(player);
                                    }
                                }
                            }
                        }
                    }
                    data.postValueSuccess(filteredPlayers);
                }

            }

            @Override
            public void onFailure(@NonNull Call<PlayerModel> call, @NonNull Throwable t) {
                data.postValueError(t);

            }

        });
        return data;
    }
}
