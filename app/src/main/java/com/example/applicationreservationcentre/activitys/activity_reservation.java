package com.example.applicationreservationcentre.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.models.compoment_;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

public class activity_reservation extends AppCompatActivity {

    ShapeableImageView shapeableImageView;
    CalendarView calendar;
    FirebaseFirestore firestore;
    String date_picker;
    View view_8;
    View view_9;
    View view_10;
    View view_11;
    View view_12;
    View view_14;
    View view_15;
    View view_16;
    View view_17;
    View view_18;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        firestore =FirebaseFirestore.getInstance();
        calendar =findViewById(R.id.calendar);

        view_8 =findViewById(R.id.view_8);
        view_9 =findViewById(R.id.view_9);
        view_10 =findViewById(R.id.view_10);
        view_11 =findViewById(R.id.view_11);
        view_12 =findViewById(R.id.view_12);
        view_14 =findViewById(R.id.view_14);
        view_15 =findViewById(R.id.view_15);
        view_16 =findViewById(R.id.view_16);
        view_17 =findViewById(R.id.view_17);
        view_18 =findViewById(R.id.view_18);

        getSupportActionBar().hide();
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date_picker = dayOfMonth+"-"+(month+1)+"-"+year;
                firestore.collection("Centres").document(""+getIntent().getStringExtra("nom"))
                        .collection("reservation").document(""+year)
                        .collection("Dates").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for(DocumentSnapshot documentSnapshot :queryDocumentSnapshots){
                                    if(documentSnapshot.getId().equalsIgnoreCase(""+date_picker)){
                                        if(documentSnapshot.getBoolean("8"))view_8.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_8.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        if(documentSnapshot.getBoolean("9"))view_9.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_9.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        if(documentSnapshot.getBoolean("10"))view_10.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_10.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        if(documentSnapshot.getBoolean("11"))view_11.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_11.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        if(documentSnapshot.getBoolean("12"))view_12.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_12.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        if(documentSnapshot.getBoolean("14"))view_14.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_14.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        if(documentSnapshot.getBoolean("15"))view_15.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_15.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        if(documentSnapshot.getBoolean("16"))view_16.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_16.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        if(documentSnapshot.getBoolean("17"))view_17.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_17.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        if(documentSnapshot.getBoolean("18"))view_18.setBackgroundResource(R.drawable.timer_view_shape_true);
                                        else view_18.setBackgroundResource(R.drawable.timer_view_chape_false);
                                        return;
                                    }
                                }
                                view_8.setBackgroundResource(R.drawable.timer_view_chape_false);
                                view_9.setBackgroundResource(R.drawable.timer_view_chape_false);
                                view_10.setBackgroundResource(R.drawable.timer_view_chape_false);
                                view_11.setBackgroundResource(R.drawable.timer_view_chape_false);
                                view_12.setBackgroundResource(R.drawable.timer_view_chape_false);
                                view_14.setBackgroundResource(R.drawable.timer_view_chape_false);
                                view_15.setBackgroundResource(R.drawable.timer_view_chape_false);
                                view_16.setBackgroundResource(R.drawable.timer_view_chape_false);
                                view_17.setBackgroundResource(R.drawable.timer_view_chape_false);
                                view_18.setBackgroundResource(R.drawable.timer_view_chape_false);
                            }
                        });
            }
        });
    }

    public void affecte_changed(String height , String nine,String tent , String eleven, String twelve,
                                String fourteen ,String fifteen ,String sixteen ,String seventeen ,String eighteen){

    }
}