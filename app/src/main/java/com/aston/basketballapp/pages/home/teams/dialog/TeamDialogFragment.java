package com.aston.basketballapp.pages.home.teams.dialog;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aston.basketballapp.R;
import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;
import java.util.ArrayList;

public class TeamDialogFragment extends DialogFragment {


    RecyclerView teamPlayersRecyclerView;
    PlayersAdapter playersAdapter;
    private TeamDialogViewModel teamDialogViewModel;


    public TeamDialogFragment() { }

    public static TeamDialogFragment newInstance() {
        return new TeamDialogFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConsts.verifyArguments(getArguments());
        AppConsts.verifyActivity(getActivity());
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getArguments().getString("teamName"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_team_dialog, container, false);

        AppConsts.verifyActivity(getActivity());
        teamDialogViewModel = new ViewModelProvider(this.getActivity()).get(TeamDialogViewModel.class);

        //recyclerView setup
        teamPlayersRecyclerView = v.findViewById(R.id.players_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        teamPlayersRecyclerView.setLayoutManager(linearLayoutManager);
        teamPlayersRecyclerView.setNestedScrollingEnabled(false);
        teamPlayersRecyclerView.setHasFixedSize(false);
        playersAdapter = new PlayersAdapter(getContext(), new ArrayList<>());
        teamPlayersRecyclerView.setAdapter(playersAdapter);


        //Observer
        Observer<LiveDataStateData<ArrayList<IndividualPlayerModel>>> playersOnTeamObserver = stateLiveData -> {
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
        };

        //Get All Players on Specific Team
        AppConsts.verifyArguments(getArguments());
        teamDialogViewModel.getPlayers(getArguments().getString("teamId")).observe(getViewLifecycleOwner(), playersOnTeamObserver);

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(v1 -> {
            AppConsts.verifyArguments(getArguments());
            teamDialogViewModel.getPlayers(getArguments().getString("teamId")).observe(getViewLifecycleOwner(), playersOnTeamObserver);
        });


        return v;
    }
}