package com.example.footballleague;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.footballleague.database.SquadRepository;
import com.example.footballleague.database.TeamRepository;
import com.example.footballleague.model.League;
import com.example.footballleague.model.Team;
import com.example.footballleague.retrofit.FootballApi;
import com.example.footballleague.retrofit.FootballClient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private FootballApi footballApi;
    Application application;
    private static final String TAG = "Repository";

    public Repository(Application application) {
        footballApi= FootballClient.getRetrofitInstance().create(FootballApi.class);
        this.application=application;
    }
    public LiveData<League> getSquad(){
        MutableLiveData<League> data=new MutableLiveData<>();
        footballApi.getLeague(FootballApi.TOKEN).enqueue(new Callback<League>() {
            @Override
            public void onResponse(Call<League> call, Response<League> response) {
                data.setValue(response.body());
                TeamRepository teamRepository=new TeamRepository(application);
                teamRepository.insertAll(response.body().getTeams());
                Log.d(TAG, "onResponse1: "+response.body());

            }

            @Override
            public void onFailure(Call<League> call, Throwable t) {
                data.setValue(null);
                Log.e(TAG, "onFailure: "+t.getMessage() );
                Toast.makeText(application.getApplicationContext(),"no network",Toast.LENGTH_SHORT).show();

            }
        });
        return data;

    }
 public LiveData<Team> getTeam(int id){
        MutableLiveData<Team>data=new MutableLiveData<>();
        footballApi.getTeam(FootballApi.TOKEN,id).enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                Log.d(TAG, "onResponse: "+response.body());
                if(response.isSuccessful()==true) {
                    SquadRepository squadRepository = new SquadRepository(application);
                    data.setValue(response.body());
                    squadRepository.insert(response.body());
                }else {
                    Toast.makeText(application.getApplicationContext(),"no internet connection",Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());

            }
        });

        return data;
 }
}
