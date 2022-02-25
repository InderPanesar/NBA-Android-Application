package com.aston.basketballapp.engine.repository.players;

import com.aston.basketballapp.engine.comms.api.players.PlayersAPI;
import com.aston.basketballapp.engine.model.player.PlayerModelApi;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModelApi;

import retrofit2.Call;
import retrofit2.Retrofit;

//Definition to make calls to get PlayersAPI
public class PlayersRepositoryImpl implements PlayersRepository{

    Retrofit retrofit;

    public PlayersRepositoryImpl(Retrofit _retrofit) {
        retrofit = _retrofit;
    }

    @Override
    public Call<PlayerModelApi> getPlayers(String teamId) {
        PlayersAPI api = retrofit.create(PlayersAPI.class);
        return api.getTeamsOfPlayers("2021", teamId);
    }

    @Override
    public Call<PlayerModelApi> getPlayer(String playerId) {
        PlayersAPI api = retrofit.create(PlayersAPI.class);
        return api.getPlayer(playerId);
    }

    @Override
    public Call<PlayerModelApi> getAllPlayers() {
        PlayersAPI api = retrofit.create(PlayersAPI.class);
        return api.getTeamsOfPlayers("2021", "1");
    }

    @Override
    public Call<PlayerStatsModelApi> getPlayerStats(String playerId) {
        PlayersAPI api = retrofit.create(PlayersAPI.class);
        return api.getPlayerStats(playerId, "2021");
    }


}
