package com.aston.basicarchitecture.pages.home.players;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aston.basicarchitecture.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayersBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayersBaseFragment extends Fragment {



    public PlayersBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment players.
     */
    // TODO: Rename and change types and number of parameters
    public static PlayersBaseFragment newInstance() {
        PlayersBaseFragment fragment = new PlayersBaseFragment();
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
        View v = inflater.inflate(R.layout.fragment_players, container, false);

        return v;
    }
}