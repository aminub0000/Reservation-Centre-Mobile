package com.example.applicationreservationcentre.ui.reservation;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.adapters.adapter_reservation;
import com.example.applicationreservationcentre.databinding.FragmentGalleryBinding;
import com.example.applicationreservationcentre.models.compoment_;
import com.example.applicationreservationcentre.models.reservation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import eightbitlab.com.blurview.BlurView;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    BlurView blurview;
    LinearLayout slide_list;
    DrawerLayout drawer_layout;
    ArrayList<reservation> list_reservation;
    adapter_reservation adapter;
    RecyclerView recyclerView;
    compoment_ c;
    FirebaseFirestore firestore;
    reservation res;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = binding.recyclerView;
        firestore =FirebaseFirestore.getInstance();
        c =new compoment_();
        firestore.collection("Comptes").document(""+c.get__id()).collection("reservation").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                list_reservation.clear();
                for (DocumentSnapshot item:
                        queryDocumentSnapshots) {
                    res = new reservation();
                    res.setNom_centre(item.getString("nom_centre"));
                    res.setNom_reservation(item.getString("nom_reservation"));
                    res.setImage_centre(item.getString("image_centre"));
                    res.setDate_reservation(item.getString("date_reservation"));
                    res.setReservation_heure_debut(item.getString("reservation_heure_debut"));
                    res.setReservation_heure_fin(item.getString("reservation_heure_fin"));
                    res.setKey(item.getId());
                    res.setResultat(item.getString("resultat"));
                    list_reservation.add(res);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        list_reservation = new ArrayList<>();
        list_reservation.add(new reservation("https://firebasestorage.googleapis.com/v0/b/reservationcentre-d7b77.appspot.com/o/Centre-d_etude-et-formation_img3.png?alt=media&token=3d1578ed-56c2-4464-bca7-a319333dcba6"
                ,"Mon reservation de piace"
                ,"Centre Piace"
                ,"04/01/2023"
                ,"10:00 AM"
                ,"12:00 AM","","en Cours ..."));

        adapter = new adapter_reservation(list_reservation);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(helper);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return root;
    }
    ItemTouchHelper.SimpleCallback helper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            list_reservation.remove(viewHolder.getAdapterPosition());
            firestore.collection("Comptes").document(""+c.get__id())
                    .collection("reservation")
                    .document(""+list_reservation.get(viewHolder.getAdapterPosition()).getKey())
                    .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(), "removed", Toast.LENGTH_SHORT).show();
                        }
                    });
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder( getContext(), c, recyclerView, viewHolder, dX, dY, actionState,
                    isCurrentlyActive)
                    .addSwipeLeftBackgroundColor (ContextCompat .getColor( getContext(), R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.delete)
                    .addSwipeRightBackgroundColor(ContextCompat . getColor( getContext(), R.color.red) )
                    .addSwipeRightActionIcon(R.drawable.delete)
                    .create()
                    .decorate() ;

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);


        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}