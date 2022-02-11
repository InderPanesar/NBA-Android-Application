package com.aston.basketballapp.pages.home.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aston.basketballapp.R;
import com.aston.basketballapp.engine.model.schedule.GamesModel;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ScheduleBaseAdapter extends RecyclerView.Adapter<ScheduleBaseAdapter.ScheduleBaseAdapterViewHolder> {

    private static ScheduleCardClicked itemListener;

    ArrayList<GamesModel> games;
    Context context;
    float dpScale;




    public ScheduleBaseAdapter(Context ct, ArrayList<GamesModel> _games, ScheduleCardClicked _itemListener, float _dpScale) {
        context = ct;
        games = _games;
        itemListener = _itemListener;
        dpScale = _dpScale;
    }

    @NonNull
    @Override
    public ScheduleBaseAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.schedule_icon_item, parent, false);
        return new ScheduleBaseAdapterViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ScheduleBaseAdapterViewHolder holder, int position) {
        GamesModel model = games.get(position);
        Picasso.get().load(games.get(position).gethTeam().getLogo()).into(holder.homeTeamLogo);
        holder.homeTeamScore.setText(games.get(position).gethTeam().getScore().getPoints());
        holder.homeTeamText.setText(games.get(position).gethTeam().getNickName());

        Picasso.get().load(games.get(position).getvTeam().getLogo()).into(holder.awayTeamLogo);
        holder.awayTeamScore.setText(games.get(position).getvTeam().getScore().getPoints());
        holder.awayTeamText.setText(games.get(position).getvTeam().getNickName());

        String status = games.get(position).getStatusGame();
        String isHalfTime = games.get(position).getHalftime();

        holder.gameStatus.setText(status);

        //Changing heights of cards programmatically and including DP. To ensure scales correctly.
        int normalHeight = (int) (250 * dpScale + 0.5f);
        int smallerHeight = (int) (200 * dpScale + 0.5f);

        ViewGroup.LayoutParams params = holder.view.getLayoutParams();
        params.height = normalHeight;
        holder.view.setLayoutParams(params);

        if (status.equals("Scheduled")) {
            holder.button.setVisibility(View.INVISIBLE);
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH);
            LocalDateTime date = LocalDateTime.parse(games.get(position).getStartTimeUTC(), inputFormatter);
            holder.gameTimeStart.setText(outputFormatter.format(date));
            holder.gameTimeStart.setVisibility(View.VISIBLE);
            params.height = smallerHeight;
            holder.view.setLayoutParams(params);

        }
        else if (status.equals("In Play")) {
            if(isHalfTime.equals("1")) {
                holder.gameTimeStart.setText("Half Time");
            }
            else {
                holder.gameTimeStart.setText("Quarter: " + games.get(position).getCurrentPeriod());
            }
            holder.gameTimeStart.setVisibility(View.VISIBLE);
        }
        else if(status.equals("Finished")) {
            holder.gameTimeStart.setVisibility(View.GONE);
            holder.button.setVisibility(View.VISIBLE);


        }


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.scheduleCardClicked(holder.itemView, model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setGames(ArrayList<GamesModel> games) {
        this.games = games;
        notifyDataSetChanged();
    }

    public class ScheduleBaseAdapterViewHolder extends RecyclerView.ViewHolder  {

        View view;

        ImageView homeTeamLogo;
        ImageView awayTeamLogo;

        TextView homeTeamText;
        TextView awayTeamText;
        TextView homeTeamScore;
        TextView awayTeamScore;

        TextView gameStatus;
        TextView gameTimeStart;

        MaterialButton button;

        public ScheduleBaseAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            homeTeamLogo = itemView.findViewById(R.id.home_team_image);
            awayTeamLogo = itemView.findViewById(R.id.away_team_image);
            homeTeamText = itemView.findViewById(R.id.home_team_short_mame);
            awayTeamText = itemView.findViewById(R.id.away_team_short_name);
            homeTeamScore = itemView.findViewById(R.id.home_team_score);
            awayTeamScore = itemView.findViewById(R.id.away_team_score);
            gameStatus = itemView.findViewById(R.id.schedule_game_status);
            gameTimeStart = itemView.findViewById(R.id.schedule_game_time);
            button = itemView.findViewById(R.id.schedule_box_score_button);
        }


    }
}