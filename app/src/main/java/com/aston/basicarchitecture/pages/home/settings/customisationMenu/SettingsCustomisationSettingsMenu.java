package com.aston.basicarchitecture.pages.home.settings.customisationMenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.pages.home.settings.favouriteTeam.SettingsTeamPreferenceViewModel;
import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsCustomisationSettingsMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsCustomisationSettingsMenu extends Fragment {


    SettingsTeamPreferenceViewModel viewModel;
    List<SwitchMaterial> sliderList = new ArrayList<>();
    List<SwitchMaterial> activeSwitches = new ArrayList<>();

    public SettingsCustomisationSettingsMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment settingsCustomisationSettingsMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsCustomisationSettingsMenu newInstance() {
        SettingsCustomisationSettingsMenu fragment = new SettingsCustomisationSettingsMenu();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View v = inflater.inflate(R.layout.fragment_settings_customisation_settings_menu, container, false);
        viewModel = new ViewModelProvider(this.getActivity()).get(SettingsTeamPreferenceViewModel.class);

        // Adds sliders to the view.
        sliderList.add(v.findViewById(R.id.pointsPerGameSlider));
        sliderList.add(v.findViewById(R.id.assistsPerGameSlider));
        sliderList.add(v.findViewById(R.id.reboundsPerGameSlider));
        sliderList.add(v.findViewById(R.id.overallFieldGoalPercentageSlider));
        sliderList.add(v.findViewById(R.id.threePointFieldGoalPercentageSlider));
        sliderList.add(v.findViewById(R.id.twoPointFieldGoalPercentageSlider));
        sliderList.add(v.findViewById(R.id.freeThrowPercentageSlider));
        sliderList.add(v.findViewById(R.id.freeThrowsMadeSlider));
        sliderList.add(v.findViewById(R.id.plusMinusSlider));

        for(SwitchMaterial _switch : sliderList) {
            _switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) { activeSwitches.add(_switch); }
                    else { activeSwitches.remove(_switch); }
                    disableAllSwitches(activeSwitches.size());
                }

            });
        }



        return v;
    }

    private void disableAllSwitches (int switchesActive) {
        if(switchesActive == 4) {
            for(SwitchMaterial s : sliderList) {
                if(!activeSwitches.contains(s)) {
                    s.setEnabled(false);
                }
            }
        }
        else {
            for(SwitchMaterial s : sliderList) {
                if(!s.isEnabled()) {
                    s.setEnabled(true);
                }
            }
        }
    }
}