package com.example.footballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.footballleague.model.SquadItem;
import com.example.footballleague.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamDetailsActivity extends AppCompatActivity {
    private SquadViewModel squadViewModel;
    private TextView teamName;
    private ImageView teamLogo;
    private RecyclerView recyclerView;
    private static final String TAG = "TeamDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        teamName=findViewById(R.id.team_name_details);
        recyclerView=findViewById(R.id.recyclerview_details);
        teamLogo=findViewById(R.id.team_logo);
        Intent intent=getIntent();
        int id= intent.getIntExtra("ID",0);
        PassId.setId(id);
        squadViewModel= new ViewModelProvider(this).get(SquadViewModel.class);

        Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show();
        squadViewModel.getTeamLiveData().observe(this,team -> {
            System.out.println("test::::"+team.getName());
        });
        squadViewModel.getRoomSquad().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                Log.d(TAG, "onChanged:room items: "+teams);
                int index=-1;
                for(int i=0;i<teams.size();i++){
                    if(teams.get(i).getId()==id){
                        index=i;
                    }
                }
                if(index!=-1) {

                    teamName.setText(teams.get(index).getName());
                    setTitle(teams.get(index).getName());
                  List<SquadItem> squad= teams.get(index).getSquad();
               SquadAdapter adapter=new SquadAdapter(TeamDetailsActivity.this);

                    Log.d(TAG, "onChangedabc: "+teams.get(index).getCrestUrl());
              //  GlideApp.with(TeamDetailsActivity.this).load(teams.get(index).getCrestUrl()).into(teamLogo);
                   SvgLoader.pluck().with(TeamDetailsActivity.this)
                           .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                           .load(teams.get(index).getCrestUrl(),teamLogo);

                recyclerView.setLayoutManager(new LinearLayoutManager(TeamDetailsActivity.this));
                recyclerView.setAdapter(adapter);
                adapter.setSquad(squad);
                  DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(recyclerView.getContext(),
                          DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(dividerItemDecoration);
                }
            }
        });
    }
}
