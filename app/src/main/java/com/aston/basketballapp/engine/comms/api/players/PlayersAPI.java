package com.aston.basketballapp.engine.comms.api.players;

import com.aston.basketballapp.engine.model.player.PlayerModel;
import com.aston.basketballapp.engine.model.player.stats.PlayerStatsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Contains all the Api methods to make calls to get player specific information
public interface PlayersAPI {
    //Gets a list of players for a specific team
    @GET("players/teamId/{teamId}/")
    Call<PlayerModel> getTeamsOfPlayers(@Path("teamId") String teamId);

    //Gets Information for a specific player
    @GET("players/playerId/{playerId}/")
    Call<PlayerModel> getPlayer(@Path("playerId") String playerId);

    //Get all NBA players
    @GET("players/league/standard/")
    Call<PlayerModel> getAllPlayer();

    //Get PlayerStats for a specific player.
    @GET("statistics/players/playerId/{playerId}")
    Call<PlayerStatsModel> getPlayerStats(@Path("playerId") String playerId);
}
