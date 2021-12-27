package com.aston.basicarchitecture.pages.home.schedule;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;
import com.aston.basicarchitecture.engine.model.schedule.GamesModel;
import com.aston.basicarchitecture.engine.model.teams.IndividualTeamsModel;
import com.aston.basicarchitecture.pages.home.players.PlayersBaseAdapter;
import com.aston.basicarchitecture.pages.home.teams.TeamsBaseViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleBaseFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    MaterialButton scheduleButton;
    DatePickerDialog datePickerDialog;
    ScheduleBaseViewModel scheduleBaseViewModel;
    RecyclerView recyclerView;
    ScheduleBaseAdapter scheduleBaseAdapter;

    ArrayList<GamesModel> games = new ArrayList<>();
    Observer<ArrayList<GamesModel>> nameObserver;


    public ScheduleBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Schedule.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduleBaseFragment newInstance(String param1, String param2) {
        ScheduleBaseFragment fragment = new ScheduleBaseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        scheduleBaseViewModel = new ViewModelProvider(this.getActivity()).get(ScheduleBaseViewModel.class);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        scheduleButton = v.findViewById(R.id.ScheduleDatePickerButton);
        scheduleButton.setText(new StringBuilder().append("Date: ").append(mDay).append("/").append(mMonth).append("/").append(mYear).toString());
        datePickerDialog = new DatePickerDialog(getContext(), this, mYear, mMonth - 1, mDay);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        recyclerView = v.findViewById(R.id.scheduleRecyclerView);
        //TODO: Implement Loading State
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        scheduleBaseAdapter = new ScheduleBaseAdapter(getContext(), games);
        recyclerView.setAdapter(scheduleBaseAdapter);


        //Observer
        nameObserver = new Observer<ArrayList<GamesModel>>() {
            @Override
            public void onChanged(ArrayList<GamesModel> games) {
                for(GamesModel model : games) {
                    Log.d("Player: ", model.getCity() + " " + model.getArena() + " " + model.getGameId());
                }
                scheduleBaseAdapter.setGames(games);
                recyclerView.setVisibility(View.VISIBLE);
                //TODO: Loading State NOT VISIBLE
            }
        };

        scheduleBaseViewModel.getGamesOnDate(mYear + "-" + mMonth + "-" + mDay).observe(getViewLifecycleOwner(), nameObserver);

        return v;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        scheduleButton.setText(new StringBuilder().append("Date: ").append(dayOfMonth).append("/").append(month + 1).append("/").append(year).toString());
        scheduleBaseViewModel.getGamesOnDate(year + "-" + (month+1) + "-" + dayOfMonth).observe(getViewLifecycleOwner(), nameObserver);

    }
}