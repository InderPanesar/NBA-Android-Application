package com.aston.basicarchitecture.pages.home.players.detail;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.pages.home.settings.favouriteTeam.TeamsRepo;
import com.aston.basicarchitecture.utils.livedata.LiveDataStateData;
import com.aston.basicarchitecture.utils.livedata.UniversalErrorStateHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayersDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersDetailFragment extends Fragment {


    Map<Integer, String> teams;
    PlayersDetailViewModel viewModel;
    TableLayout tableLayout;
    ArrayList<TableRow> removeRows = new ArrayList<>();


    public PlayersDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlayersDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayersDetailFragment newInstance(String param1, String param2) {
        PlayersDetailFragment fragment = new PlayersDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroy() {
        for(TableRow row : removeRows) {
            tableLayout.removeView(row);
        }
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(getArguments().getString("playerName"));

        teams = new HashMap<Integer, String>();
        teams.put(1, "Atlanta Hawks");
        teams.put(2, "Boston Celtics");
        teams.put(4, "Brooklyn Nets");
        teams.put(5, "Charlotte Hornets");
        teams.put(6, "Chicago Bulls");
        teams.put(7, "Cleveland Cavaliers");
        teams.put(8, "Dallas Mavericks");
        teams.put(9, "Denver Nuggets");
        teams.put(10, "Detroit Pistons");
        teams.put(11, "Golden State Warriors");
        teams.put(14, "Houston Rockets");
        teams.put(15, "Indiana Pacers");
        teams.put(16, "Los Angeles Clippers");
        teams.put(17, "Los Angeles Lakers");
        teams.put(19, "Memphis Grizzlies");
        teams.put(20, "Miami Heat");
        teams.put(21, "Milwaukee Bucks");
        teams.put(22, "Minnesota Timberwolves");
        teams.put(23, "New Orleans Pelicans");
        teams.put(24, "New York Knicks");
        teams.put(25, "Oklahoma City Thunder");
        teams.put(26, "Orlando Magic");
        teams.put(27, "Philadelphia 76ers");
        teams.put(28, "Phoenix Suns");
        teams.put(29, "Portland Trail Blazers");
        teams.put(30, "Sacramento Kings");
        teams.put(31, "San Antonio Spurs");
        teams.put(38, "Toronto Raptors");
        teams.put(40, "Washington Wizards");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String playerAttributes[] = getArguments().getStringArray("playerAttributes");
        View v = inflater.inflate(R.layout.fragment_players_detail, container, false);
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

        tableLayout = v.findViewById(R.id.team_player_stats);

        //If attribute 7 doesn't exist and is null
        try {
            textView.setText(teams.get(Integer.parseInt(playerAttributes[7])));
        }
        catch (NumberFormatException e) {
            textView.setText("Infomation Not Available");
        }

        textView = v.findViewById(R.id.player_team_jersey);
        textView.setText(playerAttributes[8]);
        textView = v.findViewById(R.id.player_team_position);
        textView.setText(playerAttributes[9]);
        textView = v.findViewById(R.id.player_team_status_title);
        if(playerAttributes[10].equals("1")) {
            textView.setText("Active");
        }
        else {
            textView.setText("Not Active");
            textView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.west_red_selected));
        }

        ImageView view = v.findViewById(R.id.player_team_image_view);
        TeamsRepo repo = new TeamsRepo();
        if(playerAttributes[7] == null) {
            Picasso.get().load("https://logoeps.com/wp-content/uploads/2011/05/nba-logo-vector-01.png").fit().into(view);
        }
        else {
            Integer teamID = Integer.parseInt(playerAttributes[7]);
            for(TeamsRepo.LocalTeam team : repo.getTeamList()) {
                if(teamID == team.getId()) {
                    Picasso.get().load(team.getLogoURL()).fit().centerCrop().fit().into(view);
                }
            }
        }


        viewModel = new ViewModelProvider(this.getActivity()).get(PlayersDetailViewModel.class);


        Observer<LiveDataStateData<ArrayList<SinglePlayerStatsAdapter>>> nameObserver = new Observer<LiveDataStateData<ArrayList<SinglePlayerStatsAdapter>>>() {
            @Override
            public void onChanged(LiveDataStateData<ArrayList<SinglePlayerStatsAdapter>> stats) {
                switch (stats.getStatus()) {
                    case SUCCESS:
                        if(stats == null) {
                            TextView header = v.findViewById(R.id.recent_game_header);
                            header.setVisibility(View.INVISIBLE);
                        }
                        else {
                            setTable(v, stats.getData());
                        }
                        UniversalErrorStateHandler.isSuccess(v);
                        break;
                    case ERROR:
                        UniversalErrorStateHandler.isError(v);
                        break;
                    case LOADING:
                        UniversalErrorStateHandler.isLoading(v);
                        break;
                }
            }

        };

        viewModel.getPlayerGameStats(getArguments().getString("playerId"), getActivity().getPreferences(Context.MODE_PRIVATE)).observe(getViewLifecycleOwner(), nameObserver);



        return v;
    }


    public void setTable (View v, ArrayList<SinglePlayerStatsAdapter> stats) {

        for(TableRow row : removeRows) {
            tableLayout.removeView(row);

        }


        if(stats.size() == 0) {
            tableLayout.setVisibility(View.INVISIBLE);
        }
        else {
            TableRow topRow = new TableRow(getContext());
            for( String header : stats.get(0).topics) {
                TextView tv0 = new TextView(getContext());
                tv0.setText(header);
                tv0.setTextColor(Color.BLACK);
                tv0.setGravity(Gravity.CENTER);
                tv0.setPadding(5, 10, 5, 10);
                tv0.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border_header));
                topRow.addView(tv0);
                removeRows.add(topRow);
            }
            tableLayout.addView(topRow);
            for (SinglePlayerStatsAdapter _stats : stats) {
                TableRow tbrow = new TableRow(getContext());
                for(String value : _stats.attributes) {
                    TextView tv = new TextView(getContext());
                    tv.setText(value);
                    tv.setTextColor(Color.BLACK);
                    tv.setGravity(Gravity.CENTER);
                    tv.setPadding(5, 10, 5, 10);
                    tv.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.table_border));
                    tbrow.addView(tv);
                    removeRows.add(tbrow);
                }
                tableLayout.addView(tbrow);


            }


        }

    }


}