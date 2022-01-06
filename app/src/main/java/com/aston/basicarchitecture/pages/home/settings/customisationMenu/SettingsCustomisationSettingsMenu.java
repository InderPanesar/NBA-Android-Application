package com.aston.basicarchitecture.pages.home.settings.customisationMenu;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.pages.home.settings.favouriteTeam.SettingsTeamPreferenceViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsCustomisationSettingsMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsCustomisationSettingsMenu extends Fragment {


    SettingsCustomisationSettingsMenuViewModel viewModel;
    HashBiMap<SwitchMaterial, Integer> sliderList = HashBiMap.create();
    ArrayList<SwitchMaterial> activeSwitches = new ArrayList<> ();

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
        viewModel = new ViewModelProvider(this.getActivity()).get(SettingsCustomisationSettingsMenuViewModel.class);

        // Adds sliders to the view.
        sliderList.put(v.findViewById(R.id.pointsPerGameSlider), 1);
        sliderList.put(v.findViewById(R.id.assistsPerGameSlider), 2);
        sliderList.put(v.findViewById(R.id.reboundsPerGameSlider), 3);
        sliderList.put(v.findViewById(R.id.overallFieldGoalPercentageSlider), 4);
        sliderList.put(v.findViewById(R.id.stealsPercentageSlider), 5);
        sliderList.put(v.findViewById(R.id.blocksPercentageSlider), 6);
        sliderList.put(v.findViewById(R.id.freeThrowPercentageSlider), 7);
        sliderList.put(v.findViewById(R.id.freeThrowsMadeSlider), 8);
        sliderList.put(v.findViewById(R.id.plusMinusSlider), 9);


        List<Integer> values = viewModel.getSharedPreferences(getActivity().getPreferences(Context.MODE_PRIVATE));
        for(int value : values) {
            if(value != -1) {
                Log.d("HIT ", String.valueOf(value));
                SwitchMaterial _switch = sliderList.inverse().get(value);
                _switch.setChecked(true);
                activeSwitches.add(_switch);

            }
        }


        for(SwitchMaterial _switch : sliderList.keySet()) {
            _switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) { activeSwitches.add(_switch); }
                    else { activeSwitches.remove(_switch); }
                    disableAllSwitches(activeSwitches.size(), false);
                }

            });
        }
        disableAllSwitches(activeSwitches.size(), true);

        return v;
    }

    private void disableAllSwitches (int switchesActive, boolean initialRun) {
        if(switchesActive == 4) {
            for(SwitchMaterial s : sliderList.keySet()) {
                if(!activeSwitches.contains(s)) {
                    s.setEnabled(false);
                }
            }
        }
        else {
            for(SwitchMaterial s : sliderList.keySet()) {
                if(!s.isEnabled()) {
                    s.setEnabled(true);
                }
            }
        }

        if(!initialRun) {
            List<Integer> values = new ArrayList<>();
            for(SwitchMaterial _switch: activeSwitches) {
                values.add(sliderList.get(_switch));
            }
            viewModel.setSharedPreferences(getActivity().getPreferences(Context.MODE_PRIVATE), values);
        }
    }
}