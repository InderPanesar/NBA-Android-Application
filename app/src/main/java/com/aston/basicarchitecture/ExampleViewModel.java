package com.aston.basicarchitecture;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.aston.basicarchitecture.engine.model.ExampleModel;
import com.aston.basicarchitecture.engine.repository.ExampleRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ExampleViewModel extends ViewModel {

    ExampleRepository repository;
    @Inject
    ExampleViewModel(@Named("ExampleRepository") ExampleRepository exampleRepository) {
        repository = exampleRepository;
    }

    LiveData<String> getList() {
        MutableLiveData<String> data = new MutableLiveData<>();
        Log.d("HIT", "GOT LIST");
        repository.getSeason().enqueue(new Callback<ExampleModel>() {
            @Override
            public void onResponse(Call<ExampleModel> call, Response<ExampleModel> response) {
              if(!response.isSuccessful()) {
                  data.postValue("Code: " + response.code());
                  return;
              }
              ExampleModel model = response.body();
              String content = "";
              content += "Status: " + model.getApi().getStatus() + "\n";
              content += "Message: " + model.getApi().getMessage() + "\n";
              content += "Results: " + model.getApi().getResults() + "\n\n";
              content += "Seasons: " + "\n";
              for(String season : model.getApi().getSeasons()) {
                  content += season + "\n";
              }
              data.postValue(content);

            }

            @Override
            public void onFailure(Call<ExampleModel> call, Throwable t) {
                data.postValue("Error:" + t.getMessage());

            }
        });
        return data;

    }
}
