package com.aston.basketballapp.pages.home.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
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
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainBaseFragment extends Fragment {

    //Favourite Team Widget
    private LinearLayout favouriteTeamWidget;
    //ViewModel for this fragment
    private MainFragmentViewModel mainFragmentViewModel;
    //TableLayout for the schedule
    private TableLayout scheduleLayout;

    //Empty Constructor For MainBaseFragment
    public MainBaseFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        AppConsts.verifyActivity(getActivity());

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_landing_page, container, false);

        //Get Text Area
        favouriteTeamWidget =  v.findViewById(R.id.favourite_team_widget);

        //Set the ViewModel
        mainFragmentViewModel = new ViewModelProvider(getActivity()).get(MainFragmentViewModel.class);
        scheduleLayout = v.findViewById(R.id.schedule_statistics_table);

        //Observer for the favourite team.
        Observer<LiveDataStateData<ArrayList<String>>> favouriteTeamObserver = stateLiveData -> {
            switch (stateLiveData.getStatus()) {
                case SUCCESS:
                    //If Widgets Exists then set Visibility to true.
                    favouriteTeamWidget.setVisibility(View.VISIBLE);
                    ArrayList<String> data = stateLiveData.getData();
                    UniversalErrorStateHandler.isSuccess(v);

                    AppConsts.verifyActivity(getActivity());

                    //Load all components.
                    TextView favouriteTeamConference = v.findViewById(R.id.favourite_team_conference_ranking);
                    TextView favouriteRecord = v.findViewById(R.id.favourite_team_record);
                    TextView favouriteName = v.findViewById(R.id.favourite_team_name);
                    ImageView imageView = v.findViewById(R.id.favourite_team_icon_image);

                    if(data != null) {
                        favouriteTeamConference.setText(mainFragmentViewModel.getConferenceString(data));
                        favouriteRecord.setText(mainFragmentViewModel.getRecordString(data));
                    }
                    favouriteName.setText(mainFragmentViewModel.getFavouriteTeamName(getActivity().getPreferences(Context.MODE_PRIVATE)));
                    //Load image from URL using Picasso.
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
        };

        //Get Favourite Team Information.
        mainFragmentViewModel.getFavouriteTeamInformation(mainFragmentViewModel.getFavouriteId(getActivity().getPreferences(Context.MODE_PRIVATE))).observe(getViewLifecycleOwner(), favouriteTeamObserver);

        //Additional ProgressBar,ErrorStates and ErrorButton for the Schedule.
        ProgressBar bar = v.findViewById(R.id.schedule_table_progress_bar);
        LinearLayout errorStates = v.findViewById(R.id.schedule_table_error_state);
        MaterialButton errorButtonSchedule = v.findViewById(R.id.schedule_table_retry_button);

        //Observer for the Schedule of the team.
        Observer<LiveDataStateData<ArrayList<TeamStandingModel>>> scheduleObserver = stateLiveData -> {
            switch (stateLiveData.getStatus()) {
                case SUCCESS:
                    scheduleLayout.removeAllViews();
                    ArrayList<TeamStandingModel> data = stateLiveData.getData();
                    if(data != null) {
                        setTable(data);
                    }
                    bar.setVisibility(View.INVISIBLE);
                    scheduleLayout.setVisibility(View.VISIBLE);
                    errorStates.setVisibility(View.INVISIBLE);
                    break;
                case ERROR:
                    bar.setVisibility(View.INVISIBLE);
                    scheduleLayout.setVisibility(View.INVISIBLE);
                    errorStates.setVisibility(View.VISIBLE);
                    break;
                case LOADING:
                    bar.setVisibility(View.VISIBLE);
                    scheduleLayout.setVisibility(View.INVISIBLE);
                    errorStates.setVisibility(View.INVISIBLE);
                    break;
            }
        };

        //Get Schedule Information.
        mainFragmentViewModel.getSchedule().observe(getViewLifecycleOwner(), scheduleObserver);

        MaterialButton eastButton = v.findViewById(R.id.east_schedule_button);
        MaterialButton westButton = v.findViewById(R.id.west_schedule_button);

        //On East Button Click update Schedule
        eastButton.setOnClickListener(v1 -> {
            mainFragmentViewModel.conference = "east";
            mainFragmentViewModel.getSchedule().observe(getViewLifecycleOwner(), scheduleObserver);
        });

        //On East Button Click update Schedule
        westButton.setOnClickListener(v12 -> {
            mainFragmentViewModel.conference = "west";
            mainFragmentViewModel.getSchedule().observe(getViewLifecycleOwner(), scheduleObserver);
        });

        //Update Error State for Schedule
        errorButtonSchedule.setOnClickListener(v13 -> mainFragmentViewModel.getSchedule().observe(getViewLifecycleOwner(), scheduleObserver));

        //Update Error State for Favourite Team Fragment
        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(v14 -> {
            AppConsts.verifyActivity(getActivity());
            mainFragmentViewModel.getFavouriteTeamInformation(mainFragmentViewModel.getFavouriteId(getActivity().getPreferences(Context.MODE_PRIVATE))).observe(getViewLifecycleOwner(), favouriteTeamObserver);
        });

        TextView favouriteTeamTitle = v.findViewById(R.id.favourite_team_title);
        MaterialCardView favouriteTeamWidgetPage = v.findViewById(R.id.favourite_team_widget_landing_page);

        AppConsts.verifyActivity(getActivity());
        //If Favourite team doesn't exist.
        if(mainFragmentViewModel.getFavouriteId(getActivity().getPreferences(Context.MODE_PRIVATE)).equals("-1")) {
            favouriteTeamTitle.setVisibility(View.GONE);
            favouriteTeamWidgetPage.setVisibility(View.GONE);
        }

        mainFragmentViewModel.conference = "east";

        return v;
    }

    //Set Table for the Schedule.
    public void setTable ( ArrayList<TeamStandingModel> teams) {


        if(teams.size() == 0) {
            scheduleLayout.setVisibility(View.INVISIBLE);
        }
        else {
            TableRow topRow = new TableRow(getContext());
            for( String header : mainFragmentViewModel.headers) {
                TextView tv0 = new TextView(getContext());
                tv0.setText(header);
                tv0.setPadding(10, 30, 10, 30);
                tv0.setTextColor(Color.BLACK);
                tv0.setGravity(Gravity.CENTER);
                AppConsts.verifyContext(getContext());
                tv0.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border_header));
                topRow.addView(tv0);
            }
            scheduleLayout.addView(topRow);
            for (TeamStandingModel team : teams) {
                TableRow tbrow = (TableRow) getLayoutInflater().inflate(R.layout.standings_table_image_view, null);


                ImageView iv = tbrow.findViewById(R.id.standings_table_icon);
                Picasso.get()
                        .load(mainFragmentViewModel.getTeamLogo(team.getTeam().getId() + ""))
                        .into(iv);

                TextView tv = tbrow.findViewById(R.id.standings_seed_number);
                tv.setText(team.getConference().getRank());

                tv = tbrow.findViewById(R.id.standings_team_name);
                tv.setText(mainFragmentViewModel.getTeamName(team.getTeam().getId() + ""));

                tv = tbrow.findViewById(R.id.standings_team_record);
                tv.setText(mainFragmentViewModel.getRecordStringForTeam(team));
                

                scheduleLayout.addView(tbrow);


            }


        }

    }




}