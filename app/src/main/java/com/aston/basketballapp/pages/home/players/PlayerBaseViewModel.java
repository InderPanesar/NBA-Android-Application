package com.aston.basketballapp.pages.home.players;

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
public class PlayerBaseViewModel extends ViewModel {

    ArrayList<IndividualPlayerModel> localTempPlayers = new ArrayList<>();
    int playerFilter = 0;

    PlayersRepository repository;
    @Inject
    PlayerBaseViewModel(@Named("PlayersRepository") PlayersRepository exampleRepository) {
        repository = exampleRepository;
    }

    StateMutableLiveData<ArrayList<IndividualPlayerModel>> getAllPlayers() {
        StateMutableLiveData<ArrayList<IndividualPlayerModel>> data = new StateMutableLiveData<>();
        repository.getAllPlayers().enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                } else {
                    PlayerModel model = response.body();
                    ArrayList<IndividualPlayerModel> players = model.getApi().getPlayers();
                    ArrayList<IndividualPlayerModel> filteredPlayers = new ArrayList<>();
                    for(IndividualPlayerModel player : players) {
                        if(player.getLeagues() != null) {
                            if(player.getLeagues().getNBADetails() != null) {
                                if(!player.getHeightInMetres().equals("") || !player.getWeightInKilometers().equals("")) {
                                    filteredPlayers.add(player);
                                }
                            }
                        }
                    }
                    localTempPlayers = filteredPlayers;
                    data.postValueSuccess(filteredPlayers);
                }

            }

            @Override
            public void onFailure(Call<PlayerModel> call, Throwable t) {
                data.postValueError(t);
            }

        });
        return data;
    }

    StateMutableLiveData<ArrayList<IndividualPlayerModel>> getInternationalFilterPlayers() {
        StateMutableLiveData<ArrayList<IndividualPlayerModel>> data = new StateMutableLiveData<>();
        ArrayList<IndividualPlayerModel> players = localTempPlayers;
        ArrayList<IndividualPlayerModel> filteredPlayers = new ArrayList<>();

        if(playerFilter == 1) {
            for(IndividualPlayerModel player : players) {
                if(player.getCountry().equals("USA")) {
                    filteredPlayers.add(player);
                }
            }
        }
        else if (playerFilter == 2) {
            for(IndividualPlayerModel player : players) {
                if(!player.getCountry().equals("USA")) {
                    filteredPlayers.add(player);
                }
            }
        }
        else {
            filteredPlayers = players;
        }

        data.postValueSuccess(filteredPlayers);

        return data;
    }

}
