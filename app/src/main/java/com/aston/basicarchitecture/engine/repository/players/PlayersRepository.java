package com.aston.basicarchitecture.engine.repository.players;

import com.aston.basicarchitecture.engine.model.player.PlayerModel;

import retrofit2.Call;

public interface PlayersRepository {
    Call<PlayerModel> getPlayers(String teamId);
    Call<PlayerModel> getPlayer(String playerId);

}
