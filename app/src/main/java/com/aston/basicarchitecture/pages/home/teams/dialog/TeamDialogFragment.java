package com.aston.basicarchitecture.pages.home.teams.dialog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.pages.home.teams.TeamsAdapter;
import com.aston.basicarchitecture.pages.home.teams.TeamsBaseViewModel;
import com.aston.basicarchitecture.utils.livedata.LiveDataStateData;
import com.aston.basicarchitecture.utils.livedata.UniversalErrorStateHandler;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamDialogFragment extends DialogFragment {


    RecyclerView recyclerView;
    PlayersAdapter playersAdapter;
    ProgressBar bar;

    private TeamDialogViewModel teamDialogViewModel;
    ArrayList<IndividualPlayerModel> players = new ArrayList<IndividualPlayerModel>();


    public TeamDialogFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        recyclerView = v.findViewById(R.id.playersRecyclerView);
        //TODO: Implement Loading State
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        playersAdapter = new PlayersAdapter(getContext(), players);
        recyclerView.setAdapter(playersAdapter);


        //Observer
        Observer<LiveDataStateData<ArrayList<IndividualPlayerModel>>> nameObserver = new Observer<LiveDataStateData<ArrayList<IndividualPlayerModel>>>() {
            @Override
            public void onChanged(LiveDataStateData<ArrayList<IndividualPlayerModel>> stateLiveData) {
                switch (stateLiveData.getStatus()) {
                    case SUCCESS:
                        ArrayList<IndividualPlayerModel> data = stateLiveData.getData();
                        playersAdapter.setPlayers(data);
                        recyclerView.setVisibility(View.VISIBLE);
                        UniversalErrorStateHandler.isSuccess(v);
                        break;
                    case ERROR:
                        recyclerView.setVisibility(View.INVISIBLE);
                        UniversalErrorStateHandler.isError(v);
                        break;
                    case LOADING:
                        recyclerView.setVisibility(View.INVISIBLE);
                        UniversalErrorStateHandler.isLoading(v);
                        break;
                }
            }
        };

        teamDialogViewModel.getPlayers(getArguments().getString("teamId")).observe(getViewLifecycleOwner(), nameObserver);;

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamDialogViewModel.getPlayers(getArguments().getString("teamId")).observe(getViewLifecycleOwner(), nameObserver);;
            }
        });


        return v;
    }
}