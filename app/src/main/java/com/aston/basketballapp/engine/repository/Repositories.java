package com.aston.basketballapp.engine.repository;

import com.aston.basketballapp.engine.repository.players.PlayersRepository;
import com.aston.basketballapp.engine.repository.players.PlayersRepositoryImpl;
import com.aston.basketballapp.engine.repository.schedule.ScheduleRepository;
import com.aston.basketballapp.engine.repository.schedule.ScheduleRepositoryImpl;
import com.aston.basketballapp.engine.repository.standings.StandingsRepository;
import com.aston.basketballapp.engine.repository.standings.StandingsRepositoryImpl;
import com.aston.basketballapp.engine.repository.teams.TeamsRepository;
import com.aston.basketballapp.engine.repository.teams.TeamsRepositoryImpl;
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

//Where all Repositories are registered
@Module
@InstallIn(SingletonComponent.class)
public class Repositories {

    //API Host value
    private final String HOST = "api-nba-v1.p.rapidapi.com";
    //API Key Value
    private final String KEY = "5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424";

    //Creates a retrofit model to make API calls to the API-NBA-V1 API (rapidAPI)
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
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://"+HOST).client(httpClient.build()).build();
    }

    //Register teams repository as singleton to allow injection into ViewModels
    @Singleton
    @Provides
    @Named("TeamsRepository")
    TeamsRepository provideTeamsRepository() {
        return new TeamsRepositoryImpl(provideRetrofit());
    }

    //Register players repository as singleton to allow injection into ViewModels
    @Singleton
    @Provides
    @Named("PlayersRepository")
    PlayersRepository providePlayersRepository() { return new PlayersRepositoryImpl(provideRetrofit()); }

    //Register schedule repository as singleton to allow injection into ViewModels
    @Singleton
    @Provides
    @Named("ScheduleRepository")
    ScheduleRepository provideScheduleRepository() { return new ScheduleRepositoryImpl(provideRetrofit()); }

    //Register standings repository as singleton to allow injection into ViewModels
    @Singleton
    @Provides
    @Named("StandingsRepository")
    StandingsRepository provideStandingsRepository() { return new StandingsRepositoryImpl(provideRetrofit()); }
}


