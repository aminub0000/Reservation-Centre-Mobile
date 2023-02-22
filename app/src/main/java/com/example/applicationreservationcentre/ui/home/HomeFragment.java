package com.example.applicationreservationcentre.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationreservationcentre.adapters.Adapter_centre;
import com.example.applicationreservationcentre.activitys.MainActivity2;
import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.models.centre_info;
import com.example.applicationreservationcentre.models.centre_item;
import com.example.applicationreservationcentre.databinding.FragmentHomeBinding;
import com.example.applicationreservationcentre.models.offre;
import com.example.applicationreservationcentre.adapters.selectadapter;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class HomeFragment extends Fragment implements centre_item, selectadapter.OnpikerListiner{
    RecyclerView rec_horizontal;
    RecyclerView recyclerView;
    AutoCompleteTextView dropdown_text;
    ArrayList<centre_info> list_centre = new ArrayList<>();
    ArrayList<centre_info> list_search = new ArrayList<>();
    private FragmentHomeBinding binding;
    FirebaseFirestore firestore =FirebaseFirestore.getInstance();
    CollectionReference ref =firestore.collection("Centres");
    String[] lsit_pre_fill_centre  = new String[4];
    centre_info centre_info =new centre_info();
    centre_info c =new centre_info();
    String[] imgs = new String[4];
    androidx.appcompat.widget.SearchView search;
    int index = 0;
    ShimmerFrameLayout shimmer ;
    Adapter_centre adapter_centre;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rec_horizontal = binding.recycleview;
        recyclerView =binding.recycleview1;
        shimmer =binding.shimmer;
        search =binding.search;

        ArrayList<offre> offreArrayList;
        offreArrayList = new ArrayList<>();
        offreArrayList.add(new offre(R.drawable.equip_purple,"Touts"));
        offreArrayList.add(new offre(R.drawable.purple_wifi,"Wifi"));
        offreArrayList.add(new offre(R.drawable.purple_datashow,"Datashow"));

        selectadapter selectadapter= new selectadapter(offreArrayList , this);
        rec_horizontal.setAdapter(selectadapter);
        GridLayoutManager grid = new GridLayoutManager(getActivity() ,1,GridLayoutManager.HORIZONTAL,false);
        rec_horizontal.setLayoutManager(grid);

        adapter_centre = new Adapter_centre(list_centre,this);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter_centre);
        lsit_pre_fill_centre[0] = ""+R.drawable.background_item_centre;
        lsit_pre_fill_centre[1] = ""+R.drawable.background_item_centre;
        lsit_pre_fill_centre[2] = ""+R.drawable.background_item_centre;
        lsit_pre_fill_centre[3] = ""+R.drawable.background_item_centre;

        firestore.collection("Centres").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                        for (DocumentSnapshot d :queryDocumentSnapshots){
                            centre_info = new centre_info();
                            centre_info.setImage_centre(lsit_pre_fill_centre);
                            centre_info.setCentre_name(d.getString("nom").toString());
                            centre_info.setCentre_maptext(d.getString("map").toString());
                            centre_info.setDes("Description : "+d.getString("des").toString());
                            centre_info.setNb_classroom(Integer.parseInt(d.getString("nb_classroom")));
                            centre_info.setHair(Integer.parseInt(d.getString("hair")));
                            centre_info.setDatashow(d.getBoolean("datashow"));
                            centre_info.setWifi(d.getBoolean("wifi"));
                            centre_info.setEmail_tele(d.getString("email"));
                            centre_info.setCentre_tele(d.getString("tele"));
                            imgs = new String[4];
                            imgs[0] = d.getString("ref1");
                            imgs[1] = d.getString("ref2");
                            imgs[2] = d.getString("ref3");
                            imgs[3] = d.getString("ref4");
                            centre_info.setImage_centre(imgs);
                            list_centre.add(centre_info);
                            shimmer.stopShimmer();
                            shimmer.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }

                }
            }
        });
        search.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                list_search =new ArrayList<>();
                if(newText.length()>0){
                    for(int i =0 ; i<list_centre.size(); i++){
                        if(list_centre.get(i).getCentre_name().toUpperCase().contains(newText.toUpperCase())){
                            list_search.add( (centre_info) list_centre.get(i));
                        }
                    }
                    change_recycle();

                }
                else{
                    backto_recycle();
                }
                return false;
            }
        });

        return root;
    }

    public void change_recycle(){
        adapter_centre = new Adapter_centre(list_search ,this);
        recyclerView.setAdapter(adapter_centre);
        adapter_centre.notifyDataSetChanged();
    }
    public void backto_recycle(){
        adapter_centre = new Adapter_centre(list_centre ,this);
        recyclerView.setAdapter(adapter_centre);
        adapter_centre.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onClickpiker(int position) {
        ArrayList<centre_info> list = new ArrayList<>();
        if(position == 0){
            recyclerView.setAdapter(new Adapter_centre(list_centre,this));
        }
        else if (position == 1){
            for (centre_info item:list_centre) {
                if(item.isWifi() && !item.isDatashow() ){
                    list.add(item);
                }
            }
            recyclerView.setAdapter(new Adapter_centre(list,this));}
        else if (position == 2){
            for (centre_info item:list_centre) {
                if(item.isDatashow() && !item.isWifi()){
                    list.add(item);
                }
            }
            recyclerView.setAdapter(new Adapter_centre(list,this));
        }

    }
    @Override
    public void centre_onclick_(int pos, ImageView imgcentre, TextView name, TextView map, ImageView icon_map,String imgUrl) {
        Intent intent = new Intent(getActivity() , MainActivity2.class);
        intent.putExtra("img_centre",imgUrl);
        intent.putExtra("name_centre",name.getText().toString());
        intent.putExtra("map_centre",map.getText().toString());
        intent.putExtra("des",list_centre.get(pos).getDes());
        Pair[] pairs =new Pair[4];
        pairs[0]= new Pair<View ,String>(imgcentre,"transaction_imgcente");
        pairs[1]= new Pair<View ,String>(name,"transaction_nom");
        pairs[2]= new Pair<View ,String>(map,"transaction_map");
        pairs[3]= new Pair<View ,String>(icon_map,"transaction_iconmap");

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity() , pairs);
        startActivity(intent ,options.toBundle());
    }
}