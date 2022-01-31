package com.aston.basicarchitecture.pages.home.teams;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.utils.livedata.LiveDataStateData;
import com.aston.basicarchitecture.utils.livedata.UniversalErrorStateHandler;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamsBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamsBaseFragment extends Fragment implements TeamsCardClicked {

    RecyclerView recyclerView;
    TeamsAdapter teamsAdapter;


    ArrayList<IndividualTeamsModel> teams = new ArrayList<IndividualTeamsModel>();
    private TeamsBaseViewModel teamsBaseViewModel;

    private static final String TeamName = "";
    private static final String TeamID = "";

    public TeamsBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Teams.
     */
    // TODO: Rename and change types and number of parameters
    public static TeamsBaseFragment newInstance(String param1, String param2) {
        TeamsBaseFragment fragment = new TeamsBaseFragment();
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
        View v = inflater.inflate(R.layout.fragment_teams, container, false);

        //recyclerView setup
        recyclerView = v.findViewById(R.id.teamsRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);
        teamsAdapter = new TeamsAdapter(getContext(), teams, this);
        recyclerView.setAdapter(teamsAdapter);

        //Set the ViewModel
        teamsBaseViewModel = new ViewModelProvider(this.getActivity()).get(TeamsBaseViewModel.class);


        //Observer
        Observer<LiveDataStateData<ArrayList<IndividualTeamsModel>>> nameObserver = new Observer<LiveDataStateData<ArrayList<IndividualTeamsModel>>>() {
            @Override
            public void onChanged(LiveDataStateData<ArrayList<IndividualTeamsModel>> stateLiveData) {
                switch (stateLiveData.getStatus()) {
                    case SUCCESS:
                        ArrayList<IndividualTeamsModel> data = stateLiveData.getData();
                        teamsAdapter.setTeams(data);
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

        teamsBaseViewModel.getTeams().observe(getViewLifecycleOwner(), nameObserver);

        MaterialButton easternConferenceButton = (MaterialButton) v.findViewById(R.id.east_conference_button);
        MaterialButton westernConferenceButton = (MaterialButton) v.findViewById(R.id.west_conference_button);

        Typeface fontSelected = ResourcesCompat.getFont(getContext(), R.font.asap_bold);
        Typeface fontUnSelected = ResourcesCompat.getFont(getContext(), R.font.asap_regular);


        easternConferenceButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                teamsBaseViewModel.currentConference = "east";
                teamsBaseViewModel.getTeams().observe(getViewLifecycleOwner(), nameObserver);

                easternConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.east_blue_selected));
                westernConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_unselected));

                easternConferenceButton.setTypeface(fontSelected);
                westernConferenceButton.setTypeface(fontUnSelected);

            }
        });

        westernConferenceButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                teamsBaseViewModel.currentConference = "west";
                teamsBaseViewModel.getTeams().observe(getViewLifecycleOwner(), nameObserver);

                easternConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.east_blue_unselected));
                westernConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_selected));

                easternConferenceButton.setTypeface(fontUnSelected);
                westernConferenceButton.setTypeface(fontSelected);
            }
        });

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamsBaseViewModel.getTeams().observe(getViewLifecycleOwner(), nameObserver);
            }
        });


        if(teamsBaseViewModel.currentConference == "east") {
            easternConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.east_blue_selected));
            westernConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_unselected));
        }
        else {
            easternConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.east_blue_unselected));
            westernConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_selected));
        }

        return v;
    }





    @Override
    public void cardClicked(View v, IndividualTeamsModel teamsModel) {
        Bundle bundle = new Bundle();
        bundle.putString("teamId", teamsModel.getTeamId());
        bundle.putString("teamName", teamsModel.getFullName());
        Navigation.findNavController(v).navigate(R.id.action_teams_to_teamDialogFragment, bundle);
    }
}