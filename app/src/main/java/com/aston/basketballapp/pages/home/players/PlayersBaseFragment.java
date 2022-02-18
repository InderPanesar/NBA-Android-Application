package com.aston.basketballapp.pages.home.players;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.aston.basketballapp.R;
import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.DrawerLayoutControl;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;

import java.util.ArrayList;

public class PlayersBaseFragment extends Fragment implements PlayersCardClicked {


    RecyclerView playersRecyclerView;
    PlayersBaseAdapter playersAdapter;
    PlayerBaseViewModel playerBaseViewModel;


    public PlayersBaseFragment() {}


    public static PlayersBaseFragment newInstance() {
        return new PlayersBaseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_players, container, false);

        AppConsts.verifyActivity(getActivity());
        playerBaseViewModel = new ViewModelProvider(this.getActivity()).get(PlayerBaseViewModel.class);

        playersRecyclerView = v.findViewById(R.id.players_fragment_recycler_view);
        //TODO: Implement Loading State
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        playersRecyclerView.setLayoutManager(linearLayoutManager);
        playersRecyclerView.setNestedScrollingEnabled(false);
        playersRecyclerView.setHasFixedSize(false);
        playersAdapter = new PlayersBaseAdapter(getContext(), new ArrayList<>(), this);
        playersRecyclerView.setAdapter(playersAdapter);


        //Get Multiplayer Player Observer.
        Observer<LiveDataStateData<ArrayList<IndividualPlayerModel>>> playersObserver = arrayListLiveDataStateData -> {
            switch (arrayListLiveDataStateData.getStatus()) {
                case SUCCESS:
                    playersAdapter.setPlayers(arrayListLiveDataStateData.getData());
                    playersRecyclerView.setVisibility(View.VISIBLE);
                    UniversalErrorStateHandler.isSuccess(v);
                    break;
                case ERROR:
                    playersRecyclerView.setVisibility(View.INVISIBLE);
                    UniversalErrorStateHandler.isError(v);
                    break;
                case LOADING:
                    playersRecyclerView.setVisibility(View.INVISIBLE);
                    UniversalErrorStateHandler.isLoading(v);
                    break;
            }
        };

        playerBaseViewModel.getAllPlayers().observe(getViewLifecycleOwner(), playersObserver);

        //Setup Dialog for user to filter each of the Player values.
        Button internationButton  = v.findViewById(R.id.players_international_button);
        internationButton.setText(R.string.filter_button_all);
        internationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsDialog();
            }
            private void showOptionsDialog() {
                final int[] tempValue = {playerBaseViewModel.playerFilter};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose Player Category");
                builder.setSingleChoiceItems(playerBaseViewModel.internationalValues, playerBaseViewModel.playerFilter, (dialog, which) -> tempValue[0] = which);
                builder.setPositiveButton("Confirm", (dialog, which) -> {
                    playerBaseViewModel.playerFilter = tempValue[0];
                    if(playerBaseViewModel.playerFilter == 1) {
                        internationButton.setText(R.string.filter_button_1);
                    }
                    else if (playerBaseViewModel.playerFilter == 2) {
                        internationButton.setText(R.string.filter_button_2);
                    }
                    else {
                        internationButton.setText(R.string.filter_button_all);
                    }
                    playersRecyclerView.setVisibility(View.GONE);
                    playerBaseViewModel.getInternationalFilterPlayers().observe(getViewLifecycleOwner(), playersObserver);
                    dialog.dismiss();
                });

                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                builder.show();

            }

        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppConsts.verifyActivity(getActivity());
        ((DrawerLayoutControl) getActivity()).setDrawerEnabled(true);
    }

    @Override
    public void cardClicked(View v, IndividualPlayerModel playerModel) {

        AppConsts.verifyActivity(getActivity());
        ((DrawerLayoutControl) getActivity()).setDrawerEnabled(false);

        //Pass all bundle information to PlayersDetailFragment.
        Bundle bundle = new Bundle();
        String[] playerAttributes = {
            playerModel.getYearsPro(),
            playerModel.getCollegeName(),
            playerModel.getCountry(),
            playerModel.getPlayerId(),
            playerModel.getStartNBA(),
            playerModel.getHeightInMetres() + "m",
            playerModel.getWeightInKilograms() + "kg",
            playerModel.getTeamId(),
            "#" + playerModel.getLeagues().getNBADetails().getJersey(),
            "Position: " + playerModel.getLeagues().getNBADetails().getPos(),
            playerModel.getLeagues().getNBADetails().getActive()
        };
        bundle.putString("playerId", playerModel.getPlayerId());
        bundle.putString("playerName", playerModel.getFirstName() + " " + playerModel.getLastName());
        bundle.putStringArray("playerAttributes",playerAttributes);

        Navigation.findNavController(v).navigate(R.id.action_players_to_playersDetailFragment, bundle);
    }
}