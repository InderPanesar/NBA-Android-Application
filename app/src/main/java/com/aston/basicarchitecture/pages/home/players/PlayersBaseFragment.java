package com.aston.basicarchitecture.pages.home.players;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.pages.home.teams.dialog.PlayersAdapter;
import com.aston.basicarchitecture.pages.home.teams.dialog.TeamDialogViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayersBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersBaseFragment extends Fragment implements PlayersCardClicked {

    String[] internationalValues = {"All", "USA", "World"};
    int checkedItem = 0;

    RecyclerView recyclerView;
    PlayersBaseAdapter playersAdapter;
    PlayerBaseViewModel playerBaseViewModel;
    ArrayList<IndividualPlayerModel> players = new ArrayList<IndividualPlayerModel>();


    public PlayersBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment players.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayersBaseFragment newInstance() {
        PlayersBaseFragment fragment = new PlayersBaseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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

        playerBaseViewModel = new ViewModelProvider(this.getActivity()).get(PlayerBaseViewModel.class);

        recyclerView = v.findViewById(R.id.playersFragmentRecyclerView);
        //TODO: Implement Loading State
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        playersAdapter = new PlayersBaseAdapter(getContext(), players, this);
        recyclerView.setAdapter(playersAdapter);


        //Observer
        Observer<ArrayList<IndividualPlayerModel>> nameObserver = new Observer<ArrayList<IndividualPlayerModel>>() {
            @Override
            public void onChanged(ArrayList<IndividualPlayerModel> individualTeamsModels) {
                for(IndividualPlayerModel model : individualTeamsModels) {
                    Log.d("Player: ", model.getFirstName());
                }
                playersAdapter.setPlayers(individualTeamsModels);
                recyclerView.setVisibility(View.VISIBLE);
                //TODO: Loading State NOT VISIBLE
            }
        };

        playerBaseViewModel.getAllPlayers().observe(getViewLifecycleOwner(), nameObserver);

        Button internationButton  = v.findViewById(R.id.playersInternationalButton);
        internationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsDialog();
            }

            private void showOptionsDialog() {
                final int[] tempValue = {checkedItem};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose Player Category");
                builder.setSingleChoiceItems(internationalValues, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tempValue[0] = which;
                    }
                });
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItem = tempValue[0];
                        recyclerView.setVisibility(View.GONE);
                        //TODO: Loading State VISIBLE
                        playerBaseViewModel.getInternationalFilterPlayers(checkedItem).observe(getViewLifecycleOwner(), nameObserver);
                        dialog.dismiss();


                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();

            }

        });


        return v;
    }

    @Override
    public void cardClicked(View v, IndividualPlayerModel playerModel) {

        //ToDo: Move this to the ViewModel
        Bundle bundle = new Bundle();
        String[] playerAttributes = {
                playerModel.getYearsPro(),
                playerModel.getCollegeName(),
                playerModel.getCountry(),
                playerModel.getPlayerId(),
                playerModel.getStartNBA(),
                playerModel.getHeightInMetres() + "m",
                playerModel.getWeightInKilometers() + "kg",
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