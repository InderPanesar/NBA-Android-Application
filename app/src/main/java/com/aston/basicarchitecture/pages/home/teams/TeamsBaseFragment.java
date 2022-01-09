package com.aston.basicarchitecture.pages.home.teams;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.pages.home.main.ExampleViewModel;
import com.aston.basicarchitecture.utils.livedata.LiveDataStateData;
import com.aston.basicarchitecture.utils.livedata.StateMutableLiveData;
import com.aston.basicarchitecture.utils.livedata.UniversalErrorStateHandler;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamsBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamsBaseFragment extends Fragment implements TeamsCardClicked {

    RecyclerView recyclerView;
    TeamsAdapter teamsAdapter;
    String currentConference = "east";


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

        teamsBaseViewModel.getTeams(currentConference).observe(getViewLifecycleOwner(), nameObserver);

        Button easternConferenceButton = (Button) v.findViewById(R.id.eastConferenceButton);
        easternConferenceButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                currentConference = "east";
                teamsBaseViewModel.getTeams(currentConference).observe(getViewLifecycleOwner(), nameObserver);
            }
        });

        Button westernConferenceButton = (Button) v.findViewById(R.id.westConferenceButton);
        westernConferenceButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.GONE);
                currentConference = "west";
                teamsBaseViewModel.getTeams(currentConference).observe(getViewLifecycleOwner(), nameObserver);
            }
        });

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamsBaseViewModel.getTeams(currentConference).observe(getViewLifecycleOwner(), nameObserver);
            }
        });

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