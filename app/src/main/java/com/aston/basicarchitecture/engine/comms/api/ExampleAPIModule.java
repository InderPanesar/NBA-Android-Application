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

    private final String BASE_URL = "https://api-nba-v1.p.rapidapi.com";


    @Singleton
    @Provides
    @Named("AppRetrofit")
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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


