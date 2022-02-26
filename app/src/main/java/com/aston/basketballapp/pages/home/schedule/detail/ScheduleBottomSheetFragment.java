package com.aston.basketballapp.pages.home.schedule.detail;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.aston.basketballapp.databinding.FragmentScheduleBottomSheetBinding;
import com.aston.basketballapp.utils.AppConsts;
import com.aston.basketballapp.utils.livedata.LiveDataStateData;
import com.aston.basketballapp.utils.livedata.UniversalErrorStateHandler;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.aston.basketballapp.R;
import com.squareup.picasso.Picasso;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ScheduleBottomSheetFragment extends BottomSheetDialogFragment {

    Observer<LiveDataStateData<ArrayList<String>>> statisticsObserver;
    ScheduleBottomSheetViewModel scheduleBottomSheetViewModel;
    TableLayout tableLayout;

    private FragmentScheduleBottomSheetBinding binding;

    public static ScheduleBottomSheetFragment newInstance() {
        return new ScheduleBottomSheetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentScheduleBottomSheetBinding.inflate(inflater, container, false);
        AppConsts.verifyActivity(getActivity());
        scheduleBottomSheetViewModel = new ViewModelProvider(this.getActivity()).get(ScheduleBottomSheetViewModel.class);

        //Create a table layout and load.
        tableLayout = binding.getRoot().findViewById(R.id.schedule_statistics_table);

        //Load each teams image
        ImageView iv = binding.getRoot().findViewById(R.id.home_team_image);
        AppConsts.verifyArguments(getArguments());
        Picasso.get().load(AppConsts.URLImageCorrector(getArguments().getString("homeTeamLogo"))).into(iv);
        iv = binding.getRoot().findViewById(R.id.away_team_image);
        Picasso.get().load(AppConsts.URLImageCorrector(getArguments().getString("awayTeamLogo"))).into(iv);

        TextView textView = binding.getRoot().findViewById(R.id.home_team_score);
        textView.setText(getArguments().getString("homeTeamScore", ""));
        textView = binding.getRoot().findViewById(R.id.table_team1_title);
        textView.setText(getArguments().getString("homeTeamNickName", ""));

        textView = binding.getRoot().findViewById(R.id.away_team_score);
        textView.setText(getArguments().getString("awayTeamScore", ""));
        textView = binding.getRoot().findViewById(R.id.table_team2_title);
        textView.setText(getArguments().getString("awayTeamNickName", ""));

        statisticsObserver = stateLiveData -> {
            switch (stateLiveData.getStatus()) {
                case SUCCESS:
                    ArrayList<String> data = stateLiveData.getData();
                    tableLayout.setVisibility(View.VISIBLE);
                    View v = binding.getRoot();
                    TextView view;

                    if(data != null) {
                        //Set Data for each team in the right rows in the table.
                        view = v.findViewById(R.id.table_team1_fbp);
                        view.setText(data.get(0));
                        view = v.findViewById(R.id.table_team1_pip);
                        view.setText(data.get(1));
                        view = v.findViewById(R.id.table_team1_bl);
                        view.setText(data.get(2));
                        view = v.findViewById(R.id.table_team1_pot);
                        view.setText(data.get(3));
                        view = v.findViewById(R.id.table_team1_FTP);
                        view.setText(data.get(4));
                        view = v.findViewById(R.id.table_team1_or);
                        view.setText(data.get(5));
                        view = v.findViewById(R.id.table_team1_dr);
                        view.setText(data.get(6));
                        view = v.findViewById(R.id.table_team1_assists);
                        view.setText(data.get(7));
                        view = v.findViewById(R.id.table_team1_plus_minus);
                        view.setText(data.get(8));

                        view = v.findViewById(R.id.table_team2_fbp);
                        view.setText(data.get(9));
                        view = v.findViewById(R.id.table_team2_pip);
                        view.setText(data.get(10));
                        view = v.findViewById(R.id.table_team2_bl);
                        view.setText(data.get(11));
                        view = v.findViewById(R.id.table_team2_pot);
                        view.setText(data.get(12));
                        view = v.findViewById(R.id.table_team2_FTP);
                        view.setText(data.get(13));
                        view = v.findViewById(R.id.table_team2_or);
                        view.setText(data.get(14));
                        view = v.findViewById(R.id.table_team2_dr);
                        view.setText(data.get(15));
                        view = v.findViewById(R.id.table_team2_assists);
                        view.setText(data.get(16));
                        view = v.findViewById(R.id.table_team2_plus_minus);
                        view.setText(data.get(17));

                        UniversalErrorStateHandler.isSuccess(binding.getRoot());
                    }
                    break;
                case ERROR:
                    tableLayout.setVisibility(View.INVISIBLE);
                    UniversalErrorStateHandler.isError(binding.getRoot());
                    break;
                case LOADING:
                    tableLayout.setVisibility(View.INVISIBLE);
                    UniversalErrorStateHandler.isLoading(binding.getRoot());
                    break;
            }
        };

        scheduleBottomSheetViewModel.getGameStatistics(getArguments().getString("gameId", "-1")).observe(getViewLifecycleOwner(), statisticsObserver);

        UniversalErrorStateHandler.getRetryButton(binding.getRoot()).setOnClickListener(v -> {
            AppConsts.verifyArguments(getArguments());
            scheduleBottomSheetViewModel.getGameStatistics(getArguments().getString("gameId", "-1")).observe(getViewLifecycleOwner(), statisticsObserver);
        });


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}