package com.aston.basketballapp.pages.home.schedule.detail;

import androidx.lifecycle.ViewModel;

import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModel;
import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModelAPI;
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
public class ScheduleBottomSheetViewModel extends ViewModel {

    ScheduleRepository repository;
    @Inject
    ScheduleBottomSheetViewModel(@Named("ScheduleRepository") ScheduleRepository exampleRepository) {
        repository = exampleRepository;
    }

    StateMutableLiveData<ArrayList<String>> getGameStatistics(String gameID) {

        StateMutableLiveData<ArrayList<String>> data = new StateMutableLiveData<>();
        data.postValueLoading();

        repository.getGameStatisticDetails(gameID).enqueue(new Callback<GameStatisticModel>() {
            @Override
            public void onResponse(Call<GameStatisticModel> call, Response<GameStatisticModel> response) {
                if (!response.isSuccessful()) {
                    data.postValueError(null);
                } else {
                    GameStatisticModelAPI model = response.body().getApi();
                    if(model.getStatistics().size() < 2) {
                        data.postValueError(new Throwable());
                    }
                    ArrayList<String> games = new ArrayList<>();

                    games.add(model.getStatistics().get(1).getFastBreakPoints());
                    games.add(model.getStatistics().get(1).getPointsInPaint());
                    games.add(model.getStatistics().get(1).getBiggestLead());
                    games.add(model.getStatistics().get(1).getPointsOffTurnovers());
                    games.add(model.getStatistics().get(1).getFtp());
                    games.add(model.getStatistics().get(1).getOffReb());
                    games.add(model.getStatistics().get(1).getDefReb());
                    games.add(model.getStatistics().get(1).getAssists());
                    games.add(model.getStatistics().get(1).getPlusMinus());

                    games.add(model.getStatistics().get(0).getFastBreakPoints());
                    games.add(model.getStatistics().get(0).getPointsInPaint());
                    games.add(model.getStatistics().get(0).getBiggestLead());
                    games.add(model.getStatistics().get(0).getPointsOffTurnovers());
                    games.add(model.getStatistics().get(0).getFtp());
                    games.add(model.getStatistics().get(0).getOffReb());
                    games.add(model.getStatistics().get(0).getDefReb());
                    games.add(model.getStatistics().get(0).getAssists());
                    games.add(model.getStatistics().get(0).getPlusMinus());



                    data.postValueSuccess(games);
                }

            }

            @Override
            public void onFailure(Call<GameStatisticModel> call, Throwable t) {
                data.postValueError(t);
            }

        });
        return data;
    }

}
