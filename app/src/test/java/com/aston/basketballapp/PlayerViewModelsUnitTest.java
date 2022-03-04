package com.aston.basketballapp;

import static org.junit.Assert.fail;

import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.aston.basketballapp.engine.model.player.PlayerModelApi;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModelApi;
import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModelAPI;
import com.aston.basketballapp.engine.model.schedule.schedule.ScheduleModelApi;
import com.aston.basketballapp.engine.repository.players.PlayersRepository;
import com.aston.basketballapp.engine.repository.schedule.ScheduleRepository;
import com.aston.basketballapp.pages.home.players.detail.PlayersDetailViewModel;
import com.aston.basketballapp.pages.home.players.detail.SinglePlayerStatsAdapter;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.livedata.StateMutableLiveData;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.Calls;

@RunWith(MockitoJUnitRunner.class)
public class PlayerViewModelsUnitTest {

    @Mock
    PlayersRepository repository;
    @Mock
    ScheduleRepository scheduleRepository;
    @Mock
    SharedPreferences mockPrefs;
    @Mock
    SharedPreferences.Editor mockEditor;


    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    String getPlayerJson = "{\"get\":\"players\\/\",\"parameters\":{\"id\":\"1\"},\"errors\":[],\"results\":1,\"response\":[{\"id\":1,\"firstname\":\"Alex\",\"lastname\":\"Abrines\",\"birth\":{\"date\":\"1993-08-01\",\"country\":\"Spain\"},\"nba\":{\"start\":2016,\"pro\":0},\"height\":{\"feets\":null,\"inches\":null,\"meters\":null},\"weight\":{\"pounds\":null,\"kilograms\":null},\"college\":null,\"affiliation\":\"Spain\\/Spain\",\"leagues\":{\"standard\":{\"jersey\":8,\"active\":false,\"pos\":null}}}]}";
    String getPlayerStatsJson = "{\"get\":\"players\\/statistics\",\"parameters\":{\"season\":\"2021\",\"id\":\"236\"},\"errors\":[],\"results\":62,\"response\":[{\"player\":{\"id\":236,\"firstname\":\"Buddy\",\"lastname\":\"Hield\"},\"team\":{\"id\":15,\"name\":\"Indiana Pacers\",\"nickname\":\"Pacers\",\"code\":\"IND\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/c\\/cf\\/Pacers_de_l%27Indiana_logo.svg\\/1180px-Pacers_de_l%27Indiana_logo.svg.png\"},\"game\":{\"id\":10441},\"points\":15,\"pos\":\"SG\",\"min\":\"39:29\",\"fgm\":5,\"fga\":12,\"fgp\":\"41.7\",\"ftm\":4,\"fta\":4,\"ftp\":\"100\",\"tpm\":1,\"tpa\":5,\"tpp\":\"20.0\",\"offReb\":0,\"defReb\":5,\"totReb\":5,\"assists\":7,\"pFouls\":1,\"steals\":0,\"turnovers\":1,\"blocks\":1,\"plusMinus\":\"12\",\"comment\":null}]}";
    String getSpecificGameJson = "{\"get\":\"games\\/statistics\",\"parameters\":{\"id\":\"10441\"},\"errors\":[],\"results\":2,\"response\":[{\"team\":{\"id\":41,\"name\":\"Washington Wizards\",\"nickname\":\"Wizards\",\"code\":\"WAS\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/archive\\/d\\/d6\\/20161212034849%21Wizards2015.png\"},\"statistics\":[{\"fastBreakPoints\":15,\"pointsInPaint\":36,\"biggestLead\":7,\"secondChancePoints\":12,\"pointsOffTurnovers\":15,\"longestRun\":10,\"points\":108,\"fgm\":39,\"fga\":91,\"fgp\":\"42.9\",\"ftm\":13,\"fta\":16,\"ftp\":\"81.2\",\"tpm\":17,\"tpa\":37,\"tpp\":\"45.9\",\"offReb\":12,\"defReb\":32,\"totReb\":44,\"assists\":25,\"pFouls\":20,\"steals\":7,\"turnovers\":12,\"blocks\":3,\"plusMinus\":\"-5\",\"min\":\"240:00\"}]},{\"team\":{\"id\":15,\"name\":\"Indiana Pacers\",\"nickname\":\"Pacers\",\"code\":\"IND\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/c\\/cf\\/Pacers_de_l%27Indiana_logo.svg\\/1180px-Pacers_de_l%27Indiana_logo.svg.png\"},\"statistics\":[{\"fastBreakPoints\":20,\"pointsInPaint\":74,\"biggestLead\":13,\"secondChancePoints\":7,\"pointsOffTurnovers\":10,\"longestRun\":10,\"points\":113,\"fgm\":45,\"fga\":83,\"fgp\":\"54.2\",\"ftm\":19,\"fta\":29,\"ftp\":\"65.5\",\"tpm\":4,\"tpa\":20,\"tpp\":\"20.0\",\"offReb\":7,\"defReb\":33,\"totReb\":40,\"assists\":30,\"pFouls\":14,\"steals\":9,\"turnovers\":11,\"blocks\":6,\"plusMinus\":\"5\",\"min\":\"240:00\"}]}]}";

    @Before
    public void before() {
        repository = Mockito.mock(PlayersRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(repository.getAllPlayers()).thenReturn(mockAPIGetAllPlayers());
        Mockito.when(repository.getPlayer("playerID")).thenReturn(mockAPIGetPlayer());
        Mockito.when(repository.getPlayers("teamID")).thenReturn(mockAPIGetPlayers());
        Mockito.when(repository.getPlayerStats("playerId")).thenReturn(mockAPIGetPlayerStats());
        scheduleRepository = Mockito.mock(ScheduleRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(scheduleRepository.getGameStatisticDetails("10441")).thenReturn(mockAPIGetScheduleRepo());
    }

    Call<PlayerModelApi> mockAPIGetAllPlayers() {
        Gson gson = new Gson();
        PlayerModelApi model = gson.fromJson(getPlayerJson, PlayerModelApi.class);
        return Calls.response(Response.success(model));
    }

    Call<PlayerModelApi> mockAPIGetPlayer() {
        Gson gson = new Gson();
        PlayerModelApi model = gson.fromJson(getPlayerJson, PlayerModelApi.class);
        return Calls.response(Response.success(model));
    }

    Call<PlayerModelApi> mockAPIGetPlayers() {
        Gson gson = new Gson();
        PlayerModelApi model = gson.fromJson(getPlayerJson, PlayerModelApi.class);
        return Calls.response(Response.success(model));
    }

    Call<PlayerStatsModelApi> mockAPIGetPlayerStats() {
        Gson gson = new Gson();
        PlayerStatsModelApi model = gson.fromJson(getPlayerStatsJson, PlayerStatsModelApi.class);
        return Calls.response(Response.success(model));
    }

    Call<GameStatisticModelAPI> mockAPIGetScheduleRepo() {
        Gson gson = new Gson();
        GameStatisticModelAPI model = gson.fromJson(getSpecificGameJson, GameStatisticModelAPI.class);
        return Calls.response(Response.success(model));
    }


    @Test
    public void mockAPICallForGetPlayerStats() {
        PlayersDetailViewModel viewModel = new PlayersDetailViewModel(repository, scheduleRepository);
        mockPrefs.getInt(AppConsts.RECENT_GAMES_ONE, 1);
        mockPrefs.getInt(AppConsts.RECENT_GAMES_TWO, 2);
        mockPrefs.getInt(AppConsts.RECENT_GAMES_THREE, 3);
        mockPrefs.getInt(AppConsts.RECENT_GAMES_FOUR, 4);
        StateMutableLiveData<ArrayList<SinglePlayerStatsAdapter>> data = viewModel.getPlayerGameStats("playerId",mockPrefs );
        if(data.getValue() == null) {
            fail("API CALL for Get All Players Failed");
        }
    }

}
