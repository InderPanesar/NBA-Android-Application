package com.aston.basketballapp.pages.home.settings.favouriteTeam;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aston.basketballapp.R;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SettingsTeamsAdapter extends RecyclerView.Adapter<SettingsTeamsAdapter.SettingsTeamsAdapterViewHolder> {
    private static SettingsTeamClicked itemListener;
    ArrayList<TeamsRepo.LocalTeam> teams;
    Context context;
    int ItemSelected = -1;




    public SettingsTeamsAdapter(Context ct, ArrayList<TeamsRepo.LocalTeam> _teams, SettingsTeamClicked itemListener) {
        context = ct;
        teams = _teams;
        SettingsTeamsAdapter.itemListener = itemListener;
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).isSelected) {
                ItemSelected = i;
                break;
            }
        }
    }

    @NonNull
    @Override
    public SettingsTeamsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.teams_image_icon_item, parent, false);
        return new SettingsTeamsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsTeamsAdapterViewHolder holder, int position) {
        Picasso.get().load(teams.get(position).getLogoURL()).into(holder.logo);
        if(position == ItemSelected) {
            holder.cardView.setCardBackgroundColor(Color.LTGRAY);
        }
        else {
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class SettingsTeamsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView logo;
        MaterialCardView cardView;

        public SettingsTeamsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.teams_image_view);
            cardView = itemView.findViewById(R.id.teams_image_icon_item);
            itemView.setOnClickListener(this);

        }


        //If Item is clicked then ensure to pass the value for whether the value is being added or removed.
        @Override
        public void onClick(View v) {
            int _valueSelected = getAdapterPosition();
            if(_valueSelected == ItemSelected) {
                ItemSelected = -1;
                itemListener.cardClicked(teams.get(_valueSelected), true);
            }
            else {
                ItemSelected = getAdapterPosition();
                itemListener.cardClicked(teams.get(_valueSelected), false);
            }
            notifyDataSetChanged();

        }
    }
}
