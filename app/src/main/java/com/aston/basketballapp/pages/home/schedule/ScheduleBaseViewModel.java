package com.aston.basketballapp.pages.home.schedule;

import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.engine.model.schedule.GamesModel;
import com.aston.basketballapp.engine.model.schedule.ScheduleModel;
import com.aston.basketballapp.engine.repository.schedule.ScheduleRepository;
import com.aston.basketballapp.utils.livedata.StateMutableLiveData;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ScheduleBaseViewModel extends ViewModel  {

    ScheduleRepository repository;
    @Inject
    ScheduleBaseViewModel(@Named("ScheduleRepository") ScheduleRepository exampleRepository) {
        repository = exampleRepository;
    }

    StateMutableLiveData<ArrayList<GamesModel>> getGamesOnDate(String date) {

        StateMutableLiveData<ArrayList<GamesModel>> data = new StateMutableLiveData<>();
        data.postValueLoading();

        repository.getGames(date).enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(Call<ScheduleModel> call, Response<ScheduleModel> response) {
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                } else {
                    ScheduleModel model = response.body();
                    ArrayList<GamesModel> games = model.getApi().getGames();
                    data.postValueSuccess(games);
                }

            }

            @Override
            public void onFailure(Call<ScheduleModel> call, Throwable t) {
                data.postValueError(t);
            }

        });
        return data;
    }
}
