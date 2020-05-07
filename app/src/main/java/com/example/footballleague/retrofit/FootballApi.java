package com.example.footballleague.retrofit;

import com.example.footballleague.model.League;
import com.example.footballleague.model.Team;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface FootballApi {
    public static final String BASE_URL="https://api.football-data.org/";
    public static final String TOKEN="e4f062e20408495287b3946cb4af3b56";

    @GET("v2/competitions/PL/teams")
    Call<League> getLeague(@Header("X-Auth-Token") String token);
    @GET("v2/teams/{id}")
    Call<Team>getTeam(@Header("X-Auth-Token") String token,@Path("id") int id );


}
