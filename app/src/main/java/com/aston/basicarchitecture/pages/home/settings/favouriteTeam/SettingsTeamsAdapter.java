package com.aston.basicarchitecture.pages.home.settings.favouriteTeam;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aston.basicarchitecture.R;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SettingsTeamsAdapter extends RecyclerView.Adapter<SettingsTeamsAdapter.MyViewHolder> {
    private static SettingsTeamClicked itemListener;
    ArrayList<TeamsRepo.LocalTeam> teams;
    Context context;

    MaterialCardView tempCardView;



    public SettingsTeamsAdapter(Context ct, ArrayList<TeamsRepo.LocalTeam> _teams, SettingsTeamClicked itemListener) {
        context = ct;
        teams = _teams;
        this.itemListener = itemListener;

    }

    @NonNull
    @Override
    public SettingsTeamsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.teams_image_icon_item, parent, false);
        return new SettingsTeamsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsTeamsAdapter.MyViewHolder holder, int position) {
        Picasso.get().load(teams.get(position).getLogoURL()).into(holder.logo);
        if(teams.get(position).isSelected) {
            holder.cardView.setCardBackgroundColor(Color.LTGRAY);
            tempCardView = holder.cardView;
        }

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView logo;
        MaterialCardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.teams_image_view);
            cardView = itemView.findViewById(R.id.teams_image_icon_item);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if(tempCardView == cardView) {
                tempCardView = null;
                cardView.setCardBackgroundColor(Color.WHITE);
                itemListener.cardClicked(v, teams.get(getLayoutPosition()), true);
            }

            else {
                if(tempCardView != null) {
                    tempCardView.setCardBackgroundColor(Color.WHITE);
                    tempCardView = null;
                }
                tempCardView = cardView;
                cardView.setCardBackgroundColor(Color.LTGRAY);
                itemListener.cardClicked(v, teams.get(getLayoutPosition()), false);
            }

        }
    }
}
