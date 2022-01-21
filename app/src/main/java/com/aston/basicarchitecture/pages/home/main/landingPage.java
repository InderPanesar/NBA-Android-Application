package com.aston.basicarchitecture.pages.home.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.utils.livedata.LiveDataStateData;
import com.aston.basicarchitecture.utils.livedata.UniversalErrorStateHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class landingPage extends Fragment {

    private LinearLayout favouriteTeamWidget;
    private ExampleViewModel exampleViewModel;

    public landingPage() { }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_landing_page, container, false);

        //Get Text Area
        favouriteTeamWidget =  v.findViewById(R.id.favourite_team_widget);



        //Set the ViewModel
        exampleViewModel = new ViewModelProvider(getActivity()).get(ExampleViewModel.class);

        Observer<LiveDataStateData<ArrayList<String>>> nameObserver = new Observer<LiveDataStateData<ArrayList<String>>>() {
            @Override
            public void onChanged(LiveDataStateData<ArrayList<String>> stateLiveData) {
                switch (stateLiveData.getStatus()) {
                    case SUCCESS:
                        favouriteTeamWidget.setVisibility(View.VISIBLE);
                        ArrayList<String> data = stateLiveData.getData();
                        UniversalErrorStateHandler.isSuccess(v);
                        String conferenceString = "";

                        if(data.get(0).equals("east")) { conferenceString = conferenceString + "Eastern Conference : "; }
                        else { conferenceString = conferenceString + "Western Conference : "; }
                        conferenceString = conferenceString + data.get(1);
                        TextView favouriteTeamConference = v.findViewById(R.id.favouriteTeamConferenceRanking);
                        favouriteTeamConference.setText(conferenceString);

                        String recordString = "Record : ";
                        recordString = recordString + data.get(2) + " - " + data.get(3);
                        TextView favouriteRecord = v.findViewById(R.id.favouriteTeamRecord);
                        favouriteRecord.setText(recordString);

                        TextView favouriteName = v.findViewById(R.id.favouriteTeamName);
                        favouriteName.setText(exampleViewModel.getFavouriteTeamName(getActivity().getPreferences(Context.MODE_PRIVATE)));

                        ImageView imageView = v.findViewById(R.id.favouriteTeamIconImage);
                        Picasso.get().load(exampleViewModel.getFavouriteTeamLink(getActivity().getPreferences(Context.MODE_PRIVATE))).into(imageView);


                        break;
                    case ERROR:
                        favouriteTeamWidget.setVisibility(View.INVISIBLE);
                        UniversalErrorStateHandler.isError(v);
                        break;
                    case LOADING:
                        favouriteTeamWidget.setVisibility(View.INVISIBLE);
                        UniversalErrorStateHandler.isLoading(v);
                        break;
                }
            }

        };

        exampleViewModel.getPlayers(exampleViewModel.getFavouriteId(getActivity().getPreferences(Context.MODE_PRIVATE))).observe(getViewLifecycleOwner(), nameObserver);


        return v;
    }
}