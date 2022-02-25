package com.aston.basketballapp;

import static org.junit.Assert.fail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.engine.model.player.PlayerModelApi;
import com.aston.basketballapp.engine.model.teams.IndividualTeamsModel;
import com.aston.basketballapp.engine.model.teams.TeamsModelAPI;
import com.aston.basketballapp.engine.repository.players.PlayersRepository;
import com.aston.basketballapp.engine.repository.teams.TeamsRepository;
import com.aston.basketballapp.pages.home.teams.TeamsBaseViewModel;
import com.aston.basketballapp.pages.home.teams.dialog.TeamDialogViewModel;
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
public class TeamsViewModelsUnitTest {

    @Mock
    TeamsRepository teamsRepository;

    @Mock
    PlayersRepository playersRepository;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    String getTeamJson = "{\"get\":\"teams\\/\",\"parameters\":{\"id\":\"1\"},\"errors\":[],\"results\":1,\"response\":[{\"id\":1,\"name\":\"Atlanta Hawks\",\"nickname\":\"Hawks\",\"code\":\"ATL\",\"city\":\"Atlanta\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/e\\/ee\\/Hawks_2016.png\",\"allStar\":false,\"nbaFranchise\":true,\"leagues\":{\"standard\":{\"conference\":\"East\",\"division\":\"Southeast\"},\"vegas\":{\"conference\":\"East\",\"division\":\"Southeast\"},\"utah\":{\"conference\":\"East\",\"division\":\"Southeast\"},\"sacramento\":{\"conference\":\"East\",\"division\":\"Southeast\"}}}]}";
    String getPlayerJson = "{\"get\":\"players\\/\",\"parameters\":{\"id\":\"1\"},\"errors\":[],\"results\":1,\"response\":[{\"id\":1,\"firstname\":\"Alex\",\"lastname\":\"Abrines\",\"birth\":{\"date\":\"1993-08-01\",\"country\":\"Spain\"},\"nba\":{\"start\":2016,\"pro\":0},\"height\":{\"feets\":null,\"inches\":null,\"meters\":null},\"weight\":{\"pounds\":null,\"kilograms\":null},\"college\":null,\"affiliation\":\"Spain\\/Spain\",\"leagues\":{\"standard\":{\"jersey\":8,\"active\":false,\"pos\":null}}}]}";


    @Before
    public void before() {
        teamsRepository = Mockito.mock(TeamsRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(teamsRepository.getTeams("east")).thenReturn(mockAPIGetTeams());
        Mockito.when(teamsRepository.getTeam("teamId")).thenReturn(mockAPIGetTeam());

        playersRepository = Mockito.mock(PlayersRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(playersRepository.getPlayers("teamID")).thenReturn(mockAPIGetPlayers());
    }

    Call<TeamsModelAPI> mockAPIGetTeams() {
        Gson gson = new Gson();
        TeamsModelAPI model = gson.fromJson(getTeamJson, TeamsModelAPI.class);
        return Calls.response(Response.success(model));
    }

    Call<TeamsModelAPI> mockAPIGetTeam() {
        Gson gson = new Gson();
        TeamsModelAPI model = gson.fromJson(getTeamJson, TeamsModelAPI.class);
        return Calls.response(Response.success(model));
    }

    Call<PlayerModelApi> mockAPIGetPlayers() {
        Gson gson = new Gson();
        PlayerModelApi model = gson.fromJson(getPlayerJson, PlayerModelApi.class);
        return Calls.response(Response.success(model));
    }

    @Test
    public void mockAPICallForGetTeams() {
        TeamsBaseViewModel viewModel = new TeamsBaseViewModel(teamsRepository);
        StateMutableLiveData<ArrayList<IndividualTeamsModel>> data = viewModel.getTeams();
        if(data.getValue() == null) {
            fail("Mock API CALL for Get Teams Failed");
        }
    }

    @Test
    public void mockAPICallForGetTeam() {
        TeamDialogViewModel viewModel = new TeamDialogViewModel(playersRepository);
        StateMutableLiveData<ArrayList<IndividualPlayerModel>> data = viewModel.getPlayers("teamID");
        if(data.getValue() == null) {
            fail("Mock API CALL for Get Team Failed");
        }
    }



}
