package com.aston.basketballapp.pages.home.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aston.basketballapp.R;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.DrawerLayoutControl;
import com.google.android.material.card.MaterialCardView;


public class SettingsFragment extends Fragment {



    public SettingsFragment() { }


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        AppConsts.verifyActivity(getActivity());
        ((DrawerLayoutControl) getActivity()).setDrawerEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        // Set Interactions with Customisation Settings Page
        MaterialCardView settingsCardView = v.findViewById(R.id.settings_customisation_settings);
        settingsCardView.setOnClickListener(cardView -> {
            AppConsts.verifyActivity(getActivity());
            ((DrawerLayoutControl) getActivity()).setDrawerEnabled(false);
            Navigation.findNavController(cardView).navigate(R.id.action_settings_to_settingsCustomisationSettingsMenu);
        });

        // Set Interactions with Favourite Teams customisation page.
        settingsCardView = v.findViewById(R.id.settings_change_favourite_team);
        settingsCardView.setOnClickListener(cardView -> {
            AppConsts.verifyActivity(getActivity());
            ((DrawerLayoutControl) getActivity()).setDrawerEnabled(false);
            Navigation.findNavController(cardView).navigate(R.id.action_settings_to_fragmentFavouriteTeam);
        });
        return v;
    }
}