package com.aston.basketballapp.pages.home.settings.favouriteTeam;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aston.basketballapp.MainActivity;
import com.aston.basketballapp.R;
import com.aston.basketballapp.utils.AppConsts;
import com.google.android.material.button.MaterialButton;


public class FragmentFavouriteTeam extends Fragment implements SettingsTeamClicked {

    RecyclerView teamsRecyclerView;
    SettingsTeamsAdapter teamsAdapter;
    SettingsTeamPreferenceViewModel viewModel;

    public FragmentFavouriteTeam() {
        // Required empty public constructor
    }


    public static FragmentFavouriteTeam newInstance() {
        return new FragmentFavouriteTeam();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View actionBar = (getActivity()).findViewById(R.id.main_app_bar_layout);
        actionBar.setBackground(new ColorDrawable(Color.parseColor("#FFA834")));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this.getActivity()).get(SettingsTeamPreferenceViewModel.class);
        viewModel.setPref(getActivity().getPreferences(Context.MODE_PRIVATE));

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favourite_team, container, false);
        //recyclerView setup
        teamsRecyclerView = v.findViewById(R.id.favourite_team_recycler_view);
        teamsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        teamsRecyclerView.setHasFixedSize(true);

        teamsAdapter = new SettingsTeamsAdapter(getContext(), viewModel.getTeams(), this);
        teamsRecyclerView.setAdapter(teamsAdapter);

        MaterialButton confirmButton = v.findViewById(R.id.favourite_team_confirm);
        confirmButton.setOnClickListener(v1 -> {
            //Set the shared preferences on confirm button press and pop back to previous fragment.
            AppConsts.verifyActivity(getActivity());
            viewModel.setSharedPreferences(getActivity().getPreferences(Context.MODE_PRIVATE));
            ((MainActivity)getActivity()).updateNavigationMenuHeader();
            FragmentFavouriteTeam.super.getActivity().onBackPressed();
        });

        return v;
    }


    //When card click add preference or remove team preference.
    @Override
    public void cardClicked(TeamsRepo.LocalTeam team, Boolean isRemoved) {
        if(!isRemoved) {
            viewModel.addTeamPreference(team.getId());
        }
        else { viewModel.removeTeamPreference(); }

    }

    @Override
    public void onDestroy() {
        View actionBar = (getActivity()).findViewById(R.id.main_app_bar_layout);
        actionBar.setBackground(new ColorDrawable(Color.parseColor("#FFFFFF")));
        super.onDestroy();
    }
}