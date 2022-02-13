package com.aston.basketballapp.pages.home.settings.customisationMenu;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aston.basketballapp.R;
import com.aston.basketballapp.utils.AppConsts;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.common.collect.HashBiMap;
import java.util.ArrayList;
import java.util.List;

public class SettingsCustomisationSettingsMenu extends Fragment {

    SettingsCustomisationSettingsMenuViewModel viewModel;
    //Using a hashBiMap to allow for Switches to be obtained from Integer number
    HashBiMap<SwitchMaterial, Integer> sliderList = HashBiMap.create();
    ArrayList<SwitchMaterial> activeSwitches = new ArrayList<> ();

    public SettingsCustomisationSettingsMenu() {}


    public static SettingsCustomisationSettingsMenu newInstance() {
        return new SettingsCustomisationSettingsMenu();
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
        AppConsts.verifyContext(getActivity());
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
                if(_switch != null) {
                    _switch.setChecked(true);
                    activeSwitches.add(_switch);
                }
            }
        }


        for(SwitchMaterial _switch : sliderList.keySet()) {
            _switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                //Add switches if it is checked.
                if(isChecked) { activeSwitches.add(_switch); }
                //Remove switch if it is unchecked.
                else { activeSwitches.remove(_switch); }
                disableAllSwitches(activeSwitches.size(), false);
            });
        }
        disableAllSwitches(activeSwitches.size(), true);

        return v;
    }

    //Disable all switches if the number of active is 4 and if it is not the initialisation save the new values.
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
            AppConsts.verifyActivity(getActivity());
            viewModel.setSharedPreferences(getActivity().getPreferences(Context.MODE_PRIVATE), values);
        }
    }
}