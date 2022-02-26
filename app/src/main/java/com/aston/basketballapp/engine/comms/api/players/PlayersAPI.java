package com.aston.basketballapp.engine.comms.api.players;

import com.aston.basketballapp.engine.model.player.PlayerModelApi;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModelApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//Contains all the Api methods to make calls to get player specific information
public interface PlayersAPI {
    //Gets a list of players for a specific team
    @GET("players")
    Call<PlayerModelApi> getTeamsOfPlayers(@Query("season") String season, @Query("team") String teamId);

    //Gets Information for a specific player
    @GET("players")
    Call<PlayerModelApi> getPlayer(@Query("id") String playerId);

    //Get PlayerStats for a specific player.
    @GET("players/statistics")
    Call<PlayerStatsModelApi> getPlayerStats(@Query("id") String id, @Query("season") String playerId);
}
