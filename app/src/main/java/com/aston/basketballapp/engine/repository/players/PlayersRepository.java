package com.aston.basketballapp.engine.repository.players;

import com.aston.basketballapp.engine.model.player.PlayerModelApi;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModelApi;

import retrofit2.Call;

//Player Repository to make calls to the API
public interface PlayersRepository {
    Call<PlayerModelApi> getPlayers(String teamId);
    Call<PlayerModelApi> getPlayer(String playerId);
    Call<PlayerModelApi> getAllPlayers();
    Call<PlayerStatsModelApi> getPlayerStats(String playerId);


}
