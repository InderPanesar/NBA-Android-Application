package com.aston.basicarchitecture.pages.home.players.detail;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aston.basicarchitecture.R;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayersDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersDetailFragment extends Fragment {


    Map<Integer, String> teams;
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
        TextView textView =  v.findViewById(R.id.playersDetailYearsPro);
        textView.setText(playerAttributes[0]);
        textView = v.findViewById(R.id.playersDetailCollege);
        textView.setText(playerAttributes[1]);
        textView = v.findViewById(R.id.playersDetailCountry);
        textView.setText(playerAttributes[2]);
        textView = v.findViewById(R.id.playersDetailPlayerId);
        textView.setText(playerAttributes[3]);
        textView = v.findViewById(R.id.playersDetailFirstYear);
        textView.setText(playerAttributes[4]);
        textView = v.findViewById(R.id.playersDetailHeight);
        textView.setText(playerAttributes[5]);
        textView = v.findViewById(R.id.playersDetailWeight);
        textView.setText(playerAttributes[6]);

        textView = v.findViewById(R.id.playerTeamName);
        textView.setText(teams.get(Integer.parseInt(playerAttributes[7])));

        textView = v.findViewById(R.id.playerTeamJersey);
        textView.setText(playerAttributes[8]);
        textView = v.findViewById(R.id.playerTeamPosition);
        textView.setText(playerAttributes[9]);
        textView = v.findViewById(R.id.playerTeamStatusTitle);
        if(playerAttributes[10].equals("1")) {
            textView.setText("Active");
        }
        else {
            textView.setText("Not Active");
        }
        return v;
    }


}