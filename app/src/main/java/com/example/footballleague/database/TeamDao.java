package com.example.footballleague.database;

import com.example.footballleague.model.TeamsItem;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
@Dao
public interface TeamDao {
    @Insert
     void insertTeam(TeamsItem teamsItem);
    @Query("SELECT * FROM team_table ORDER BY name DESC")
     LiveData<List<TeamsItem>>getAllTeams();
    @Query("DELETE FROM TEAM_TABLE")
    void deleteAllTeams();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeams(List<TeamsItem> teamsItems);
}
