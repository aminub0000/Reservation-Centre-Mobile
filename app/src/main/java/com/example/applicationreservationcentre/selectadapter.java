package com.example.applicationreservationcentre;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class selectadapter extends RecyclerView.Adapter<selectadapter.Viewholder>  {
    final ArrayList<offre> list ;
    OnpikerListiner onpikerListiner;
    public selectadapter(ArrayList<offre> list , OnpikerListiner onpikerListiner){
        this.list = list;
        this.onpikerListiner =onpikerListiner;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View v = inflate.inflate(R.layout.pick , parent,false);
        return new Viewholder(v , onpikerListiner);
    }
    int index;
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.img.setImageResource(list.get(position).getImage());
        holder.offre.setText(list.get(position).getOffre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onpikerListiner.onClickpiker(position);
                index = position;
                notifyDataSetChanged();
            }
        });
        if(position == index){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#6a62b7"));
            holder.offre.setTextColor(Color.WHITE);
            if(position == 0)holder.img.setImageResource(R.drawable.white_equip);
            else if(position == 1)holder.img.setImageResource(R.drawable.white_wifi);
            else if(position == 2)holder.img.setImageResource(R.drawable.white_datashow);
        }
        else{
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
            holder.offre.setTextColor(Color.parseColor("#6a62b7"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView img ;
        TextView offre ;
        OnpikerListiner listiner;
        CardView cardView;
        public Viewholder(@NonNull View itemView , OnpikerListiner listiner) {
            super(itemView);
            offre=itemView.findViewById(R.id.offre);
            img=itemView.findViewById(R.id.img);
            cardView=itemView.findViewById(R.id.cardView1);
            this.listiner =listiner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listiner.onClickpiker(getAdapterPosition());
        }
    }
    public interface OnpikerListiner{
        void onClickpiker(int position);
    }
}
