package com.aston.basketballapp.pages.home.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

import com.aston.basketballapp.R;
import com.aston.basketballapp.engine.model.standings.TeamStandingModel;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;
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
    private MainFragmentViewModel mainFragmentViewModel;
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
        mainFragmentViewModel = new ViewModelProvider(getActivity()).get(MainFragmentViewModel.class);
        tableLayout = v.findViewById(R.id.schedule_statistics_table);

        Observer<LiveDataStateData<ArrayList<String>>> favouriteTeamObserver = new Observer<LiveDataStateData<ArrayList<String>>>() {
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
                        TextView favouriteTeamConference = v.findViewById(R.id.favourite_team_conference_ranking);
                        favouriteTeamConference.setText(conferenceString);

                        String recordString = "Record : ";
                        recordString = recordString + data.get(2) + " - " + data.get(3);
                        TextView favouriteRecord = v.findViewById(R.id.favourite_team_record);
                        favouriteRecord.setText(recordString);

                        TextView favouriteName = v.findViewById(R.id.favourite_team_name);
                        favouriteName.setText(mainFragmentViewModel.getFavouriteTeamName(getActivity().getPreferences(Context.MODE_PRIVATE)));

                        ImageView imageView = v.findViewById(R.id.favourite_team_icon_image);
                        Picasso.get().load(mainFragmentViewModel.getFavouriteTeamLink(getActivity().getPreferences(Context.MODE_PRIVATE))).into(imageView);


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

        mainFragmentViewModel.getPlayers(mainFragmentViewModel.getFavouriteId(getActivity().getPreferences(Context.MODE_PRIVATE))).observe(getViewLifecycleOwner(), favouriteTeamObserver);

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

        mainFragmentViewModel.getSchedule("east").observe(getViewLifecycleOwner(), scheduleObserver);

        MaterialButtonToggleGroup toggleGroup = v.findViewById(R.id.schedule_button_group);

        MaterialButton eastButton = v.findViewById(R.id.east_schedule_button);
        MaterialButton westButton = v.findViewById(R.id.west_schedule_button);

        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conference = "east";

                mainFragmentViewModel.getSchedule(conference).observe(getViewLifecycleOwner(), scheduleObserver);
            }
        });

        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conference = "west";
                mainFragmentViewModel.getSchedule(conference).observe(getViewLifecycleOwner(), scheduleObserver);
            }
        });

        errorButtonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFragmentViewModel.getSchedule(conference).observe(getViewLifecycleOwner(), scheduleObserver);
            }
        });

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFragmentViewModel.getPlayers(mainFragmentViewModel.getFavouriteId(getActivity().getPreferences(Context.MODE_PRIVATE))).observe(getViewLifecycleOwner(), favouriteTeamObserver);
            }
        });

        TextView favouriteTeamTitle = v.findViewById(R.id.favourite_team_title);
        MaterialCardView favouriteTeamWidgetPage = v.findViewById(R.id.favourite_team_widget_landing_page);


        if(mainFragmentViewModel.getFavouriteId(getActivity().getPreferences(Context.MODE_PRIVATE)).equals("-1")) {
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
            TableRow topRow = new TableRow(getContext());
            for( String header : mainFragmentViewModel.headers) {
                TextView tv0 = new TextView(getContext());
                tv0.setText(header);
                tv0.setPadding(10, 30, 10, 30);
                tv0.setTextColor(Color.BLACK);
                tv0.setGravity(Gravity.CENTER);
                tv0.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border_header));
                topRow.addView(tv0);
            }
            tableLayout.addView(topRow);
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
                        .load(mainFragmentViewModel.getTeamLogo(team.getTeamId()))
                        .into(view);
                view.setLayoutParams(new TableRow.LayoutParams(
                        111,
                        111
                ));
                tbrow.addView(view);

                tv = new TextView(getContext());
                tv.setText(mainFragmentViewModel.getTeamName(team.getTeamId()));
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