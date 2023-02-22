package com.example.applicationreservationcentre.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.models.compoment_;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationreservationcentre.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    compoment_ compoment;
    FirebaseFirestore firestore;
    ImageView img;
    TextView nom;
    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        compoment = new compoment_();
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_commentaire)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View headerView = navigationView.getHeaderView(0);
        img = headerView.findViewById(R.id.slide_img_profil);
        nom = headerView.findViewById(R.id.slide_nom_profil);
        email = headerView.findViewById(R.id.slide_email_profil);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        firestore = FirebaseFirestore.getInstance();
        nom.setText(compoment.get__nom());
        email.setText(compoment.get__email());
        Picasso.get().load(compoment.get__img()).into(img);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle(R.string.quiter)
                .setMessage(R.string.txt_quiter)
                .setPositiveButton(R.string.ok , null)
                .setNegativeButton(R.string.annuler , null)
                .show();

        Button ok_btn =dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.this.finish();
                System.exit(0);
            }
        });
    }
    public void click_setting(MenuItem item) {
        if(item.getItemId() == R.id.setting_slide){
            Intent it = new Intent(MainActivity.this , settingbar.class);
            startActivity(it);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}