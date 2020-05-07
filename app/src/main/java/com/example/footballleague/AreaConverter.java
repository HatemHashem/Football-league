package com.example.footballleague;

import com.example.footballleague.model.Area;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

public class AreaConverter {
    @TypeConverter
    public String fromArea(Area area){
        if(area==null){
            return (null);
        }
        Gson gson = new Gson();

        String json =gson.toJson(area);
        return json;

    }
    @TypeConverter
    public Area toArea(String areaString){
        if(areaString==null){
            return (null);
        }
        Gson gson=new Gson();
        Area area=gson.fromJson(areaString,Area.class);
        return area;


    }

}
