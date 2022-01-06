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
    @Headers({"x-rapidapi-host:api-nba-v1.p.rapidapi.com", "x-rapidapi-key:5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424"})
    @GET("players/teamId/{teamId}/")
    Call<PlayerModel> getTeamsOfPlayers(@Path("teamId") String teamId);

    @Headers({"x-rapidapi-host:api-nba-v1.p.rapidapi.com", "x-rapidapi-key:5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424"})
    @GET("players/playerId/{playerId}/")
    Call<PlayerModel> getPlayer(@Path("playerId") String playerId);

    @Headers({"x-rapidapi-host:api-nba-v1.p.rapidapi.com", "x-rapidapi-key:5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424"})
    @GET("players/league/standard/")
    Call<PlayerModel> getAllPlayer();

    @Headers({"x-rapidapi-host:api-nba-v1.p.rapidapi.com", "x-rapidapi-key:5dbbf11d63msh76c8d4afa6cd3c7p16cdfejsn136e72230424"})
    @GET("statistics/players/playerId/{playerId}")
    Call<PlayerStatsModel> getPlayerStats(@Path("playerId") String playerId);
}
