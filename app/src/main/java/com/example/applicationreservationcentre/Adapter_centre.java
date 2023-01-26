package com.example.applicationreservationcentre;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        holder.img_centre.setImageResource(list_centre.get(position).image_centre);
        holder.text_name.setText(list_centre.get(position).centre_name);
        holder.map_texte.setText(list_centre.get(position).centre_maptext);
        if(!list_centre.get(position).wifi){
            holder.wifi.setImageResource(R.drawable.purple_datashow);
            holder.more.setVisibility(View.INVISIBLE);
            if(list_centre.get(position).datashow){
                holder.datashow.setImageResource(R.drawable.purple_add);
                holder.datashow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog mydialog = new Dialog(view.getContext());
                        mydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        mydialog.setContentView(R.layout.equipement);
                        mydialog.setTitle("Equipement");
                        //mydialog.setCanceledOnTouchOutside(false);
                        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        TextView txt_class =mydialog.findViewById(R.id.txt_class);
                        TextView txt_chair =mydialog.findViewById(R.id.txt_chair);
                        ImageView img_wifi =mydialog.findViewById(R.id.img_wifi);
                        img_wifi.setImageResource(R.drawable.no_white_wifi);
                        txt_class.setText("Classroom : "+String.valueOf(list_centre.get(position).nb_classroom));
                        txt_chair.setText(String.valueOf(list_centre.get(position).hair+"+"));
                        mydialog.show();
                    }
                });
            }
            else{
                holder.datashow.setVisibility(View.INVISIBLE);
                holder.wifi.setImageResource(R.drawable.purple_add);
                holder.wifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog mydialog = new Dialog(view.getContext());
                        mydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        mydialog.setContentView(R.layout.equipement);
                        mydialog.setTitle("Equipement");
                        //mydialog.setCanceledOnTouchOutside(false);
                        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        TextView txt_class =mydialog.findViewById(R.id.txt_class);
                        TextView txt_chair =mydialog.findViewById(R.id.txt_chair);
                        ImageView img_wifi =mydialog.findViewById(R.id.img_wifi);
                        ImageView img_datashow =mydialog.findViewById(R.id.img_datashow);
                        img_wifi.setImageResource(R.drawable.no_white_wifi);
                        img_datashow.setImageResource(R.drawable.no_white_datashow);
                        txt_class.setText("Classroom : "+String.valueOf(list_centre.get(position).nb_classroom));
                        txt_chair.setText(String.valueOf(list_centre.get(position).hair+"+"));

                        mydialog.show();
                    }
                });
            }
        }
        else{
            if(list_centre.get(position).datashow){
                holder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog mydialog = new Dialog(view.getContext());
                        mydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        mydialog.setContentView(R.layout.equipement);
                        //mydialog.setCanceledOnTouchOutside(false);
                        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        TextView txt_class =mydialog.findViewById(R.id.txt_class);
                        TextView txt_chair =mydialog.findViewById(R.id.txt_chair);
                        txt_class.setText("Classroom : "+String.valueOf(list_centre.get(position).nb_classroom));
                        txt_chair.setText(String.valueOf(list_centre.get(position).hair+"+"));

                        mydialog.show();
                    }
                });
            }
            else{
                holder.more.setVisibility(View.INVISIBLE);
                holder.datashow.setImageResource(R.drawable.purple_add);
                holder.datashow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog mydialog = new Dialog(view.getContext());
                        mydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        mydialog.setContentView(R.layout.equipement);
                        mydialog.setTitle("Equipement");
                        //mydialog.setCanceledOnTouchOutside(false);
                        mydialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        TextView txt_wifi =mydialog.findViewById(R.id.txt_wifi);
                        TextView txt_datashow =mydialog.findViewById(R.id.txt_datashow);
                        TextView txt_class =mydialog.findViewById(R.id.txt_class);
                        TextView txt_chair =mydialog.findViewById(R.id.txt_chair);
                        ImageView img_wifi =mydialog.findViewById(R.id.img_wifi);
                        ImageView img_datashow =mydialog.findViewById(R.id.img_datashow);
                        ImageView img_class =mydialog.findViewById(R.id.img_class);
                        ImageView img_chair =mydialog.findViewById(R.id.img_chair);
                        img_datashow.setImageResource(R.drawable.no_white_datashow);
                        txt_class.setText("Classroom : "+String.valueOf(list_centre.get(position).nb_classroom));
                        txt_chair.setText(String.valueOf(list_centre.get(position).hair+"+"));
                        mydialog.show();
                    }
                });
            }
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

            //TextView
            text_name =itemView.findViewById(R.id.texte_name);
            map_texte =itemView.findViewById(R.id.map_texte);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item_centre.centre_onclick_(getAdapterPosition(),img_centre ,text_name,map_texte,icon_map);
                }
            });
        }
    }
}














