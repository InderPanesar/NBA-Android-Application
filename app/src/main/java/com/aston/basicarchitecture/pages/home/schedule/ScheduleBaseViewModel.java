package com.aston.basicarchitecture.pages.home.schedule;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;
import com.aston.basicarchitecture.engine.model.player.PlayerModel;
import com.aston.basicarchitecture.engine.model.schedule.GamesModel;
import com.aston.basicarchitecture.engine.model.schedule.ScheduleModel;
import com.aston.basicarchitecture.engine.repository.schedule.ScheduleRepository;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ScheduleBaseViewModel extends ViewModel {

    ScheduleRepository repository;
    @Inject
    ScheduleBaseViewModel(@Named("ScheduleRepository") ScheduleRepository exampleRepository) {
        repository = exampleRepository;
    }

    LiveData<ArrayList<GamesModel>> getGamesOnDate(String date) {

        MutableLiveData<ArrayList<GamesModel>> data = new MutableLiveData<>();
        repository.getGames(date).enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(Call<ScheduleModel> call, Response<ScheduleModel> response) {
                if (!response.isSuccessful()) {
                    Log.d("UNSUCCESSFUL CALL", "" + response.code());
                } else {
                    ScheduleModel model = response.body();
                    ArrayList<GamesModel> games = model.getApi().getGames();
                    data.postValue(games);
                }

            }
            @Override
            public void onFailure(Call<ScheduleModel> call, Throwable t) {
                //Do Something here!
                Log.d("UNSUCCESSFUL CALL", "" + t.getLocalizedMessage());
            }

        });
        return data;
    }
}
