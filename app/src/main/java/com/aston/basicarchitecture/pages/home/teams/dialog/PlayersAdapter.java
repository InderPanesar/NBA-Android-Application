package com.aston.basicarchitecture.pages.home.teams.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;
import com.aston.basicarchitecture.R;
import java.util.ArrayList;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.MyViewHolder> {
    ArrayList<IndividualPlayerModel> players;
    Context context;



    public PlayersAdapter(Context ct, ArrayList<IndividualPlayerModel> _players) {
        context = ct;
        players = _players;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.players_icon_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.teamCity.setText(teams.get(position).getCity());
        //holder.teamName.setText(teams.get(position).getNickname());
        //Picasso.get().load(teams.get(position).getLogo()).into(holder.logo);

        if(!players.isEmpty()) {
            holder.playerJersey.setText(players.get(position).getLeagues().getNBADetails().getJersey());
            holder.playerName.setText(new StringBuilder().append(players.get(position).getFirstName()).append(" ").append(players.get(position).getLastName()).toString());
            holder.playerDescriptors.setText(new StringBuilder().append("Height: ").append(players.get(position).getHeightInMetres()).append("m ").append("Weight: ").append(players.get(position).getWeightInKilometers()).append("kg").toString());
            holder.playerPosition.setText(players.get(position).getLeagues().getNBADetails().getPos());
        }


    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setPlayers(ArrayList<IndividualPlayerModel> teams) {
        this.players = teams;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //ToDo: Implement Team Logos.

        TextView playerJersey, playerName, playerDescriptors, playerPosition;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerJersey = itemView.findViewById(R.id.cardPlayersJerseyNumber);
            playerName = itemView.findViewById(R.id.cardPlayersName);
            playerDescriptors = itemView.findViewById(R.id.cardPlayersHeightAndWeight);
            playerPosition = itemView.findViewById(R.id.cardPlayersPosition);

        }
    }
}