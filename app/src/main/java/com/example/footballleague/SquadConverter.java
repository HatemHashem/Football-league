package com.example.footballleague;

import com.example.footballleague.model.SquadItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class SquadConverter {
    @TypeConverter
    public String fromSquad(List<SquadItem> squad){
        Gson gson=new Gson();
       String json= gson.toJson(squad);

        return json;
    }
    @TypeConverter
    public List<SquadItem> toSquad(String squadString){
        Gson gson=new Gson();
        Type squadListType=new TypeToken<List<SquadItem>>(){}.getType();
        List<SquadItem> squadMembers=gson.fromJson(squadString,squadListType);
        return squadMembers;
    }

}
