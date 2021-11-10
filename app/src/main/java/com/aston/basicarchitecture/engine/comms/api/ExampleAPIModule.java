package com.aston.basicarchitecture.engine.comms.api;

import com.aston.basicarchitecture.engine.repository.ExampleRepository;
import com.aston.basicarchitecture.engine.repository.ExampleRepositoryImpl;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ExampleAPIModule {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Singleton
    @Provides
    @Named("AppRetrofit")
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Singleton
    @Provides
    @Named("ExampleRepository")
    ExampleRepository provideExampleRepository() {
        return new ExampleRepositoryImpl(provideRetrofit());
    }
}
