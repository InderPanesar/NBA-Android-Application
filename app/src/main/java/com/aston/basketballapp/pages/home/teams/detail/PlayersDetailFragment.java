package com.aston.basketballapp.pages.home.teams.detail;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.aston.basketballapp.R;
import com.aston.basketballapp.pages.home.settings.favouriteTeam.TeamsRepo;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class PlayersDetailFragment extends Fragment {

    //ViewModel for Player Details.
    PlayersDetailViewModel viewModel;
    //Player Stats table.
    TableLayout playerStatsTable;
    //Locally Contain TableRows to remove them onDestroy.
    ArrayList<TableRow> removeRows = new ArrayList<>();


    public PlayersDetailFragment() { }

    public static PlayersDetailFragment newInstance() {
        return new PlayersDetailFragment();
    }

    @Override
    public void onDestroy() {
        for(TableRow row : removeRows) {
            playerStatsTable.removeView(row);
        }
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set Toolbar title to Player's Name.
        AppConsts.verifyActivity(getActivity());
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        AppConsts.verifyArguments(getArguments());
        toolbar.setTitle(getArguments().getString("playerName"));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AppConsts.verifyArguments(getArguments());
        String[] playerAttributes = getArguments().getStringArray("playerAttributes");
        View v = inflater.inflate(R.layout.fragment_players_detail, container, false);
        AppConsts.verifyActivity(getActivity());
        viewModel = new ViewModelProvider(this.getActivity()).get(PlayersDetailViewModel.class);

        //Set Overall Player Information to each specific data row.
        TextView textView =  v.findViewById(R.id.players_detail_years_pro);
        textView.setText(playerAttributes[0]);
        textView = v.findViewById(R.id.players_detail_college);
        textView.setText(playerAttributes[1]);
        textView = v.findViewById(R.id.players_detail_country);
        textView.setText(playerAttributes[2]);
        textView = v.findViewById(R.id.players_detail_player_id);
        textView.setText(playerAttributes[3]);
        textView = v.findViewById(R.id.players_detail_first_year);
        textView.setText(playerAttributes[4]);
        textView = v.findViewById(R.id.players_detail_height);
        textView.setText(playerAttributes[5]);
        textView = v.findViewById(R.id.players_detail_weight);
        textView.setText(playerAttributes[6]);
        textView = v.findViewById(R.id.player_team_name);

        playerStatsTable = v.findViewById(R.id.team_player_stats);

        try {
            textView.setText(viewModel.teams.get(Integer.parseInt(playerAttributes[7])));
        }
        catch (NumberFormatException e) {
            textView.setText(R.string.InformationNotaAvaliablePlayerCard);
        }

        textView = v.findViewById(R.id.player_team_jersey);
        textView.setText(playerAttributes[8]);
        textView = v.findViewById(R.id.player_team_position);
        textView.setText(playerAttributes[9]);
        textView = v.findViewById(R.id.player_team_status_title);
        System.out.println(playerAttributes[10]);
        if(playerAttributes[10].equals("true")) {
            textView.setText(R.string.PlayerIsActive);
        }
        else {
            textView.setText(R.string.PlayerIsNotActive);
            AppConsts.verifyContext(getContext());
            textView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_selected));
        }

        ImageView view = v.findViewById(R.id.player_team_image_view);
        TeamsRepo repo = new TeamsRepo();
        if(playerAttributes[7] == null) {
            Picasso.get().load(viewModel.nbaLogoURL).fit().into(view);
        }
        else {
            int teamID = Integer.parseInt(playerAttributes[7]);
            for(TeamsRepo.LocalTeam team : repo.getTeamList()) {
                if(teamID == team.getId()) {
                    Picasso.get().load(team.getLogoURL()).fit().centerCrop().fit().into(view);
                }
            }
        }




        Observer<LiveDataStateData<ArrayList<SinglePlayerStatsAdapter>>> recentGamesAdapterObserver = stats -> {
            switch (stats.getStatus()) {
                case SUCCESS:
                    setTable(v, stats.getData());
                    UniversalErrorStateHandler.isSuccess(v);
                    break;
                case ERROR:
                    UniversalErrorStateHandler.isError(v);
                    break;
                case LOADING:
                    UniversalErrorStateHandler.isLoading(v);
                    break;
            }
        };

        AppConsts.verifyActivity(getActivity());
        AppConsts.verifyArguments(getArguments());
        viewModel.getPlayerGameStats(getArguments().getString("playerId"), getActivity().getPreferences(Context.MODE_PRIVATE)).observe(getViewLifecycleOwner(), recentGamesAdapterObserver);

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(view1 ->
                viewModel.getPlayerGameStats(getArguments().getString("playerId"), getActivity().getPreferences(Context.MODE_PRIVATE)).observe(getViewLifecycleOwner(), recentGamesAdapterObserver)
        );


        return v;
    }


    //Create Tables for the PlayerStats.
    public void setTable (View v, ArrayList<SinglePlayerStatsAdapter> stats) {

        for(TableRow row : removeRows) {
            playerStatsTable.removeView(row);
        }

        if(stats == null) {
            TextView header = v.findViewById(R.id.recent_game_header);
            header.setVisibility(View.INVISIBLE);
            playerStatsTable.setVisibility(View.INVISIBLE);
        }
        else if(stats.get(0).attributes.size() == 0){
            TextView header = v.findViewById(R.id.recent_game_header);
            header.setVisibility(View.INVISIBLE);
            playerStatsTable.setVisibility(View.INVISIBLE);
        }
        else {
            TableRow topRow = new TableRow(getContext());
            for( String header : stats.get(0).topics) {
                TextView tv0 = new TextView(getContext());
                tv0.setText(header);
                tv0.setTextColor(Color.BLACK);
                tv0.setGravity(Gravity.CENTER);
                tv0.setPadding(5, 10, 5, 10);
                AppConsts.verifyContext(getContext());
                tv0.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border_header));
                topRow.addView(tv0);
                removeRows.add(topRow);
            }
            playerStatsTable.addView(topRow);
            for (SinglePlayerStatsAdapter _stats : stats) {
                TableRow tbrow = new TableRow(getContext());
                for(String value : _stats.attributes) {

                    TextView tv = new TextView(getContext());
                    if(TextUtils.isEmpty(value)) { tv.setText("n/a"); }
                    else if(value.equals("")) { tv.setText("n/a"); }
                    else { tv.setText(value); }
                    tv.setTextColor(Color.BLACK);
                    tv.setGravity(Gravity.CENTER);
                    tv.setPadding(5, 10, 5, 10);
                    AppConsts.verifyContext(getContext());
                    tv.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border));
                    tbrow.addView(tv);
                }
                playerStatsTable.addView(tbrow);


            }


        }

    }

}