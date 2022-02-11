package com.aston.basketballapp.engine.comms.api.players;

import com.aston.basketballapp.engine.model.player.PlayerModel;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlayersAPI {
    @GET("players/teamId/{teamId}/")
    Call<PlayerModel> getTeamsOfPlayers(@Path("teamId") String teamId);

    @GET("players/playerId/{playerId}/")
    Call<PlayerModel> getPlayer(@Path("playerId") String playerId);

    @GET("players/league/standard/")
    Call<PlayerModel> getAllPlayer();

    @GET("statistics/players/playerId/{playerId}")
    Call<PlayerStatsModel> getPlayerStats(@Path("playerId") String playerId);
}
