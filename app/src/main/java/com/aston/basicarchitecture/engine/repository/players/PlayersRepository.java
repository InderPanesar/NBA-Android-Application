package com.aston.basicarchitecture.engine.repository.players;

import com.aston.basicarchitecture.engine.model.player.PlayerModel;
import com.aston.basicarchitecture.engine.model.player.stats.PlayerStatsModel;
import com.aston.basicarchitecture.engine.model.player.stats.PlayerStatsModelApi;

import retrofit2.Call;

public interface PlayersRepository {
    Call<PlayerModel> getPlayers(String teamId);
    Call<PlayerModel> getPlayer(String playerId);
    Call<PlayerModel> getAllPlayers();
    Call<PlayerStatsModel> getPlayerStats(String playerId);


}
