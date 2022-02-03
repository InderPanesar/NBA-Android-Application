package com.aston.basicarchitecture;

import org.junit.Test;
import static org.junit.Assert.*;

import com.aston.basicarchitecture.pages.home.main.MainFragmentViewModel;
import com.aston.basicarchitecture.pages.home.settings.favouriteTeam.TeamsRepo;

public class MainFragmentViewModelUnitTest {

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
}
