package com.aston.basketballapp.pages.home.schedule;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.aston.basketballapp.R;
import com.aston.basketballapp.engine.model.schedule.GamesModel;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduleBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleBaseFragment extends Fragment implements DatePickerDialog.OnDateSetListener, ScheduleCardClicked {

    MaterialButton scheduleButton;
    DatePickerDialog datePickerDialog;
    ScheduleBaseViewModel scheduleBaseViewModel;
    RecyclerView scheduleRecyclerView;
    ScheduleBaseAdapter scheduleBaseAdapter;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c;

    ArrayList<GamesModel> games = new ArrayList<>();

    Observer<LiveDataStateData<ArrayList<GamesModel>>> gameScheduleObserver;


    public ScheduleBaseFragment() {
        // Required empty public constructor
    }


    public static ScheduleBaseFragment newInstance() {
        return new ScheduleBaseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AppConsts.verifyActivity(getActivity());
        scheduleBaseViewModel = new ViewModelProvider(this.getActivity()).get(ScheduleBaseViewModel.class);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        //Create all the data involved with Calendar Data.
        c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH) + 1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        scheduleButton = v.findViewById(R.id.schedule_date_picker_button);
        scheduleButton.setText(new StringBuilder().append("Date: ").append(mDay).append("/").append(mMonth).append("/").append(mYear).toString());
        datePickerDialog = new DatePickerDialog(getContext(), this, mYear, mMonth - 1, mDay);
        scheduleButton.setOnClickListener(v1 -> datePickerDialog.show());

        scheduleRecyclerView = v.findViewById(R.id.schedule_recycler_view);
        //TODO: Implement Loading State
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( getActivity() );
        linearLayoutManager.setOrientation( LinearLayoutManager.VERTICAL );
        scheduleRecyclerView.setLayoutManager(linearLayoutManager);
        scheduleRecyclerView.setNestedScrollingEnabled(false);
        scheduleRecyclerView.setHasFixedSize(false);

        //Pass the Density of the current screen to ensure schedule widget scales correctly.
        AppConsts.verifyContext(getContext());
        float value = getContext().getResources().getDisplayMetrics().density;
        scheduleBaseAdapter = new ScheduleBaseAdapter(getContext(), games, this, value);
        scheduleRecyclerView.setAdapter(scheduleBaseAdapter);


        //Observer for the game schedule.
        gameScheduleObserver = stateLiveData -> {
            switch (stateLiveData.getStatus()) {
                case SUCCESS:
                    games = stateLiveData.getData();
                    scheduleRecyclerView.setVisibility(View.VISIBLE);
                    scheduleBaseAdapter.setGames(games);
                    UniversalErrorStateHandler.isSuccess(v);
                    break;
                case ERROR:
                    scheduleRecyclerView.setVisibility(View.INVISIBLE);
                    UniversalErrorStateHandler.isError(v);
                    break;
                case LOADING:
                    scheduleRecyclerView.setVisibility(View.INVISIBLE);
                    UniversalErrorStateHandler.isLoading(v);
                    break;
            }
        };

        scheduleBaseViewModel.getGamesOnDate(dateFormat.format(c.getTime())).observe(getViewLifecycleOwner(), gameScheduleObserver);

        UniversalErrorStateHandler.getRetryButton(v).setOnClickListener(v12 ->
                scheduleBaseViewModel.getGamesOnDate(dateFormat.format(c.getTime())).observe(getViewLifecycleOwner(), gameScheduleObserver)
        );

        return v;
    }

    //On Date Set create update calendar, UI Text and update schedules shown.
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c.set(year, month, dayOfMonth);
        scheduleButton.setText(new StringBuilder().append("Date: ").append(dayOfMonth).append("/").append(month + 1).append("/").append(year).toString());
        scheduleBaseViewModel.getGamesOnDate(dateFormat.format(c.getTime())).observe(getViewLifecycleOwner(), gameScheduleObserver);
    }

    //Send Information to Bottom Sheet through the Bundle.
    private void showBottomSheetDialog(View v, GamesModel gamesModel) {
        Bundle b = new Bundle();
        b.putString("homeTeamLogo", gamesModel.gethTeam().getLogo());
        b.putString("homeTeamScore", gamesModel.gethTeam().getScore().getPoints());
        b.putString("homeTeamNickName", gamesModel.gethTeam().getNickName());

        b.putString("awayTeamLogo", gamesModel.getvTeam().getLogo());
        b.putString("awayTeamScore", gamesModel.getvTeam().getScore().getPoints());
        b.putString("awayTeamNickName", gamesModel.getvTeam().getNickName());

        b.putString("gameId", gamesModel.getGameId());


        Navigation.findNavController(v).navigate(R.id.action_schedule_to_scheduleBottomSheetFragment, b);

    }

    //Show Bottom Sheet with fragment information when Box Score button clicked.
    @Override
    public void scheduleCardClicked(View v, GamesModel gamesModel) {
        showBottomSheetDialog(getView(), gamesModel);
    }
}