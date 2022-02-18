package com.aston.basketballapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.aston.basketballapp.pages.home.settings.customisationMenu.SettingsCustomisationSettingsMenu;
import com.aston.basketballapp.pages.home.settings.customisationMenu.SettingsCustomisationSettingsMenuViewModel;
import com.aston.basketballapp.utils.AppConsts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SettingsCustomisationSettingsMenuViewModelUnitTest {


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
    public void checkSharedPreferencesForGameSelectionValueReturnsFalse() {
        Mockito.when(mockPrefs.getInt(AppConsts.RECENT_GAMES_ONE, 1)).thenReturn(2);
        Mockito.when(mockPrefs.getInt(AppConsts.RECENT_GAMES_TWO, 2)).thenReturn(2);
        Mockito.when(mockPrefs.getInt(AppConsts.RECENT_GAMES_THREE, 3)).thenReturn(2);
        Mockito.when(mockPrefs.getInt(AppConsts.RECENT_GAMES_FOUR, 4)).thenReturn(2);

        SettingsCustomisationSettingsMenuViewModel viewModel = new SettingsCustomisationSettingsMenuViewModel();
        List<Integer> integers = viewModel.getSharedPreferences(mockPrefs);

        boolean value = integers.contains(-1);
        assertFalse(value);
    }

    @Test
    public void checkSharedPreferencesForGameSelectionValueReturnsTrue() {
        Mockito.when(mockPrefs.getInt(AppConsts.RECENT_GAMES_ONE, 1)).thenReturn(2);
        Mockito.when(mockPrefs.getInt(AppConsts.RECENT_GAMES_TWO, -1)).thenReturn(-1);
        Mockito.when(mockPrefs.getInt(AppConsts.RECENT_GAMES_THREE, -1)).thenReturn(-1);
        Mockito.when(mockPrefs.getInt(AppConsts.RECENT_GAMES_FOUR, -1)).thenReturn(-1);

        SettingsCustomisationSettingsMenuViewModel viewModel = new SettingsCustomisationSettingsMenuViewModel();
        List<Integer> integers = viewModel.getSharedPreferences(mockPrefs);

        boolean value = integers.contains(-1);
        assertTrue(value);
    }



}
