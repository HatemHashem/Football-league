package com.example.footballleague;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.footballleague.model.TeamsItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {
    private List<TeamsItem> squads;
    private Context context;
    private static final String TAG = "TeamsAdapter";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.team_item,parent,false));

    }

    public void setSquads(List<TeamsItem> squads) {
        this.squads = squads;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TeamsItem currentSquad=squads.get(position);
        holder.bindTo(currentSquad);


    }

    @Override
    public int getItemCount() {
        return squads.size();
    }

    public TeamsAdapter(Context context) {
        this.context=context;
        squads=new ArrayList<>();
    }
    class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        private TextView teamName;
        private TextView teamWebsite;
        private TextView teamColor;
        private TextView teamVenue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName=itemView.findViewById(R.id.team_name);
            teamWebsite=itemView.findViewById(R.id.team_website);
            teamColor=itemView.findViewById(R.id.team_color);
            teamVenue=itemView.findViewById(R.id.team_venue);
            itemView.setOnClickListener(this);
        }
        public void bindTo(TeamsItem currentTeamsItem){
            teamName.setText(currentTeamsItem.getName());
            teamWebsite.setText(currentTeamsItem.getWebsite());
            teamColor.setText(currentTeamsItem.getClubColors());
            teamVenue.setText(currentTeamsItem.getVenue());
            Log.d(TAG, "bindTo: "+"wtf");
        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context,TeamDetailsActivity.class);
            TeamsItem currentTeamItem=squads.get(getAdapterPosition());
            intent.putExtra("ID",currentTeamItem.getId());
            context.startActivity(intent);

        }
    }
}
