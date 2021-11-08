package com.aston.basicarchitecture.engine.comms.api;

import com.aston.basicarchitecture.engine.model.ExampleModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ExampleAPI {

    @GET("posts")
    Call<List<ExampleModel>> getPosts();
}
