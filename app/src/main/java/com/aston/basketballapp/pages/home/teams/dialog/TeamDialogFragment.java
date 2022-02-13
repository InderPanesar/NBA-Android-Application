package com.aston.basketballapp.pages.home.teams.dialog;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.aston.basketballapp.R;
import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamDialogFragment extends DialogFragment {


    RecyclerView teamPlayersRecyclerView;
    PlayersAdapter playersAdapter;
    private TeamDialogViewModel teamDialogViewModel;


    public TeamDialogFragment() { }

    public static TeamDialogFragment newInstance() {
        TeamDialogFragment fragment = new TeamDialogFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getArguments().getString("teamName"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_team_dialog, container, false);

        teamDialogViewModel = new ViewModelProvider(this.getActivity()).get(TeamDialogViewModel.class);

        //recyclerView setup
        teamPlayersRecyclerView = v.findViewById(R.id.players_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        teamPlayersRecyclerView.setLayoutManager(linearLayoutManager);
        teamPlayersRecyclerView.setNestedScrollingEnabled(false);
        teamPlayersRecyclerView.setHasFixedSize(false);
        playersAdapter = new PlayersAdapter(getContext(), new ArrayList<IndividualPlayerModel>());
        teamPlayersRecyclerView.setAdapter(playersAdapter);


        //Observer
        Observer<LiveDataStateData<ArrayList<IndividualPlayerModel>>> playersOnTeamObserver = new Observer<LiveDataStateData<ArrayList<IndividualPlayerModel>>>() {
            @Override
            public void onChanged(LiveDataStateData<ArrayList<IndividualPlayerModel>> stateLiveData) {
                switch (stateLiveData.getStatus()) {
                    case SUCCESS:
                        ArrayList<IndividualPlayerModel> data = stateLiveData.getData();
                        playersAdapter.setPlayers(data);
                        teamPlayersRecyclerView.setVisibility(View.VISIBLE);
                        UniversalErrorStateHandler.isSuccess(v);
                        break;
                    case ERROR:
                        teamPlayersRecyclerView.setVisibility(View.INVISIBLE);
                        UniversalErrorStateHandler.isError(v);
                        break;
                    case LOADING:
                        teamPlayersRecyclerView.setVisibility(View.INVISIBLE);
                        UniversalErrorStateHandler.isLoading(v);
                        break;
                }
            }
        };

        //Get All Players on Specific Team
        teamDialogViewModel.getPlayers(getArguments().getString("teamId")).observe(getViewLifecycleOwner(), playersOnTeamObserver);;

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamDialogViewModel.getPlayers(getArguments().getString("teamId")).observe(getViewLifecycleOwner(), playersOnTeamObserver);;
            }
        });


        return v;
    }
}