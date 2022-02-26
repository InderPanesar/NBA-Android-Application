package com.aston.basketballapp.pages.home.teams.dialog;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.engine.model.player.PlayerModelApi;
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
        repository.getPlayers(teamId).enqueue(new Callback<PlayerModelApi>() {
            @Override
            public void onResponse(@NonNull Call<PlayerModelApi> call, @NonNull Response<PlayerModelApi> response) {
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                } else {
                    PlayerModelApi model = response.body();

                    if(model != null) {
                        ArrayList<IndividualPlayerModel> players = model.getPlayers();
                        ArrayList<IndividualPlayerModel> filteredPlayers = new ArrayList<>();
                        for(IndividualPlayerModel player : players) {
                            if(player.getLeagues() != null) {
                                if(player.getLeagues().getStandard() != null) {
                                    if (!player.getLeagues().getStandard().getPos().equals("")) {
                                        if (!player.getHeight().getMeters().equals("") || !player.getWeight().getKilograms().equals("")) {
                                            filteredPlayers.add(player);
                                        }
                                    }
                                }
                            }
                        }
                        data.postValueSuccess(filteredPlayers);
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<PlayerModelApi> call, @NonNull Throwable t) {
                data.postValueError(t);

            }

        });
        return data;
    }
}
