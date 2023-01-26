package com.example.applicationreservationcentre;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_img extends RecyclerView.Adapter<adapter_img.ViewH> {
    ArrayList<Integer> list_img = new ArrayList<>();

    public adapter_img(ArrayList<Integer> list_img) {
        this.list_img = list_img;
    }

    @NonNull
    @Override
    public ViewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View v= inflate.inflate(R.layout.img , parent , false);
        return new ViewH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewH holder, int position) {
        holder.img.setImageResource(list_img.get(position));
    }

    @Override
    public int getItemCount() {
        return list_img.size();
    }

    public class ViewH extends RecyclerView.ViewHolder{
        ImageView img ;
        public ViewH(@NonNull View itemView) {
            super(itemView);

            img =itemView.findViewById(R.id.imageView4);
        }
    }
}
