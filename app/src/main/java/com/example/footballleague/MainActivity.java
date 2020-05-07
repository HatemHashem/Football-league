package com.example.footballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.footballleague.model.TeamsItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SquadViewModel squadViewModel;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        TeamsAdapter adapter=new TeamsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        squadViewModel=new ViewModelProvider(this).get(SquadViewModel.class);

        squadViewModel.getSquadLiveData().observe(this,squad ->{
            if(squad!=null){
                List<TeamsItem> squads=squad.getTeams();
               // adapter.setSquads(squads);


            }
        } );
        squadViewModel.getRoomTeams().observe(this, new Observer<List<TeamsItem>>() {
            @Override
            public void onChanged(List<TeamsItem> teamsItems) {
                squadViewModel.getSquadLiveData().getValue();
               adapter.setSquads(teamsItems);
                Log.d(TAG, "room: "+teamsItems);
            }
        });



    }
}
