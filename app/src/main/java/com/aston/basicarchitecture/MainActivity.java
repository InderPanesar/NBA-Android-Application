package com.aston.basicarchitecture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.aston.basicarchitecture.engine.comms.api.ExampleAPIModule;
import com.aston.basicarchitecture.engine.model.ExampleModel;
import com.aston.basicarchitecture.engine.repository.ExampleRepository;
import com.aston.basicarchitecture.engine.repository.ExampleRepositoryImpl;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    @Inject
    @Named(value="ExampleRepository")
    ExampleRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult = findViewById(R.id.text_view_result);


        repository.getPosts().enqueue(new Callback<List<ExampleModel>>() {
                @Override
                public void onResponse(Call<List<ExampleModel>> call, Response<List<ExampleModel>> response) {
                    if(!response.isSuccessful()) {
                        textViewResult.setText("Code: " + response.code());
                        return;
                    }
                    List<ExampleModel> models = response.body();
                    for(ExampleModel model : models) {
                        String content = "";
                        content += "ID: " + model.getId() + "\n";
                        content += "User ID: " + model.getUserId() + "\n";
                        content += "Title: " + model.getTitle() + "\n";
                        content += "Text: " + model.getText() + "\n\n";
                        textViewResult.append(content);
                    }

                }

                @Override
                public void onFailure(Call<List<ExampleModel>> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            }
        );


    }
}