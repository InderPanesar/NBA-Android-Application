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
        repository.getPosts().enqueue(new Callback<List<ExampleModel>>() {
            @Override
            public void onResponse(Call<List<ExampleModel>> call, Response<List<ExampleModel>> response) {
              if(!response.isSuccessful()) {
                  data.postValue("Code: " + response.code());
                  return;
              }
              List<ExampleModel> models = response.body();
                String content = "";
                for(ExampleModel model : models) {
                    content += "ID: " + model.getId() + "\n";
                    content += "User ID: " + model.getUserId() + "\n";
                    content += "Title: " + model.getTitle() + "\n";
                    content += "Text: " + model.getText() + "\n\n";
              }
                data.postValue(content);

            }

            @Override
            public void onFailure(Call<List<ExampleModel>> call, Throwable t) {
                data.postValue("Error:" + t.getMessage());

            }
        });
        return data;

    }
}
