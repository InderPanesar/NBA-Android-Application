package com.aston.basketballapp.engine.repository.players;

import com.aston.basketballapp.engine.comms.api.players.PlayersAPI;
import com.aston.basketballapp.engine.model.player.PlayerModel;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModel;

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

    @Override
    public Call<PlayerModel> getAllPlayers() {
        PlayersAPI api = retrofit.create(PlayersAPI.class);
        return api.getAllPlayer();
    }

    @Override
    public Call<PlayerStatsModel> getPlayerStats(String playerId) {
        PlayersAPI api = retrofit.create(PlayersAPI.class);
        return api.getPlayerStats(playerId);
    }


}
