package com.aston.basicarchitecture.engine.repository;

import com.aston.basicarchitecture.engine.comms.api.ExampleAPI;
import com.aston.basicarchitecture.engine.di.Dependencies;
import com.aston.basicarchitecture.engine.model.ExampleModel;
import java.util.List;
import retrofit2.Call;

public class ExampleRepositoryImpl implements ExampleRepository {
    @Override
    public Call<List<ExampleModel>> getPosts() {
        ExampleAPI api = Dependencies.getInstance().getRetrofit().create(ExampleAPI.class);
        return api.getPosts();
    }
}
