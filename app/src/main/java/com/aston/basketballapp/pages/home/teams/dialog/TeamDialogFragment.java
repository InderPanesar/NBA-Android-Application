package com.aston.basketballapp.pages.home.teams.dialog;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.aston.basketballapp.R;
import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.DrawerLayoutControl;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;
import java.util.ArrayList;

public class TeamDialogFragment extends DialogFragment implements PlayersCardClicked {


    RecyclerView teamPlayersRecyclerView;
    PlayersBaseAdapter playersAdapter;
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
    public void onResume() {
        super.onResume();
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
        playersAdapter = new PlayersBaseAdapter(getContext(), new ArrayList<>(), this);
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

    @Override
    public void cardClicked(View v, IndividualPlayerModel playerModel) {
        AppConsts.verifyActivity(getActivity());
        ((DrawerLayoutControl) getActivity()).setDrawerEnabled(false);

        AppConsts.verifyArguments(getArguments());
        String teamID = getArguments().getString("teamId");

        //Pass all bundle information to PlayersDetailFragment.
        Bundle bundle = new Bundle();
        String[] playerAttributes = {
                String.valueOf(playerModel.getNba().getPro()),
                playerModel.getCollege(),
                playerModel.getBirth().getCountry(),
                String.valueOf(playerModel.getId()),
                String.valueOf(playerModel.getNba().getStart()),
                playerModel.getHeight().getMeters() + "m",
                playerModel.getWeight().getKilograms() + "kg",
                teamID,
                "#" + playerModel.getLeagues().getStandard().getJersey(),
                "Position: " + playerModel.getLeagues().getStandard().getPos(),
                String.valueOf(playerModel.getLeagues().getStandard().isActive())
        };
        bundle.putString("playerId", String.valueOf(playerModel.getId()));
        bundle.putString("playerName", playerModel.getFirstname() + " " + playerModel.getLastname());
        bundle.putStringArray("playerAttributes",playerAttributes);

        Navigation.findNavController(v).navigate(R.id.action_teamDialogFragment_to_playersDetailFragment, bundle);
    }
}