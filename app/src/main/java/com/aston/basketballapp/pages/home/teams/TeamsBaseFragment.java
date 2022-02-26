package com.aston.basketballapp.pages.home.teams;

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
import com.aston.basketballapp.R;
import com.aston.basketballapp.engine.model.teams.IndividualTeamsModel;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.DrawerLayoutControl;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;

public class TeamsBaseFragment extends Fragment implements TeamsCardClicked {

    RecyclerView recyclerView;
    TeamsAdapter teamsAdapter;

    private TeamsBaseViewModel teamsBaseViewModel;

    public TeamsBaseFragment() { }


    public static TeamsBaseFragment newInstance() {
        return new TeamsBaseFragment();
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
        recyclerView = v.findViewById(R.id.teams_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        recyclerView.setHasFixedSize(true);
        teamsAdapter = new TeamsAdapter(getContext(), new ArrayList<>(), this);
        recyclerView.setAdapter(teamsAdapter);

        //Set the ViewModel
        AppConsts.verifyActivity(getActivity());
        teamsBaseViewModel = new ViewModelProvider(this.getActivity()).get(TeamsBaseViewModel.class);


        //Observer to show each individual team
        Observer<LiveDataStateData<ArrayList<IndividualTeamsModel>>> individualTeamObserver = stateLiveData -> {
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
        };

        teamsBaseViewModel.getTeams().observe(getViewLifecycleOwner(), individualTeamObserver);

        MaterialButton easternConferenceButton = v.findViewById(R.id.east_conference_button);
        MaterialButton westernConferenceButton = v.findViewById(R.id.west_conference_button);

        AppConsts.verifyContext(getContext());
        Typeface fontSelected = ResourcesCompat.getFont(getContext(), R.font.asap_bold);
        Typeface fontUnSelected = ResourcesCompat.getFont(getContext(), R.font.asap_regular);


        //East button selected on top of screen
        easternConferenceButton.setOnClickListener(view -> {
            recyclerView.setVisibility(View.GONE);
            teamsBaseViewModel.currentConference = "east";
            teamsBaseViewModel.getTeams().observe(getViewLifecycleOwner(), individualTeamObserver);

            easternConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.east_blue_selected));
            westernConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_unselected));

            easternConferenceButton.setTypeface(fontSelected);
            westernConferenceButton.setTypeface(fontUnSelected);

        });

        //West button selected on top of screen
        westernConferenceButton.setOnClickListener(view -> {
            recyclerView.setVisibility(View.GONE);
            teamsBaseViewModel.currentConference = "west";
            teamsBaseViewModel.getTeams().observe(getViewLifecycleOwner(), individualTeamObserver);
            AppConsts.verifyContext(getContext());
            easternConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.east_blue_unselected));
            westernConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_selected));

            easternConferenceButton.setTypeface(fontUnSelected);
            westernConferenceButton.setTypeface(fontSelected);
        });

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(view -> teamsBaseViewModel.getTeams().observe(getViewLifecycleOwner(), individualTeamObserver));


        if(teamsBaseViewModel.currentConference.equals("east")) {
            AppConsts.verifyContext(getContext());
            easternConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.east_blue_selected));
            westernConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_unselected));
        }
        else {
            AppConsts.verifyContext(getContext());
            easternConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.east_blue_unselected));
            westernConferenceButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_selected));
        }

        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        AppConsts.verifyActivity(getActivity());
        ((DrawerLayoutControl) getActivity()).setDrawerEnabled(true);
    }

    @Override
    public void cardClicked(View v, IndividualTeamsModel teamsModel) {
        AppConsts.verifyActivity(getActivity());
        ((DrawerLayoutControl) getActivity()).setDrawerEnabled(false);
        Bundle bundle = new Bundle();
        bundle.putString("teamId", teamsModel.getId()+"");
        bundle.putString("teamName", teamsModel.getName());
        Navigation.findNavController(v).navigate(R.id.action_teams_to_teamDialogFragment, bundle);
    }
}