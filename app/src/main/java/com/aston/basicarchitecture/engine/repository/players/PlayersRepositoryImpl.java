package com.aston.basicarchitecture.engine.repository.players;

import com.aston.basicarchitecture.engine.comms.api.players.PlayersAPI;
import com.aston.basicarchitecture.engine.comms.api.teams.TeamsAPI;
import com.aston.basicarchitecture.engine.model.player.PlayerModel;

import retrofit2.Call;
import retrofit2.Retrofit;

public class PlayersRepositoryImpl implements PlayersRepository{

    Retrofit retrofit;

    public PlayersRepositoryImpl(Retrofit _retrofit) {
        retrofit = _retrofit;
    }

    @Override
    public Call<PlayerModel> getPlayers(String teamId) {
        PlayersAPI api = retrofit.create(PlayersAPI.class);
        return api.getTeamsOfPlayers(teamId);
    }

    @Override
    public Call<PlayerModel> getPlayer(String playerId) {
        PlayersAPI api = retrofit.create(PlayersAPI.class);
        return api.getPlayer(playerId);
    }


}
