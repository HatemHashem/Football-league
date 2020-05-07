package com.example.footballleague.database;

import android.app.Application;

import com.example.footballleague.model.Team;

import java.util.List;

import androidx.lifecycle.LiveData;

import static com.example.footballleague.database.TeamDatabase.databaseWriterSERVICE;

public class SquadRepository {
    private LiveData<List<Team>> allTeamDetails;
    private SquadDao squadDao;

    public SquadRepository(Application application) {
        TeamDatabase db=TeamDatabase.getInstance(application);
        squadDao=db.squadDao();
        allTeamDetails=squadDao.getAllTeamDetails();
    }

    public LiveData<List<Team>> getAllTeamDetails() {
        return allTeamDetails;
    }
    public void insert(Team team){
        databaseWriterSERVICE.execute(() -> {
            squadDao.insert(team);
        });

    }
    public void deleteAll(){
        databaseWriterSERVICE.execute(() -> {
            squadDao.deleteAll();

        });
    }
}
