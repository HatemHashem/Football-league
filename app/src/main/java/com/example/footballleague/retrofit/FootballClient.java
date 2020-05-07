package com.example.footballleague.retrofit;

import com.example.footballleague.retrofit.FootballApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FootballClient {
    private static  Retrofit retrofit=null;

    public static Retrofit getRetrofitInstance() {
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(FootballApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
