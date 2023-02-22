package com.example.applicationreservationcentre.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.models.centre_item;
import com.example.applicationreservationcentre.models.centre_info;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_centre extends RecyclerView.Adapter<Adapter_centre.ViewHolder>{

    ArrayList<centre_info> list_centre;
    centre_item item_centre;
    public Adapter_centre(ArrayList<centre_info> list_centre, centre_item item_centre) {
        this.list_centre = list_centre;
        this.item_centre =item_centre;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View v = inflate.inflate(R.layout.centreitem   , parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get().load(list_centre.get(position).getImage_centre(0)).into(holder.img_centre);

        holder.text_name.setText(list_centre.get(position).getCentre_name());
        holder.map_texte.setText(list_centre.get(position).getCentre_maptext());
        if(!list_centre.get(position).isWifi()){
            holder.wifi.setImageResource(R.drawable.no_purple_wifi);
        }
        if(!list_centre.get(position).isDatashow()){
            holder.datashow.setImageResource(R.drawable.np_purple_datashow);
        }

    }
    @Override
    public int getItemCount() {
        return list_centre.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_centre;
        ImageView wifi;
        ImageView datashow;
        ImageView more;
        ImageView info_flaiche;
        ImageView icon_map;

        TextView text_name;
        TextView map_texte;
        TextView centre_tele;
        TextView email_centre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //imageView
            img_centre =itemView.findViewById(R.id.img_centre);
            wifi =itemView.findViewById(R.id.wifi);
            datashow =itemView.findViewById(R.id.datashow);
            datashow =itemView.findViewById(R.id.datashow);
            more =itemView.findViewById(R.id.more);
            info_flaiche =itemView.findViewById(R.id.info_flaiche);
            icon_map =itemView.findViewById(R.id.icon_map);
            icon_map =itemView.findViewById(R.id.icon_map);
            icon_map =itemView.findViewById(R.id.icon_map);
            centre_tele =itemView.findViewById(R.id.centre_tele);
            email_centre =itemView.findViewById(R.id.email_centre);

            //TextView
            text_name =itemView.findViewById(R.id.texte_name);
            map_texte =itemView.findViewById(R.id.map_texte);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item_centre.centre_onclick_(getAdapterPosition(),
                            img_centre ,
                            text_name,
                            map_texte,
                            icon_map,
                            list_centre.get(getAdapterPosition()).getImage_centre(0));
                }
            });
        }
    }
}














