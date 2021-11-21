package com.aston.basicarchitecture.engine.comms.api;

import com.aston.basicarchitecture.engine.model.ExampleModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ExampleAPI {
    @Headers({"x-rapidapi-host:api-nba-v1.p.rapidapi.com", "x-rapidapi-key:5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424"})
    @GET("seasons/")
    Call<ExampleModel> getSeasons();
}
