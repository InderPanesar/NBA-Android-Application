package com.aston.basicarchitecture.pages.home.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.engine.model.standings.TeamStandingModel;
import com.aston.basicarchitecture.utils.livedata.LiveDataStateData;
import com.aston.basicarchitecture.utils.livedata.UniversalErrorStateHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainBaseFragment extends Fragment {

    private LinearLayout favouriteTeamWidget;
    private MainFragmentViewModel exampleViewModel;
    private TableLayout tableLayout;
    private String conference = "east";

    public MainBaseFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_landing_page, container, false);

        //Get Text Area
        favouriteTeamWidget =  v.findViewById(R.id.favourite_team_widget);

        //Set the ViewModel
        exampleViewModel = new ViewModelProvider(getActivity()).get(MainFragmentViewModel.class);
        tableLayout = v.findViewById(R.id.schedule_statistics_table);

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

        ProgressBar bar = v.findViewById(R.id.schedule_table_progress_bar);
        LinearLayout errorStates = v.findViewById(R.id.schedule_table_error_state);
        MaterialButton errorButtonSchedule = v.findViewById(R.id.schedule_table_retry_button);

        Observer<LiveDataStateData<ArrayList<TeamStandingModel>>> scheduleObserver = new Observer<LiveDataStateData<ArrayList<TeamStandingModel>>>() {
            @Override
            public void onChanged(LiveDataStateData<ArrayList<TeamStandingModel>> stateLiveData) {
                switch (stateLiveData.getStatus()) {
                    case SUCCESS:
                        tableLayout.removeAllViews();
                        ArrayList<TeamStandingModel> data = stateLiveData.getData();
                        setTable(v, data);
                        Log.d("HIT", "SET TABLE");

                        bar.setVisibility(View.INVISIBLE);
                        tableLayout.setVisibility(View.VISIBLE);
                        errorStates.setVisibility(View.INVISIBLE);
                        break;
                    case ERROR:
                        bar.setVisibility(View.INVISIBLE);
                        tableLayout.setVisibility(View.INVISIBLE);
                        errorStates.setVisibility(View.VISIBLE);
                        break;
                    case LOADING:
                        bar.setVisibility(View.VISIBLE);
                        tableLayout.setVisibility(View.INVISIBLE);
                        errorStates.setVisibility(View.INVISIBLE);
                        break;
                }
            }

        };

        exampleViewModel.getSchedule("east").observe(getViewLifecycleOwner(), scheduleObserver);

        MaterialButtonToggleGroup toggleGroup = v.findViewById(R.id.schedule_button_group);

        MaterialButton eastButton = v.findViewById(R.id.east_schedule_button);
        MaterialButton westButton = v.findViewById(R.id.west_schedule_button);

        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conference = "east";
                exampleViewModel.getSchedule(conference).observe(getViewLifecycleOwner(), scheduleObserver);
            }
        });

        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conference = "west";
                exampleViewModel.getSchedule(conference).observe(getViewLifecycleOwner(), scheduleObserver);
            }
        });

        errorButtonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exampleViewModel.getSchedule(conference).observe(getViewLifecycleOwner(), scheduleObserver);
            }
        });

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exampleViewModel.getPlayers(exampleViewModel.getFavouriteId(getActivity().getPreferences(Context.MODE_PRIVATE))).observe(getViewLifecycleOwner(), nameObserver);
            }
        });

        TextView favouriteTeamTitle = v.findViewById(R.id.favourite_team_title);
        MaterialCardView favouriteTeamWidgetPage = v.findViewById(R.id.favourite_team_widget_landing_page);


        if(exampleViewModel.getFavouriteId(getActivity().getPreferences(Context.MODE_PRIVATE)).equals("-1")) {
            favouriteTeamTitle.setVisibility(View.GONE);
            favouriteTeamWidgetPage.setVisibility(View.GONE);
        }

        return v;
    }

    public void setTable (View v, ArrayList<TeamStandingModel> teams) {

        if(teams.size() == 0) {
            tableLayout.setVisibility(View.INVISIBLE);
        }
        else {
            ArrayList<String> headers = new ArrayList<>();
            headers.add("Seed");
            headers.add("Logo");
            headers.add("Team");
            headers.add("Record");

            TableRow topRow = new TableRow(getContext());
            for( String header : headers) {
                TextView tv0 = new TextView(getContext());
                tv0.setText(header);
                tv0.setTextColor(Color.BLACK);
                tv0.setGravity(Gravity.CENTER);
                tv0.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border));
                topRow.addView(tv0);
            }
            tableLayout.addView(topRow);
            int seed = 1;
            for (TeamStandingModel team : teams) {
                TableRow tbrow = new TableRow(getContext());
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                tbrow.setLayoutParams(params);

                TextView tv = new TextView(getContext());
                tv.setText(team.getConference().getRank());
                tv.setPadding(10, 30, 10, 30);

                tv.setTextColor(Color.BLACK);
                tv.setGravity(Gravity.CENTER);
                tv.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border));
                tbrow.addView(tv);

                ImageView view = new ImageView(getContext());
                view.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border));
                Picasso.get()
                        .load(exampleViewModel.getTeamLogo(team.getTeamId()))
                        .into(view);
                view.setLayoutParams(new TableRow.LayoutParams(113, 113));
                tbrow.addView(view);


                tv = new TextView(getContext());
                tv.setText(exampleViewModel.getTeamName(team.getTeamId()));
                tv.setTextColor(Color.BLACK);
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(10, 30, 10, 30);
                tv.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border));
                tbrow.addView(tv);


                tv = new TextView(getContext());
                tv.setText(new StringBuilder().append(team.getConference().getWin()).append(" - ").append(team.getConference().getLoss()).toString());
                tv.setTextColor(Color.BLACK);
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(10, 30, 10, 30);
                tv.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border));
                tbrow.addView(tv);


                tableLayout.addView(tbrow);


            }


        }

    }
}