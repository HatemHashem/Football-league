package com.example.footballleague.database;

import com.example.footballleague.model.Team;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SquadDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Team team);

    @Query("DELETE FROM team_details")
    void deleteAll();

    @Query("SELECT * FROM team_details ")
    LiveData<List<Team>> getAllTeamDetails();

}
