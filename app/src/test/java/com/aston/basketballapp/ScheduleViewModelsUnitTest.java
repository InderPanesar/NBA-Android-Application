package com.aston.basketballapp;

import static org.junit.Assert.fail;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.aston.basketballapp.engine.model.schedule.GamesModel;
import com.aston.basketballapp.engine.model.schedule.ScheduleModel;
import com.aston.basketballapp.engine.model.schedule.game.GameModel;
import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModel;
import com.aston.basketballapp.engine.repository.schedule.ScheduleRepository;
import com.aston.basketballapp.pages.home.schedule.ScheduleBaseViewModel;
import com.aston.basketballapp.pages.home.schedule.detail.ScheduleBottomSheetViewModel;
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
public class ScheduleViewModelsUnitTest {

    @Mock
    ScheduleRepository repository;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    String getGamesJSON = "{\"api\":{\"status\":200,\"message\":\"GET games/date/2021-12-13\",\"results\":4,\"filters\":[\"seasonYear\",\"league\",\"gameId\",\"teamId\",\"date\",\"live\"],\"games\":[{\"seasonYear\":\"2021\",\"league\":\"standard\",\"gameId\":\"9186\",\"startTimeUTC\":\"2021-12-13T00:00:00.000Z\",\"endTimeUTC\":\"2021-12-13T02:15:00.000Z\",\"arena\":\"Paycom Center\",\"city\":\"Oklahoma City\",\"country\":\"USA\",\"clock\":\"\",\"gameDuration\":\"2:04\",\"currentPeriod\":\"4/4\",\"halftime\":\"0\",\"EndOfPeriod\":\"0\",\"seasonStage\":\"2\",\"statusShortGame\":\"3\",\"statusGame\":\"Finished\",\"vTeam\":{\"teamId\":\"8\",\"shortName\":\"DAL\",\"fullName\":\"Dallas Mavericks\",\"nickName\":\"Mavericks\",\"logo\":\"https://upload.wikimedia.org/wikipedia/fr/thumb/b/b8/Mavericks_de_Dallas_logo.svg/150px-Mavericks_de_Dallas_logo.svg.png\",\"score\":{\"points\":\"103\"}},\"hTeam\":{\"teamId\":\"25\",\"shortName\":\"OKC\",\"fullName\":\"Oklahoma City Thunder\",\"nickName\":\"Thunder\",\"logo\":\"https://upload.wikimedia.org/wikipedia/fr/thumb/4/4f/Thunder_d%27Oklahoma_City_logo.svg/1200px-Thunder_d%27Oklahoma_City_logo.svg.png\",\"score\":{\"points\":\"84\"}}}]}}";
    String getGameDetailsJSON = "{\"api\":{\"status\":200,\"message\":\"GET gameDetails\\/1\",\"results\":1,\"filters\":[\"\"],\"game\":[{\"seasonYear\":\"2015\",\"league\":\"standard\",\"gameId\":\"1\",\"startTimeUTC\":\"2015-10-03T02:30:00.000Z\",\"endTimeUTC\":\"2015-10-03T05:01:00.000Z\",\"arena\":\"Staples Center\",\"city\":\"Los Angeles\",\"country\":\"\",\"clock\":\"\",\"gameDuration\":\"2:21\",\"timesTied\":\"2\",\"leadChanges\":\"0\",\"currentPeriod\":\"4\\/4\",\"halftime\":\"0\",\"EndOfPeriod\":\"0\",\"seasonStage\":\"1\",\"statusShortGame\":\"3\",\"statusGame\":\"Finished\",\"vTeam\":{\"fullName\":\"Denver Nuggets\",\"teamId\":\"9\",\"nickname\":\"Nuggets\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/3\\/35\\/Nuggets_de_Denver_2018.png\\/180px-Nuggets_de_Denver_2018.png\",\"shortName\":\"DEN\",\"allStar\":\"0\",\"nbaFranchise\":\"1\",\"score\":{\"win\":\"0\",\"loss\":\"1\",\"seriesWin\":\"0\",\"seriesLoss\":\"0\",\"linescore\":[\"17\",\"25\",\"23\",\"31\"],\"points\":\"96\"},\"leaders\":[{\"points\":\"16\",\"playerId\":\"307\",\"name\":\"Joffrey Lauvergne\"},{\"rebounds\":\"13\",\"playerId\":\"38\",\"name\":\"Will Barton\"},{\"assists\":\"7\",\"playerId\":\"388\",\"name\":\"Jameer Nelson\"}]},\"hTeam\":{\"fullName\":\"LA Clippers\",\"teamId\":\"16\",\"nickname\":\"Clippers\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/d\\/d6\\/Los_Angeles_Clippers_logo_2010.png\",\"shortName\":\"LAC\",\"allStar\":\"0\",\"nbaFranchise\":\"1\",\"score\":{\"win\":\"1\",\"loss\":\"0\",\"seriesWin\":\"0\",\"seriesLoss\":\"0\",\"linescore\":[\"22\",\"34\",\"28\",\"19\"],\"points\":\"103\"},\"leaders\":[{\"points\":\"15\",\"playerId\":\"286\",\"name\":\"DeAndre Jordan\"},{\"rebounds\":\"12\",\"playerId\":\"286\",\"name\":\"DeAndre Jordan\"},{\"assists\":\"9\",\"playerId\":\"415\",\"name\":\"Chris Paul\"}]},\"officials\":[{\"name\":\"Ron Garretson\"},{\"name\":\"Tre Maddox\"},{\"name\":\"Rodney Mott\"}]}]}}";
    String getGameStatisticJSON = "{\"api\":{\"status\":200,\"message\":\"GET statistics\\/games\\/gameId\\/1\",\"results\":2,\"filters\":[\"gameId\"],\"statistics\":[{\"gameId\":\"1\",\"teamId\":\"9\",\"fastBreakPoints\":\"18\",\"pointsInPaint\":\"46\",\"biggestLead\":\"0\",\"secondChancePoints\":\"\",\"pointsOffTurnovers\":\"12\",\"longestRun\":\"\",\"points\":\"96\",\"fgm\":\"37\",\"fga\":\"87\",\"fgp\":\"42.5\",\"ftm\":\"15\",\"fta\":\"20\",\"ftp\":\"75.0\",\"tpm\":\"7\",\"tpa\":\"18\",\"tpp\":\"38.9\",\"offReb\":\"14\",\"defReb\":\"43\",\"totReb\":\"57\",\"assists\":\"16\",\"pFouls\":\"26\",\"steals\":\"9\",\"turnovers\":\"23\",\"blocks\":\"2\",\"plusMinus\":\"-7\",\"min\":\"240:00\"},{\"gameId\":\"1\",\"teamId\":\"16\",\"fastBreakPoints\":\"19\",\"pointsInPaint\":\"38\",\"biggestLead\":\"21\",\"secondChancePoints\":\"\",\"pointsOffTurnovers\":\"23\",\"longestRun\":\"\",\"points\":\"103\",\"fgm\":\"37\",\"fga\":\"91\",\"fgp\":\"40.7\",\"ftm\":\"20\",\"fta\":\"26\",\"ftp\":\"76.9\",\"tpm\":\"9\",\"tpa\":\"32\",\"tpp\":\"28.1\",\"offReb\":\"9\",\"defReb\":\"30\",\"totReb\":\"39\",\"assists\":\"22\",\"pFouls\":\"15\",\"steals\":\"12\",\"turnovers\":\"13\",\"blocks\":\"6\",\"plusMinus\":\"7\",\"min\":\"240:00\"}]}}";


    @Before
    public void before() {
        repository = Mockito.mock(ScheduleRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(repository.getGames("0")).thenReturn(mockAPIGetAllGames());
        Mockito.when(repository.getGameDetails("gameId")).thenReturn(mockAPIGetGameDetails());
        Mockito.when(repository.getGameStatisticDetails("gameId")).thenReturn(mockAPIGetGameStats());
    }

    Call<ScheduleModel> mockAPIGetAllGames() {
        Gson gson = new Gson();
        ScheduleModel model = gson.fromJson(getGamesJSON, ScheduleModel.class);
        return Calls.response(Response.success(model));
    }

    Call<GameModel> mockAPIGetGameDetails() {
        Gson gson = new Gson();
        GameModel model = gson.fromJson(getGameDetailsJSON, GameModel.class);
        return Calls.response(Response.success(model));
    }

    Call<GameStatisticModel> mockAPIGetGameStats() {
        Gson gson = new Gson();
        GameStatisticModel model = gson.fromJson(getGameStatisticJSON, GameStatisticModel.class);
        return Calls.response(Response.success(model));
    }

    @Test
    public void mockAPICallForGetGamesOnDate() {
        ScheduleBaseViewModel viewModel = new ScheduleBaseViewModel(repository);
        StateMutableLiveData<ArrayList<GamesModel>> data = viewModel.getGamesOnDate("0");
        if(data.getValue() == null) {
            fail("API CALL for Get All Players Failed");
        }
    }

    @Test
    public void mockAPICallForGetGameStats() {
        ScheduleBottomSheetViewModel viewModel = new ScheduleBottomSheetViewModel(repository);
        StateMutableLiveData<ArrayList<String>> data = viewModel.getGameStatistics("gameId");
        if(data.getValue() == null) {
            fail("API CALL for Get All Players Failed");
        }
    }
}
