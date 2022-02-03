package com.aston.basicarchitecture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.aston.basicarchitecture.pages.home.settings.favouriteTeam.SettingsTeamPreferenceViewModel;
import com.aston.basicarchitecture.utils.AppConsts;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SettingsTeamPreferenceViewModelTest {

    @Mock
    Context mockContext;
    @Mock
    SharedPreferences mockPrefs;
    @Mock
    SharedPreferences.Editor mockEditor;

    @Before
    public void before() throws Exception {
        Mockito.when(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockPrefs);
        Mockito.when(mockContext.getSharedPreferences(anyString(), anyInt()).edit()).thenReturn(mockEditor);
        Mockito.when(mockContext.getSharedPreferences(anyString(), anyInt()).edit()).thenReturn(mockEditor);

        Mockito.when(mockPrefs.getInt(AppConsts.TEAM_FAVOURITE_KEY, -1)).thenReturn(2);
    }

    @Test
    public void correctTeamFromSharedPreferencesTest() {
        SettingsTeamPreferenceViewModel viewModel = new SettingsTeamPreferenceViewModel();
        viewModel.setPref(mockPrefs);
        assertEquals(viewModel.getSelectedValue(), 2);
    }

    @Test
    public void incorrectTeamFromSharedPreferencesTest() {
        SettingsTeamPreferenceViewModel viewModel = new SettingsTeamPreferenceViewModel();
        viewModel.setPref(mockPrefs);
        assertNotEquals(viewModel.getSelectedValue(), 4);
    }

}
