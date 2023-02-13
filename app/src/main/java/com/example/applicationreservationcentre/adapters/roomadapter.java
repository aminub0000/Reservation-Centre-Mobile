package com.example.applicationreservationcentre.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.models.equipement_model;

import java.util.ArrayList;

public class roomadapter extends RecyclerView.Adapter<roomadapter.viewholder> {
        ArrayList<equipement_model>li = new ArrayList<>();
    public roomadapter(ArrayList<equipement_model> ar) {
        li= (ArrayList<equipement_model>) ar.clone();
    }


    @NonNull
    @Override
    public roomadapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.roomitem, parent , false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull roomadapter.viewholder holder, int position) {
        holder.txt.setText(""+(position+1));
        holder.chairs.setText("+"+li.get(position).getChairs());
        holder.tables.setText(""+li.get(position).getTables());

        if(li.get(position).isWifi())holder.wifi_validation.setImageResource(R.drawable.verification_item_equipement_oui);
        else holder.wifi_validation.setImageResource(R.drawable.verification_item_equipement_non);

        if(li.get(position).isDatashow())holder.datashow_validation.setImageResource(R.drawable.verification_item_equipement_oui);
        else holder.datashow_validation.setImageResource(R.drawable.verification_item_equipement_non);

    }
    @Override
    public int getItemCount() {
        return li.size();
    }
    public class  viewholder extends RecyclerView.ViewHolder{
        TextView txt,chairs,tables;
        ImageView wifi_validation;
        ImageView datashow_validation;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.textView4);
            chairs = itemView.findViewById(R.id.chairs);
            tables = itemView.findViewById(R.id.tables);
            wifi_validation = itemView.findViewById(R.id.wifi_validation);
            datashow_validation = itemView.findViewById(R.id.datashow_validation);
        }
    }
}
