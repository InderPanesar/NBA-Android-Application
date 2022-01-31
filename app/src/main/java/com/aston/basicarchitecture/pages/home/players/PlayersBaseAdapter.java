package com.aston.basicarchitecture.pages.home.players;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.aston.basicarchitecture.engine.model.player.IndividualPlayerModel;
import com.aston.basicarchitecture.R;
import com.aston.basicarchitecture.pages.home.teams.TeamsCardClicked;

import java.util.ArrayList;

public class PlayersBaseAdapter extends RecyclerView.Adapter<PlayersBaseAdapter.MyViewHolder> {
    private static PlayersCardClicked itemListener;

    ArrayList<IndividualPlayerModel> players;
    Context context;



    public PlayersBaseAdapter(Context ct, ArrayList<IndividualPlayerModel> _players, PlayersCardClicked _itemListener) {
        context = ct;
        players = _players;
        itemListener = _itemListener;
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //ToDo: Implement Team Logos.

        TextView playerJersey, playerName, playerDescriptors, playerPosition;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerJersey = itemView.findViewById(R.id.cardPlayersJerseyNumber);
            playerName = itemView.findViewById(R.id.cardPlayersName);
            playerDescriptors = itemView.findViewById(R.id.cardPlayersHeightAndWeight);
            playerPosition = itemView.findViewById(R.id.cardPlayersPosition);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemListener.cardClicked(v, players.get(getLayoutPosition()));
        }
    }
}