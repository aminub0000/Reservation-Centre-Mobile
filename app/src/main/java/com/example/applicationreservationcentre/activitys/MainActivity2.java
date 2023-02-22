package com.example.applicationreservationcentre.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.adapters.adapter_img;
import com.example.applicationreservationcentre.adapters.roomadapter;
import com.example.applicationreservationcentre.models.equipement_model;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView Recycler;
    TextView name;
    TextView map;
    TextView centre_tele;
    TextView centre_email;
    TextView reservation;
    TextView des;
    TextView reservation_btn;
    CollectionReference ref;
    FloatingActionButton fab;
    FirebaseFirestore firestore =FirebaseFirestore.getInstance();
    ArrayList<equipement_model> array = new ArrayList<>();
    equipement_model e;
    roomadapter rooom;
    BottomSheetDialog mapona;
    SupportMapFragment mapFragment;
    BottomSheetBehavior<View> bottomSheetBehavior;
    String x;
    String y;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        array.add(new equipement_model(false , false, 0, 0));
        array.add(new equipement_model(false , false, 0, 0));
        array.add(new equipement_model(false , false, 0, 0));
        Recycler = findViewById(R.id.Recycler);
        centre_tele = findViewById(R.id.centre_tele);
        centre_email = findViewById(R.id.email_centre);

        GridLayoutManager G = new GridLayoutManager(getApplicationContext(),1,RecyclerView.HORIZONTAL,false);
        Recycler.setLayoutManager(G);


        fab =findViewById(R.id.fab);
        reservation_btn =findViewById(R.id.reservation_btn);

        mapona = new BottomSheetDialog(this);
        //mapona.setContentView(R.layout.consulte_map);
        View bottomsheet = LayoutInflater.from(this).inflate(R.layout.consulte_map , null);
        mapona.setContentView(bottomsheet);
        bottomSheetBehavior = BottomSheetBehavior.from((View) bottomsheet.getParent());
        bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
        bottomSheetBehavior .setState(BottomSheetBehavior.STATE_EXPANDED);
        CoordinatorLayout layout =bottomsheet.findViewById(R.id.coo);
        layout.setMinimumHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.MY_MAP);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng paris = new LatLng(Integer.parseInt(x), Integer.parseInt(y));
                googleMap.addMarker(new MarkerOptions().position(paris).title("Paris"));

            }
        });
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //LocationManager l = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + "KG" +latLng.latitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 200));
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });
        ImageView back =mapona.findViewById(R.id.back_btn);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapona.setCanceledOnTouchOutside(false);
                mapona.setCancelable(false);
                mapona.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapona.dismiss();
            }
        });
        ref = FirebaseFirestore.getInstance().collection("Centres");
        getSupportActionBar().hide();
        name =findViewById(R.id.name);
        map =findViewById(R.id.map);
        des =findViewById(R.id.des);
        name.setText(getIntent().getStringExtra("name_centre"));
        map.setText(getIntent().getStringExtra("map_centre"));
        //centre_tele.setText(getIntent().getStringExtra("centre_tele"));
        //centre_email.setText(getIntent().getStringExtra("centre_email"));
        des.setText(getIntent().getStringExtra("des"));
        String[] images = new String[4];
        images[0]= getIntent().getStringExtra("img_centre");
        reservation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity2.this , activity_reservation.class);
                it.putExtra("nom" ,""+name.getText().toString());
                startActivity(it);
            }
        });
        ViewPager2 v = findViewById(R.id.imgcentre);
        CircleIndicator3 indicator3 = findViewById(R.id.circle);
        ArrayList<String> li =new ArrayList<>();
        li.add(images[0]);
        li.add(images[1]);
        li.add(images[2]);
        li.add(images[3]);
        adapter_img adapter_img =new adapter_img(li);
        v.setAdapter(adapter_img);
        v.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        indicator3.setViewPager(v);
        indicator3.bringToFront();

        ref.document(""+getIntent().getStringExtra("name_centre")).collection("equipement").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    array = new ArrayList<>();
                    for (DocumentSnapshot item :
                            queryDocumentSnapshots) {
                        e =new equipement_model();
                        e.setDatashow(item.getBoolean("datashow"));
                        e.setWifi(item.getBoolean("wifi"));
                        e.setTables(Math.toIntExact(item.getLong("tables")));
                        e.setChairs(Math.toIntExact(item.getLong("chairs")));
                        array.add(e);
                        //Toast.makeText(MainActivity2.this, "nadi", Toast.LENGTH_SHORT).show();
                    }
                    rooom = new roomadapter(array);
                    Recycler.setAdapter(rooom);
                    rooom.notifyDataSetChanged();

                }
            }
        });
        ref.document(""+getIntent().getStringExtra("name_centre")).collection(""+getIntent().getStringExtra("name_centre")).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot item :
                        queryDocumentSnapshots) {
                    images[1] = item.getString("ref2");
                    images[2] = item.getString("ref3");
                    images[3] = item.getString("ref4");
                    li.set(1, item.getString("ref2"));
                    li.set(2, item.getString("ref3"));
                    li.set(3, item.getString("ref4"));
                    adapter_img.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}