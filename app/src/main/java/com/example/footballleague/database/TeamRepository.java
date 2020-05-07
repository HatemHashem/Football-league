package com.example.footballleague.database;

import android.app.Application;

import com.example.footballleague.model.TeamsItem;

import java.util.List;

import androidx.lifecycle.LiveData;

import static com.example.footballleague.database.TeamDatabase.databaseWriterSERVICE;

public class TeamRepository {
    private TeamDao teamDao;
    private LiveData<List<TeamsItem>> allTeams;

    public TeamRepository(Application application) {
        TeamDatabase teamDatabase=TeamDatabase.getInstance(application);
        teamDao=teamDatabase.teamDao();
        allTeams=teamDao.getAllTeams();
    }

    public LiveData<List<TeamsItem>> getAllTeams() {
        return allTeams;
    }

    public void insert(TeamsItem teamsItem){
        databaseWriterSERVICE.execute(() -> {
            teamDao.insertTeam(teamsItem);

        });

    }
    public void deleteAll(){
        databaseWriterSERVICE.execute(() -> {
            teamDao.deleteAllTeams();
        });
    }
    public void insertAll(List<TeamsItem> teamsItems){
        databaseWriterSERVICE.execute(() -> {
            teamDao.insertTeams(teamsItems);
        });
    }
}
