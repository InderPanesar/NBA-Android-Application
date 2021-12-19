package com.aston.basicarchitecture.engine.repository;

import com.aston.basicarchitecture.engine.model.ExampleModel;
import java.util.List;
import retrofit2.Call;

public interface ExampleRepository {
    Call<ExampleModel> getSeason();
}

