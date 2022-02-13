package com.aston.basketballapp.pages.home.teams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aston.basketballapp.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aston.basketballapp.engine.model.teams.IndividualTeamsModel;
import com.aston.basketballapp.utils.AppConsts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.MyViewHolder> {
    private static TeamsCardClicked itemListener;
    ArrayList<IndividualTeamsModel> teams;
    Context context;



    public TeamsAdapter(Context ct, ArrayList<IndividualTeamsModel> _teams, TeamsCardClicked itemListener) {
        context = ct;
        teams = _teams;
        this.itemListener = itemListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.teams_icon_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.teamCity.setText(teams.get(position).getCity());
        holder.teamName.setText(teams.get(position).getNickname());
        //Load ImageURL into teams.
        Picasso.get().load(AppConsts.URLImageCorrector(teams.get(position).getLogo())).into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setTeams(ArrayList<IndividualTeamsModel> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView logo;
        TextView teamCity, teamName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            teamCity = itemView.findViewById(R.id.teams_icon_teams_city);
            teamName = itemView.findViewById(R.id.teams_icon_teams_name);
            logo = itemView.findViewById(R.id.teams_icon_team_logo);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            itemListener.cardClicked(v, teams.get(getLayoutPosition()));
        }
    }
}
