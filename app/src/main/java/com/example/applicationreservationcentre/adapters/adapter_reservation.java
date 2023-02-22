package com.example.applicationreservationcentre.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.models.reservation;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter_reservation extends  RecyclerView.Adapter<adapter_reservation.ViewH>{
    ArrayList<reservation> list_res ;

    public adapter_reservation(ArrayList<reservation> list_res) {
        this.list_res = list_res;
    }

    @NonNull
    @Override
    public adapter_reservation.ViewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View v= inflate.inflate(R.layout.item_reservation , parent , false);
        return new adapter_reservation.ViewH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_reservation.ViewH holder, int position) {
        Picasso.get().load(list_res.get(position).getImage_centre()).into(holder.img);
        holder.nom_reservation.setText(list_res.get(position).getNom_reservation());
        holder.nom_centre.setText(list_res.get(position).getNom_centre());
        holder.date_reservation.setText(list_res.get(position).getDate_reservation());
        holder.reservation_heure_debut.setText(list_res.get(position).getReservation_heure_debut());
        holder.reservation_heure_fin.setText(list_res.get(position).getReservation_heure_fin());
        if(list_res.get(position).getResultat().equalsIgnoreCase("Accepté")){

            holder.resultat_reservation.setTextColor(Color.parseColor("#7BBD63"));
            holder.resultat_reservation.setText(""+list_res.get(position).getResultat());
        }

        else if(list_res.get(position).getResultat().equalsIgnoreCase("Refusé")){

            holder.resultat_reservation.setTextColor(Color.parseColor("#F12D2D"));
            holder.resultat_reservation.setText(""+list_res.get(position).getResultat());

        }

        else{
            holder.resultat_reservation.setTextColor(Color.parseColor("#9D9D9D"));}
            holder.resultat_reservation.setText(""+list_res.get(position).getResultat());


    }

    @Override
    public int getItemCount() {
        return list_res.size();
    }

    public class ViewH extends RecyclerView.ViewHolder{
        ShapeableImageView img ;
        TextView nom_reservation ;
        TextView nom_centre ;
        TextView date_reservation ;
        TextView reservation_heure_debut ;
        TextView reservation_heure_fin ;
        TextView resultat_reservation ;
        public ViewH(@NonNull View itemView) {
            super(itemView);
            img =itemView.findViewById(R.id.img_centre);
            nom_reservation =itemView.findViewById(R.id.nom_reservation);
            nom_centre =itemView.findViewById(R.id.nom_centre);
            date_reservation =itemView.findViewById(R.id.date_reservation);
            reservation_heure_debut =itemView.findViewById(R.id.reservation_heure_debut);
            reservation_heure_fin =itemView.findViewById(R.id.reservation_heure_fin);
            resultat_reservation =itemView.findViewById(R.id.resultat_reservation);
        }
    }
}


