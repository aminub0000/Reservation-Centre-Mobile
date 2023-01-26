package com.example.applicationreservationcentre.ui.home;

import android.content.Intent;
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

import com.example.applicationreservationcentre.Adapter_centre;
import com.example.applicationreservationcentre.MainActivity2;
import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.centre_info;
import com.example.applicationreservationcentre.centre_item;
import com.example.applicationreservationcentre.databinding.FragmentHomeBinding;
import com.example.applicationreservationcentre.offre;
import com.example.applicationreservationcentre.selectadapter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements centre_item, selectadapter.OnpikerListiner{
    RecyclerView rec_horizontal;
    RecyclerView recyclerView;
    TextInputLayout txt1;
    AutoCompleteTextView dropdown_text;
    ArrayList<centre_info> list_centre = new ArrayList<>();
    ImageButton btn_search;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rec_horizontal = binding.recycleview;
        recyclerView =binding.recycleview1;
        txt1 =binding.txt1;
        btn_search =binding.btnSearch;
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "haaaaa", Toast.LENGTH_SHORT).show();
            }
        });
        dropdown_text =binding.dropdownText;
        txt1.getEditText().setSingleLine(true);
        String[] items = new String[]{"centre massira", "centre 7okoma", "centre sabab ach", "centreat",
                "centre pasito", "centre paice", "centre ach", "centreat"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity() ,
                R.layout.dropdown_item,
                items );
        txt1.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.getEditText().setText("");
            }
        });
        dropdown_text.setAdapter(adapter);

        ArrayList<offre> offreArrayList;
        offreArrayList = new ArrayList<>();
        offreArrayList.add(new offre(R.drawable.equip_purple,"Touts"));
        offreArrayList.add(new offre(R.drawable.purple_wifi,"Wifi"));
        offreArrayList.add(new offre(R.drawable.purple_datashow,"Datashow"));

        selectadapter selectadapter= new selectadapter(offreArrayList , this);
        selectadapter.notifyDataSetChanged();
        rec_horizontal.setAdapter(selectadapter);
        GridLayoutManager grid = new GridLayoutManager(getActivity() ,1,GridLayoutManager.HORIZONTAL,false);
        rec_horizontal.setLayoutManager(grid);


        list_centre.add(new centre_info(R.drawable.c1 ,
                "Centre Paice" ,
                "Maroc-Tiznit chari3 30 rue 02",false,true,
                10,150,"dadaddadad"));
        list_centre.add(new centre_info(R.drawable.c1,
                "Centre d'etude et formation" ,
                "Maroc-Tiznit hay nahda rue 32",true,true,
                6,50,"dadaddadad"));
        list_centre.add(new centre_info(R.drawable.c1 ,
                "Centre massira" ,
                "Maroc-Tiznit haymassira rue 06",false,false,
                12,200,"dadaddadad"));

        list_centre.add(new centre_info(R.drawable.c1 ,
                "Centre tawjtat" ,
                "Maroc-Tiznit hay bota9kort reu 05",true,false,
                15,220,"dadaddadad"));

        Adapter_centre adapter_centre = new Adapter_centre(list_centre,this);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter_centre);

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
        intent.putExtra("img_centre",String.valueOf(list_centre.get(pos).getImage_centre()));
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