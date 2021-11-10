package com.aston.basicarchitecture.engine.repository;

import com.aston.basicarchitecture.engine.comms.api.ExampleAPI;
import com.aston.basicarchitecture.engine.model.ExampleModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;

public class ExampleRepositoryImpl implements ExampleRepository {

    Retrofit retrofit;

    public ExampleRepositoryImpl(Retrofit _retrofit) {
        retrofit = _retrofit;
    }

    @Override
    public Call<List<ExampleModel>> getPosts() {
        ExampleAPI api = retrofit.create(ExampleAPI.class);
        return api.getPosts();
    }
}
