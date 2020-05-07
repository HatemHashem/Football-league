package com.example.footballleague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.footballleague.model.SquadItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SquadAdapter extends RecyclerView.Adapter<SquadAdapter.ViewHolder> {
    private List<SquadItem>squad;
    private Context context;

    public SquadAdapter(Context context) {
       this.context=context;
    }
    public void setSquad(List<SquadItem>squad){
        this.squad=squad;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.squad_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SquadItem currentSquadItem=squad.get(position);
        holder.bindTo(currentSquadItem);


    }

    @Override
    public int getItemCount() {
        return squad.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView role;
        private TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            role=itemView.findViewById(R.id.player_role);
            name=itemView.findViewById(R.id.player_name);
        }
        public void bindTo( SquadItem currentSquad){
            name.setText(currentSquad.getName());
            if(currentSquad.getPosition()==null){
                role.setText(currentSquad.getRole());
            }else {
                role.setText(currentSquad.getPosition());
            }

        }


    }
}
