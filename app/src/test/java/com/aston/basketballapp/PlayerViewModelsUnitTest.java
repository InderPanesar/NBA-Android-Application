package com.aston.basketballapp;

import static org.junit.Assert.fail;

import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.engine.model.player.PlayerModelApi;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModelApi;
import com.aston.basketballapp.engine.repository.players.PlayersRepository;
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
    SharedPreferences mockPrefs;
    @Mock
    SharedPreferences.Editor mockEditor;


    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    String getPlayerJson = "{\"get\":\"players\\/\",\"parameters\":{\"id\":\"1\"},\"errors\":[],\"results\":1,\"response\":[{\"id\":1,\"firstname\":\"Alex\",\"lastname\":\"Abrines\",\"birth\":{\"date\":\"1993-08-01\",\"country\":\"Spain\"},\"nba\":{\"start\":2016,\"pro\":0},\"height\":{\"feets\":null,\"inches\":null,\"meters\":null},\"weight\":{\"pounds\":null,\"kilograms\":null},\"college\":null,\"affiliation\":\"Spain\\/Spain\",\"leagues\":{\"standard\":{\"jersey\":8,\"active\":false,\"pos\":null}}}]}";
    String getPlayerStatsJson = "{\"get\":\"players\\/statistics\",\"parameters\":{\"season\":\"2021\",\"id\":\"236\"},\"errors\":[],\"results\":62,\"response\":[{\"player\":{\"id\":236,\"firstname\":\"Buddy\",\"lastname\":\"Hield\"},\"team\":{\"id\":15,\"name\":\"Indiana Pacers\",\"nickname\":\"Pacers\",\"code\":\"IND\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/c\\/cf\\/Pacers_de_l%27Indiana_logo.svg\\/1180px-Pacers_de_l%27Indiana_logo.svg.png\"},\"game\":{\"id\":10441},\"points\":15,\"pos\":\"SG\",\"min\":\"39:29\",\"fgm\":5,\"fga\":12,\"fgp\":\"41.7\",\"ftm\":4,\"fta\":4,\"ftp\":\"100\",\"tpm\":1,\"tpa\":5,\"tpp\":\"20.0\",\"offReb\":0,\"defReb\":5,\"totReb\":5,\"assists\":7,\"pFouls\":1,\"steals\":0,\"turnovers\":1,\"blocks\":1,\"plusMinus\":\"12\",\"comment\":null}]}";

    @Before
    public void before() {
        repository = Mockito.mock(PlayersRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(repository.getAllPlayers()).thenReturn(mockAPIGetAllPlayers());
        Mockito.when(repository.getPlayer("playerID")).thenReturn(mockAPIGetPlayer());
        Mockito.when(repository.getPlayers("teamID")).thenReturn(mockAPIGetPlayers());
        Mockito.when(repository.getPlayerStats("playerId")).thenReturn(mockAPIGetPlayerStats());
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


    @Test
    public void mockAPICallForGetPlayerStats() {
        PlayersDetailViewModel viewModel = new PlayersDetailViewModel(repository);
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
