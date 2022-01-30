package com.aston.basicarchitecture.pages.home.settings.favouriteTeam;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aston.basicarchitecture.MainActivity;
import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.pages.home.teams.TeamsAdapter;
import com.aston.basicarchitecture.pages.home.teams.TeamsBaseViewModel;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentFavouriteTeam#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentFavouriteTeam extends Fragment implements SettingsTeamClicked {

    RecyclerView recyclerView;
    SettingsTeamsAdapter teamsAdapter;
    SettingsTeamPreferenceViewModel viewModel;

    public fragmentFavouriteTeam() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentFavouriteTeam.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentFavouriteTeam newInstance(String param1, String param2) {
        fragmentFavouriteTeam fragment = new fragmentFavouriteTeam();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        recyclerView = v.findViewById(R.id.favouriteTeamRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setHasFixedSize(true);

        teamsAdapter = new SettingsTeamsAdapter(getContext(), viewModel.getTeams(), this);
        recyclerView.setAdapter(teamsAdapter);

        MaterialButton b = v.findViewById(R.id.favouriteTeamConfirm);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setSharedPreferences(getActivity().getPreferences(Context.MODE_PRIVATE));
                ((MainActivity)getActivity()).updateNavBar();
                fragmentFavouriteTeam.super.getActivity().onBackPressed();
            }
        });




        return v;
    }


    @Override
    public void cardClicked(View v, TeamsRepo.LocalTeam team, Boolean isRemoved) {
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