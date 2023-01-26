package com.example.applicationreservationcentre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity2 extends AppCompatActivity {

    TextView name;
    TextView map;
    TextView reservation;
    TextView des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();
        name =findViewById(R.id.name);
        map =findViewById(R.id.map);
        des =findViewById(R.id.des);
        reservation =findViewById(R.id.reservation);
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        name.setText(getIntent().getStringExtra("name_centre"));
        map.setText(getIntent().getStringExtra("map_centre"));
        des.setText(getIntent().getStringExtra("des"));
        int[] images = new int[3];
        images[0]= Integer.parseInt(getIntent().getStringExtra("img_centre"));
        if(getIntent().getStringExtra("name_centre").equalsIgnoreCase("Centre Paice")){
            images[1]= R.drawable.c1;
            images[2]= R.drawable.c1;
        }
        else if(getIntent().getStringExtra("name_centre").equalsIgnoreCase("Centre d'etude et formation")){
            images[1]= R.drawable.c1;
            images[2]= R.drawable.c1;
        }
        else if(getIntent().getStringExtra("name_centre").equalsIgnoreCase("Centre massira")){
            images[1]= R.drawable.c1;
            images[2]= R.drawable.c1;
        }
        ViewPager2 v = findViewById(R.id.imgcentre);
        CircleIndicator3 indicator3 = findViewById(R.id.circle);
        ArrayList<Integer> li =new ArrayList<>();
        li.add(images[0]);
        li.add(images[1]);
        li.add(images[2]);
        adapter_img adapter_img =new adapter_img(li);
        v.setAdapter(adapter_img);
        v.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        indicator3.setViewPager(v);
        indicator3.bringToFront();
    }
}