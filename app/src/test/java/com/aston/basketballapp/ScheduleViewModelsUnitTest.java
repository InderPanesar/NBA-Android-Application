package com.aston.basketballapp;

import static org.junit.Assert.fail;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.aston.basketballapp.engine.model.schedule.gameStatistics.GameStatisticModelAPI;
import com.aston.basketballapp.engine.model.schedule.schedule.GamesModel;
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

    String getGameStatisticJSON = "{\"get\":\"games\\/statistics\",\"parameters\":{\"id\":\"10403\"},\"errors\":[],\"results\":2,\"response\":[{\"team\":{\"id\":5,\"name\":\"Charlotte Hornets\",\"nickname\":\"Hornets\",\"code\":\"CHA\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/f\\/f3\\/Hornets_de_Charlotte_logo.svg\\/1200px-Hornets_de_Charlotte_logo.svg.png\"},\"statistics\":[{\"fastBreakPoints\":15,\"pointsInPaint\":70,\"biggestLead\":28,\"secondChancePoints\":18,\"pointsOffTurnovers\":24,\"longestRun\":12,\"points\":141,\"fgm\":54,\"fga\":97,\"fgp\":\"55.7\",\"ftm\":15,\"fta\":23,\"ftp\":\"65.2\",\"tpm\":18,\"tpa\":42,\"tpp\":\"42.9\",\"offReb\":15,\"defReb\":36,\"totReb\":51,\"assists\":36,\"pFouls\":22,\"steals\":13,\"turnovers\":18,\"blocks\":2,\"plusMinus\":\"22\",\"min\":\"240:00\"}]},{\"team\":{\"id\":10,\"name\":\"Detroit Pistons\",\"nickname\":\"Pistons\",\"code\":\"DET\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/commons\\/thumb\\/6\\/6a\\/Detroit_Pistons_primary_logo_2017.png\\/150px-Detroit_Pistons_primary_logo_2017.png\"},\"statistics\":[{\"fastBreakPoints\":8,\"pointsInPaint\":52,\"biggestLead\":2,\"secondChancePoints\":13,\"pointsOffTurnovers\":24,\"longestRun\":12,\"points\":119,\"fgm\":48,\"fga\":102,\"fgp\":\"47.1\",\"ftm\":11,\"fta\":18,\"ftp\":\"61.1\",\"tpm\":12,\"tpa\":35,\"tpp\":\"34.3\",\"offReb\":16,\"defReb\":29,\"totReb\":45,\"assists\":32,\"pFouls\":20,\"steals\":10,\"turnovers\":18,\"blocks\":6,\"plusMinus\":\"-22\",\"min\":\"240:00\"}]}]}";

    @Before
    public void before() {
        repository = Mockito.mock(ScheduleRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(repository.getGameStatisticDetails("gameId")).thenReturn(mockAPIGetGameStats());
    }


    Call<GameStatisticModelAPI> mockAPIGetGameStats() {
        Gson gson = new Gson();
        GameStatisticModelAPI model = gson.fromJson(getGameStatisticJSON, GameStatisticModelAPI.class);
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
