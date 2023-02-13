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
    TextInputLayout txt1;
    AutoCompleteTextView dropdown_text;
    ArrayList<centre_info> list_centre = new ArrayList<>();
    ImageButton btn_search;
    private FragmentHomeBinding binding;
    FirebaseFirestore firestore =FirebaseFirestore.getInstance();
    CollectionReference ref =firestore.collection("Centres");
    String[] lsit_pre_fill_centre  = new String[4];
    centre_info centre_info =new centre_info();
    centre_info c =new centre_info();
    String[] imgs = new String[4];
    int index = 0;
    ShimmerFrameLayout shimmer ;
    BlurView blurview;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    Adapter_centre adapter_centre;
    ArrayList<centre_info> centre_search = new ArrayList<>();
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
        ArrayList<String> li = new ArrayList<>();

        rec_horizontal = binding.recycleview;
        blurview = binding.blurview;
        recyclerView =binding.recycleview1;
        txt1 =binding.txt1;
        btn_search =binding.btnSearch;
        shimmer =binding.shimmer;
        float radius = 20f;
        View decorView = getActivity().getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);

        Drawable windowsBackground = decorView. getBackground();

        blurview.setupWith(rootView)
                .setFrameClearDrawable(windowsBackground)
                .setBlurAlgorithm(new RenderScriptBlur( getContext()))
                .setBlurRadius(radius)
                .setBlurAutoUpdate(true)
                .setHasFixedTransformationMatrix(true);

        dropdown_text =binding.dropdownText;
        txt1.getEditText().setSingleLine(true);
        firestore.collection("Centres").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty()){
                    items = new ArrayList<>();
                    for(DocumentSnapshot item :queryDocumentSnapshots){
                        items.add(item.getId().toString());
                    }
                    adapter = new ArrayAdapter<>(getActivity(),
                            R.layout.dropdown_item,
                            items );
                    dropdown_text.setAdapter(adapter);
                }
            }
        });
        txt1.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.getEditText().setText("");
            }
        });

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
                            list_centre.add(centre_info);
                            ref.document(""+d.getString("nom").toString())
                                    .collection(""+d.getString("nom").toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                        @Override
                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                            for (DocumentSnapshot item : queryDocumentSnapshots) {
                                                imgs = new String[4];
                                                imgs[0] = item.getString("ref1");
                                                imgs[1] = item.getString("ref2");
                                                imgs[2] = item.getString("ref3");
                                                imgs[3] = item.getString("ref4");
                                                list_centre.get(index).setImage_centre(imgs);
                                                index++;
                                            }
                                            adapter_centre.notifyDataSetChanged();
                                            shimmer.stopShimmer();
                                            shimmer.setVisibility(View.GONE);
                                            recyclerView.setVisibility(View.VISIBLE);
                                        }
                                    });

                        }

                }
            }
        });

        return root;
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
    public void centre_onclick_(int pos, ImageView imgcentre, TextView name, TextView map, ImageView icon_map) {
        Intent intent = new Intent(getActivity() , MainActivity2.class);
        intent.putExtra("img_centre",list_centre.get(pos).getImage_centre(0));
        intent.putExtra("name_centre",list_centre.get(pos).getCentre_name());
        intent.putExtra("map_centre",list_centre.get(pos).getCentre_maptext());
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