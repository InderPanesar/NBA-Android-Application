package com.aston.basketballapp;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.Mockito;
import retrofit2.mock.Calls;

import static org.junit.Assert.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.aston.basketballapp.engine.model.standings.StandingsModelApi;
import com.aston.basketballapp.engine.model.standings.TeamStandingModel;
import com.aston.basketballapp.engine.repository.standings.StandingsRepository;
import com.aston.basketballapp.pages.home.main.MainFragmentViewModel;
import com.aston.basketballapp.utils.livedata.StateMutableLiveData;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class MainFragmentViewModelUnitTest {

    @Mock
    StandingsRepository repository;

    String mockAPIFavouriteTeamJsonValue = "{\"get\":\"standings\\/\",\"parameters\":{\"league\":\"standard\",\"season\":\"2021\",\"team\":\"2\"},\"errors\":[],\"results\":1,\"response\":[{\"league\":\"standard\",\"season\":2021,\"team\":{\"id\":2,\"name\":\"Boston Celtics\",\"nickname\":\"Celtics\",\"code\":\"BOS\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/6\\/65\\/Celtics_de_Boston_logo.svg\\/1024px-Celtics_de_Boston_logo.svg.png\"},\"conference\":{\"name\":\"east\",\"rank\":6,\"win\":25,\"loss\":15},\"division\":{\"name\":\"atlantic\",\"rank\":2,\"win\":8,\"loss\":6,\"gamesBehind\":\"1.5\"},\"win\":{\"home\":20,\"away\":15,\"total\":35,\"percentage\":\".574\",\"lastTen\":9},\"loss\":{\"home\":11,\"away\":15,\"total\":26,\"percentage\":\".426\",\"lastTen\":1},\"gamesBehind\":\"4.5\",\"streak\":1,\"winStreak\":true,\"tieBreakerPoints\":null}]}";
    String mockAPIScheduleJsonValue = "{\"get\":\"standings\\/\",\"parameters\":{\"league\":\"standard\",\"season\":\"2021\",\"team\":\"2\"},\"errors\":[],\"results\":1,\"response\":[{\"league\":\"standard\",\"season\":2021,\"team\":{\"id\":2,\"name\":\"Boston Celtics\",\"nickname\":\"Celtics\",\"code\":\"BOS\",\"logo\":\"https:\\/\\/upload.wikimedia.org\\/wikipedia\\/fr\\/thumb\\/6\\/65\\/Celtics_de_Boston_logo.svg\\/1024px-Celtics_de_Boston_logo.svg.png\"},\"conference\":{\"name\":\"east\",\"rank\":6,\"win\":25,\"loss\":15},\"division\":{\"name\":\"atlantic\",\"rank\":2,\"win\":8,\"loss\":6,\"gamesBehind\":\"1.5\"},\"win\":{\"home\":20,\"away\":15,\"total\":35,\"percentage\":\".574\",\"lastTen\":9},\"loss\":{\"home\":11,\"away\":15,\"total\":26,\"percentage\":\".426\",\"lastTen\":1},\"gamesBehind\":\"4.5\",\"streak\":1,\"winStreak\":true,\"tieBreakerPoints\":null}]}";

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void before() {
        repository = Mockito.mock(StandingsRepository.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(repository.getSpecificTeamStandings("teamID")).thenReturn(mockAPIFavouriteTeam());
        Mockito.when(repository.getStandings()).thenReturn(mockAPISchedule());

    }

    Call<StandingsModelApi> mockAPIFavouriteTeam() {
        Gson gson = new Gson();
        StandingsModelApi model = gson.fromJson(mockAPIFavouriteTeamJsonValue, StandingsModelApi.class);
        return Calls.response(Response.success(model));
    }

    Call<StandingsModelApi> mockAPISchedule() {
        Gson gson = new Gson();
        StandingsModelApi model = gson.fromJson(mockAPIScheduleJsonValue, StandingsModelApi.class);
        return Calls.response(Response.success(model));
    }

    @Test
    public void getTeamDetailsWhenOver1() {
        MainFragmentViewModel viewModel = new MainFragmentViewModel(null);
        assertEquals("Atlanta Hawks", viewModel.getTeamName("1"));
        assertEquals("https://loodibee.com/wp-content/uploads/nba-atlanta-hawks-logo.png", viewModel.getTeamLogo("1"));
    }

    @Test
    public void getTeamDetailsWhenBelow1() {
        MainFragmentViewModel viewModel = new MainFragmentViewModel(null);
        assertEquals("", viewModel.getTeamName("-1"));
        assertEquals("", viewModel.getTeamLogo("-1"));
    }

    @Test
    public void mockAPICallForFavouriteTeam() {
        MainFragmentViewModel viewModel = new MainFragmentViewModel(repository);
        StateMutableLiveData<ArrayList<String>> data = viewModel.getFavouriteTeamInformation("teamId");
        if(data.getValue() == null) {
            fail("API CALL for Favourite Team Failed");
        }
    }

    @Test
    public void mockAPICallForSchedule() {
        MainFragmentViewModel viewModel = new MainFragmentViewModel(repository);
        StateMutableLiveData<ArrayList<TeamStandingModel>> data = viewModel.getSchedule();
        if(data.getValue() == null) {
            fail("API CALL for Schedule Failed");
        }
    }




}
