package com.aston.basketballapp;

import static org.junit.Assert.fail;

import android.content.SharedPreferences;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.engine.model.player.PlayerModel;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModel;
import com.aston.basketballapp.engine.model.standings.StandingsModel;
import com.aston.basketballapp.engine.repository.players.PlayersRepository;
import com.aston.basketballapp.engine.repository.standings.StandingsRepository;
import com.aston.basketballapp.pages.home.main.MainFragmentViewModel;
import com.aston.basketballapp.pages.home.players.PlayerBaseViewModel;
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

    String getPlayerJson = "{\"api\":{\"status\":200,\"message\":\"GET players\\/playerId\\/1\",\"results\":1,\"filters\":[\"playerId\",\"teamId\",\"league\",\"country\",\"lastName\",\"firstName\"],\"players\":[{\"firstName\":\"Alex\",\"lastName\":\"Abrines\",\"teamId\":null,\"yearsPro\":\"0\",\"collegeName\":\"\",\"country\":\"Spain\",\"playerId\":\"1\",\"dateOfBirth\":\"1993-08-01\",\"affiliation\":\"Spain\\/Spain\",\"startNba\":\"2016\",\"heightInMeters\":\"\",\"weightInKilograms\":\"\",\"leagues\":{\"standard\":{\"jersey\":\"8\",\"active\":\"0\",\"pos\":\"\"}}}]}}";
    String getPlayerStatsJson = "{\"api\":{\"status\":200,\"message\":\"GET statistics/players/playerId/1\",\"results\":222,\"filters\":[\"gameId\",\"playerId\"],\"statistics\":[{\"gameId\":\"1431\",\"teamId\":\"25\",\"points\":\"12\",\"pos\":\"\",\"min\":\"25:04\",\"fgm\":\"4\",\"fga\":\"5\",\"fgp\":\"80.0\",\"ftm\":\"0\",\"fta\":\"0\",\"ftp\":\"0.0\",\"tpm\":\"4\",\"tpa\":\"5\",\"tpp\":\"80.0\",\"offReb\":\"1\",\"defReb\":\"0\",\"totReb\":\"1\",\"assists\":\"0\",\"pFouls\":\"3\",\"steals\":\"0\",\"turnovers\":\"0\",\"blocks\":\"0\",\"plusMinus\":\"-1\",\"playerId\":\"1\"}]}}";


    @Before
    public void before() {
        repository = Mockito.mock(PlayersRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(repository.getAllPlayers()).thenReturn(mockAPIGetAllPlayers());
        Mockito.when(repository.getPlayer("playerID")).thenReturn(mockAPIGetPlayer());
        Mockito.when(repository.getPlayers("teamID")).thenReturn(mockAPIGetPlayers());
        Mockito.when(repository.getPlayerStats("playerId")).thenReturn(mockAPIGetPlayerStats());
    }

    Call<PlayerModel> mockAPIGetAllPlayers() {
        Gson gson = new Gson();
        PlayerModel model = gson.fromJson(getPlayerJson, PlayerModel.class);
        return Calls.response(Response.success(model));
    }

    Call<PlayerModel> mockAPIGetPlayer() {
        Gson gson = new Gson();
        PlayerModel model = gson.fromJson(getPlayerJson, PlayerModel.class);
        return Calls.response(Response.success(model));
    }

    Call<PlayerModel> mockAPIGetPlayers() {
        Gson gson = new Gson();
        PlayerModel model = gson.fromJson(getPlayerJson, PlayerModel.class);
        return Calls.response(Response.success(model));
    }

    Call<PlayerStatsModel> mockAPIGetPlayerStats() {
        Gson gson = new Gson();
        PlayerStatsModel model = gson.fromJson(getPlayerStatsJson, PlayerStatsModel.class);
        return Calls.response(Response.success(model));
    }

    @Test
    public void mockAPICallForGetAllPlayers() {
        PlayerBaseViewModel viewModel = new PlayerBaseViewModel(repository);
        StateMutableLiveData<ArrayList<IndividualPlayerModel>> data = viewModel.getAllPlayers();
        if(data.getValue() == null) {
            fail("API CALL for Get All Players Failed");
        }
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
