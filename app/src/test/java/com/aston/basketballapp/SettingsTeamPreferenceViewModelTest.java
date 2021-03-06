package com.aston.basketballapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import android.content.Context;
import android.content.SharedPreferences;

import com.aston.basketballapp.pages.home.settings.favouriteTeam.SettingsTeamPreferenceViewModel;
import com.aston.basketballapp.utils.AppConsts;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;


@RunWith(MockitoJUnitRunner.Silent.class)
public class SettingsTeamPreferenceViewModelTest {

    @Mock
    Context mockContext;
    @Mock
    SharedPreferences mockPrefs;
    @Mock
    SharedPreferences.Editor mockEditor;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.LENIENT);



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
