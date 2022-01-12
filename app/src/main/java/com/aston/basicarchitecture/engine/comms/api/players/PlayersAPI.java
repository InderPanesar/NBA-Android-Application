package com.aston.basicarchitecture.engine.comms.api.players;

import com.aston.basicarchitecture.engine.model.player.PlayerModel;
import com.aston.basicarchitecture.engine.model.player.stats.PlayerStatistics;
import com.aston.basicarchitecture.engine.model.player.stats.PlayerStatsModel;
import com.aston.basicarchitecture.engine.model.player.stats.PlayerStatsModelApi;
import com.aston.basicarchitecture.engine.model.teams.TeamsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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
