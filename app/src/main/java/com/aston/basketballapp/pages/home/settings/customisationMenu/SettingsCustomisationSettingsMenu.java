package com.aston.basketballapp.pages.home.settings.customisationMenu;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.aston.basketballapp.R;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;

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
        sliderList.put(v.findViewById(R.id.points_per_game_slider), 1);
        sliderList.put(v.findViewById(R.id.assists_per_game_slider), 2);
        sliderList.put(v.findViewById(R.id.rebounds_per_game_slider), 3);
        sliderList.put(v.findViewById(R.id.overall_field_goal_percentage_slider), 4);
        sliderList.put(v.findViewById(R.id.steals_percentage_slider), 5);
        sliderList.put(v.findViewById(R.id.blocks_percentage_slider), 6);
        sliderList.put(v.findViewById(R.id.free_throw_percentage_slider), 7);
        sliderList.put(v.findViewById(R.id.free_throws_made_slider), 8);
        sliderList.put(v.findViewById(R.id.plus_minus_slider), 9);


        List<Integer> values = viewModel.getSharedPreferences(getActivity().getPreferences(Context.MODE_PRIVATE));
        for(int value : values) {
            if(value != -1) {
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