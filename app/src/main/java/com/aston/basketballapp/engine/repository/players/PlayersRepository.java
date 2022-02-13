package com.aston.basketballapp.engine.repository.players;

import com.aston.basketballapp.engine.model.player.PlayerModel;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModel;

import retrofit2.Call;

//Player Repository to make calls to the API
public interface PlayersRepository {
    Call<PlayerModel> getPlayers(String teamId);
    Call<PlayerModel> getPlayer(String playerId);
    Call<PlayerModel> getAllPlayers();
    Call<PlayerStatsModel> getPlayerStats(String playerId);


}
