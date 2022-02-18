package com.aston.basketballapp.pages.home.teams.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aston.basketballapp.engine.model.player.IndividualPlayerModel;
import com.aston.basketballapp.R;
import java.util.ArrayList;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder> {
    ArrayList<IndividualPlayerModel> players;
    Context context;



    public PlayersAdapter(Context ct, ArrayList<IndividualPlayerModel> _players) {
        context = ct;
        players = _players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.players_icon_item, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        if(!players.isEmpty()) {
            holder.playerJersey.setText(players.get(position).getLeagues().getNBADetails().getJersey());
            holder.playerName.setText(getName(position));
            holder.playerDescriptors.setText(getPlayerDescriptors(position));
            holder.playerPosition.setText(players.get(position).getLeagues().getNBADetails().getPos());
        }
    }

    public String getName(int position) {
        return players.get(position).getFirstName() + " " + players.get(position).getLastName();
    }

    public String getPlayerDescriptors(int position) {
        return "Height: " + players.get(position).getHeightInMetres() + "m " + "Weight: " + players.get(position).getWeightInKilograms();
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setPlayers(ArrayList<IndividualPlayerModel> teams) {
        this.players = teams;
        notifyDataSetChanged();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView playerJersey, playerName, playerDescriptors, playerPosition;
        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            playerJersey = itemView.findViewById(R.id.card_players_jersey_number);
            playerName = itemView.findViewById(R.id.card_players_name);
            playerDescriptors = itemView.findViewById(R.id.card_players_height_and_weight);
            playerPosition = itemView.findViewById(R.id.card_players_position);

        }
    }
}