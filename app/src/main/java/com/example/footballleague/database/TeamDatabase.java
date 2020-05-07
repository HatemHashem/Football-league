package com.example.footballleague.database;

import android.content.Context;

import com.example.footballleague.AreaConverter;
import com.example.footballleague.SquadConverter;
import com.example.footballleague.model.Team;
import com.example.footballleague.model.TeamsItem;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities = {TeamsItem.class, Team.class},version = 1,exportSchema = false)
@TypeConverters({AreaConverter.class, SquadConverter.class})
public abstract class TeamDatabase extends RoomDatabase {
    private static TeamDatabase instance;
    private static final int NUMBER_OF_THREADS=4;
    public abstract TeamDao teamDao();
    public abstract SquadDao squadDao();
    static final ExecutorService databaseWriterSERVICE= Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static synchronized TeamDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,TeamDatabase.class,"league_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
