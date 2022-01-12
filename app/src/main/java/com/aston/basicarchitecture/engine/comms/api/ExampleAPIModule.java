package com.aston.basicarchitecture.engine.comms.api;

import com.aston.basicarchitecture.engine.repository.ExampleRepository;
import com.aston.basicarchitecture.engine.repository.ExampleRepositoryImpl;
import com.aston.basicarchitecture.engine.repository.players.PlayersRepository;
import com.aston.basicarchitecture.engine.repository.players.PlayersRepositoryImpl;
import com.aston.basicarchitecture.engine.repository.schedule.ScheduleRepository;
import com.aston.basicarchitecture.engine.repository.schedule.ScheduleRepositoryImpl;
import com.aston.basicarchitecture.engine.repository.standings.StandingsRepository;
import com.aston.basicarchitecture.engine.repository.standings.StandingsRepositoryImpl;
import com.aston.basicarchitecture.engine.repository.teams.TeamsRepository;
import com.aston.basicarchitecture.engine.repository.teams.TeamsRepositoryImpl;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ExampleAPIModule {

    private final String BASE_URL = "https://api-nba-v1.p.rapidapi.com";
    private final String HOST = "api-nba-v1.p.rapidapi.com";
    private final String KEY = "5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424";


    @Singleton
    @Provides
    @Named("AppRetrofit")
    Retrofit provideRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("x-rapidapi-host", HOST)
                        .addHeader("x-rapidapi-key", KEY)
                        .build();
                return chain.proceed(request);
            }
        });
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).client(httpClient.build()).build();
    }

    //ToDo: Remove this example repository.
    @Singleton
    @Provides
    @Named("ExampleRepository")
    ExampleRepository provideExampleRepository() {
        return new ExampleRepositoryImpl(provideRetrofit());
    }

    @Singleton
    @Provides
    @Named("TeamsRepository")
    TeamsRepository provideTeamsRepository() {
        return new TeamsRepositoryImpl(provideRetrofit());
    }

    @Singleton
    @Provides
    @Named("PlayersRepository")
    PlayersRepository providePlayersRepository() { return new PlayersRepositoryImpl(provideRetrofit()); }

    @Singleton
    @Provides
    @Named("ScheduleRepository")
    ScheduleRepository provideScheduleRepository() { return new ScheduleRepositoryImpl(provideRetrofit()); }

    @Singleton
    @Provides
    @Named("StandingsRepository")
    StandingsRepository provideStandingsRepository() { return new StandingsRepositoryImpl(provideRetrofit()); }
}


