package com.example.footballleague;

import android.app.Application;

import com.example.footballleague.database.SquadRepository;
import com.example.footballleague.database.TeamRepository;
import com.example.footballleague.model.League;
import com.example.footballleague.model.Team;
import com.example.footballleague.model.TeamsItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SquadViewModel extends AndroidViewModel {
    private static final String TAG = "SquadViewModel";
    private Repository repository;
    private TeamRepository roomRepo;
    private SquadRepository squadRepository;
    private LiveData<League> squadLiveData;
    private LiveData<Team> teamLiveData;
    private LiveData<List<TeamsItem>> roomTeams;
    private LiveData<List<Team>> roomSquad;


    public LiveData<League> getSquadLiveData() {
        return squadLiveData;
    }

    public LiveData<List<Team>> getRoomSquad() {
        return roomSquad;
    }

    public SquadViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        roomRepo = new TeamRepository(application);
        squadRepository = new SquadRepository(application);
        squadLiveData = repository.getSquad();
        teamLiveData = repository.getTeam(PassId.getId());

        roomTeams = roomRepo.getAllTeams();
        roomSquad=squadRepository.getAllTeamDetails();

    }

    public LiveData<Team> getTeamLiveData() {
        return teamLiveData;
    }

    public LiveData<List<TeamsItem>> getRoomTeams() {
        return roomTeams;
    }
}
