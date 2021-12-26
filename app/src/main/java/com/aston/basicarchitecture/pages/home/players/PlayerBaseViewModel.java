package com.aston.basicarchitecture.pages.home.players;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;
import com.aston.basicarchitecture.engine.model.player.PlayerModel;
import com.aston.basicarchitecture.engine.repository.players.PlayersRepository;

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

    PlayersRepository repository;
    @Inject
    PlayerBaseViewModel(@Named("PlayersRepository") PlayersRepository exampleRepository) {
        repository = exampleRepository;
    }

    //ToDo: Filter By Players Last Name!

    LiveData<ArrayList<IndividualPlayerModel>> getAllPlayers() {

        MutableLiveData<ArrayList<IndividualPlayerModel>> data = new MutableLiveData<>();
        repository.getAllPlayers().enqueue(new Callback<PlayerModel>() {
            @Override
            public void onResponse(Call<PlayerModel> call, Response<PlayerModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("UNSUCCESSFUL CALL", "" + response.code());
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

    LiveData<ArrayList<IndividualPlayerModel>> getInternationalFilterPlayers(int choice) {


        MutableLiveData<ArrayList<IndividualPlayerModel>> data = new MutableLiveData<>();
        ArrayList<IndividualPlayerModel> players = localTempPlayers;
        ArrayList<IndividualPlayerModel> filteredPlayers = new ArrayList<>();

        if(choice == 1) {
            for(IndividualPlayerModel player : players) {
                if(player.getCountry().equals("USA")) {
                    filteredPlayers.add(player);
                }
            }
        }
        else if (choice == 2) {
            for(IndividualPlayerModel player : players) {
                if(!player.getCountry().equals("USA")) {
                    filteredPlayers.add(player);
                }
            }
        }
        else {
            filteredPlayers = players;
        }

        data.postValue(filteredPlayers);

        return data;
    }

}
