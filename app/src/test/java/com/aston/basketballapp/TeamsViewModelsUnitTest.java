package com.aston.basketballapp;

import static org.junit.Assert.fail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.engine.model.player.PlayerModel;
import com.aston.basketballapp.engine.model.schedule.GamesModel;
import com.aston.basketballapp.engine.model.schedule.ScheduleModel;
import com.aston.basketballapp.engine.model.schedule.game.GameModel;
import com.aston.basketballapp.engine.model.teams.IndividualTeamsModel;
import com.aston.basketballapp.engine.model.teams.TeamsModel;
import com.aston.basketballapp.engine.repository.players.PlayersRepository;
import com.aston.basketballapp.engine.repository.schedule.ScheduleRepository;
import com.aston.basketballapp.engine.repository.teams.TeamsRepository;
import com.aston.basketballapp.pages.home.schedule.ScheduleBaseViewModel;
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


    String getTeamsJson = "{\"api\":{\"status\":200,\"message\":\"GET teams/confName/east\",\"results\":20,\"filters\":[\"teamId\",\"league\",\"city\",\"shortName\",\"nickName\",\"confName\",\"divName\"],\"teams\":[{\"city\":\"Atlanta\",\"fullName\":\"Atlanta Hawks\",\"teamId\":\"1\",\"nickname\":\"Hawks\",\"logo\":\"https://upload.wikimedia.org/wikipedia/fr/e/ee/Hawks_2016.png\",\"shortName\":\"ATL\",\"allStar\":\"0\",\"nbaFranchise\":\"1\",\"leagues\":{\"standard\":{\"confName\":\"East\",\"divName\":\"Southeast\"},\"vegas\":{\"confName\":\"East\",\"divName\":\"Southeast\"},\"utah\":{\"confName\":\"East\",\"divName\":\"Southeast\"},\"sacramento\":{\"confName\":\"East\",\"divName\":\"Southeast\"}}}]}}";
    String getTeamJson = "{\"api\":{\"status\":200,\"message\":\"GET teams\\/teamId\\/1\",\"results\":1,\"filters\":[\"teamId\",\"league\",\"city\",\"shortName\",\"nickName\",\"confName\",\"divName\"],\"teams\":[{\"city\":\"Atlanta\",\"fullName\":\"Atlanta Hawks\",\"teamId\":\"1\",\"nickname\":\"Hawks\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/e\\/ee\\/Hawks_2016.png\",\"shortName\":\"ATL\",\"allStar\":\"0\",\"nbaFranchise\":\"1\",\"leagues\":{\"standard\":{\"confName\":\"East\",\"divName\":\"Southeast\"},\"vegas\":{\"confName\":\"East\",\"divName\":\"Southeast\"},\"utah\":{\"confName\":\"East\",\"divName\":\"Southeast\"},\"sacramento\":{\"confName\":\"East\",\"divName\":\"Southeast\"}}}]}}";
    String getPlayerJson = "{\"api\":{\"status\":200,\"message\":\"GET players\\/playerId\\/1\",\"results\":1,\"filters\":[\"playerId\",\"teamId\",\"league\",\"country\",\"lastName\",\"firstName\"],\"players\":[{\"firstName\":\"Alex\",\"lastName\":\"Abrines\",\"teamId\":null,\"yearsPro\":\"0\",\"collegeName\":\"\",\"country\":\"Spain\",\"playerId\":\"1\",\"dateOfBirth\":\"1993-08-01\",\"affiliation\":\"Spain\\/Spain\",\"startNba\":\"2016\",\"heightInMeters\":\"\",\"weightInKilograms\":\"\",\"leagues\":{\"standard\":{\"jersey\":\"8\",\"active\":\"0\",\"pos\":\"\"}}}]}}";


    @Before
    public void before() {
        teamsRepository = Mockito.mock(TeamsRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(teamsRepository.getTeams("east")).thenReturn(mockAPIGetTeams());
        Mockito.when(teamsRepository.getTeam("teamId")).thenReturn(mockAPIGetTeam());

        playersRepository = Mockito.mock(PlayersRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(playersRepository.getPlayers("teamID")).thenReturn(mockAPIGetPlayers());
    }

    Call<TeamsModel> mockAPIGetTeams() {
        Gson gson = new Gson();
        TeamsModel model = gson.fromJson(getTeamsJson, TeamsModel.class);
        return Calls.response(Response.success(model));
    }

    Call<TeamsModel> mockAPIGetTeam() {
        Gson gson = new Gson();
        TeamsModel model = gson.fromJson(getTeamJson, TeamsModel.class);
        return Calls.response(Response.success(model));
    }

    Call<PlayerModel> mockAPIGetPlayers() {
        Gson gson = new Gson();
        PlayerModel model = gson.fromJson(getPlayerJson, PlayerModel.class);
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