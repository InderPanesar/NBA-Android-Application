package com.aston.basketballapp.pages.home.schedule;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.engine.model.schedule.schedule.GamesModel;
import com.aston.basketballapp.engine.model.schedule.schedule.ScheduleModelApi;
import com.aston.basketballapp.engine.repository.schedule.ScheduleRepository;
import com.aston.basketballapp.utils.livedata.StateMutableLiveData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ScheduleBaseViewModel extends ViewModel  {

    Calendar c;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    ScheduleRepository repository;
    @Inject
    public ScheduleBaseViewModel(@Named("ScheduleRepository") ScheduleRepository exampleRepository) {
        repository = exampleRepository;
    }

    //Return games on a specific date.
    public StateMutableLiveData<ArrayList<GamesModel>> getGamesOnDate(String date) {

        StateMutableLiveData<ArrayList<GamesModel>> data = new StateMutableLiveData<>();
        data.postValueLoading();

        repository.getGames(date).enqueue(new Callback<ScheduleModelApi>() {
            @Override
            public void onResponse(@NonNull Call<ScheduleModelApi> call, @NonNull Response<ScheduleModelApi> response) {
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                } else {
                    ScheduleModelApi model = response.body();
                    if(model != null) {
                        ArrayList<GamesModel> games = model.getGames();
                        data.postValueSuccess(games);
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<ScheduleModelApi> call, @NonNull Throwable t) {
                data.postValueError(t);
            }

        });
        return data;
    }

    String getCurrentDayString() {
        c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        return "Date: " + mDay + "/" + mMonth + "/" + mYear;
    }

    public String getDateString(int dayOfMonth, int month, int year) {
        return "Date: " + dayOfMonth + "/" + (month) + "/" + (year);
    }
}
