package com.example.applicationreservationcentre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    TextView forget_password;
    Button button_login;
    EditText password;
    TextView txt_signup;
    String test_email;
    String test_password;
    String img_account;
    String nom;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref;
    ArrayList<account> accounts = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email;
        //forget_password = findViewById(R.id.forget);
        button_login = findViewById(R.id.button_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        txt_signup = findViewById(R.id.txt_signup);
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent it =new Intent(login.this , createaccount.class);
                Pair<View ,String> p1 = Pair.create(button_login , "login_button");
                Pair<View ,String> p2 = Pair.create(email , "emailtransiction");
                Pair<View ,String> p3 = Pair.create(password , "passwordtransiction");
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(login.this,p1,p2,p3);
                startActivity(it , options.toBundle());
                email.setText("");
                password.setText("");
                email.setError(null);
                password.setError(null);*/
            }
        });



        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()
                        && TextUtils.isEmpty(password.getText().toString())){
                    email.setError("Email badly formated");
                    password.setError("Password is required");
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError("Email badly formated");
                    password.setError(null);
                    return;
                }
                if (TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Password is required");
                    email.setError(null);
                    return;
                }
                myref =database.getReference();
                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int count = (int) snapshot.child("Comptes").getChildrenCount();
                        for(DataSnapshot item :snapshot.child("Comptes").getChildren()){
                            test_email =snapshot.child("Comptes").child(item.getKey().toString()).child("email").getValue(String.class);
                            test_password =snapshot.child("Comptes").child(item.getKey().toString()).child("password").getValue(String.class);
                            img_account =snapshot.child("Comptes").child(item.getKey().toString()).child("ref").getValue(String.class);
                            nom =snapshot.child("Comptes").child(item.getKey().toString()).child("nom").getValue(String.class);
                            accounts.add(new account(nom ,test_email,test_password,img_account));
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if(TextUtils.isEmpty(test_email)&&TextUtils.isEmpty(test_password)){
                    Snackbar snackbar =Snackbar.make(view , "" , Snackbar.LENGTH_SHORT);
                    Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                    View v = LayoutInflater.from(login.this).inflate(R.layout.snacktemplate , null);
                    layout.addView(v , 0);
                    snackbar.show();
                    return;
                }
                for (account item: accounts) {
                    if(email.getText().toString().equalsIgnoreCase(item.getEmail())&&password.getText().toString().equalsIgnoreCase(item.getPassword())){
                        Intent it = new Intent(login.this , MainActivity.class);
                        it.putExtra("img_account",item.getImage());
                        it.putExtra("nom_account",item.getNom());
                        it.putExtra("email_account",item.getEmail());
                        startActivity(it);
                        email.setError(null);
                        password.setError(null);
                        email.setText("");
                        password.setText("");
                        return;
                    }
                }
                Toast.makeText(login.this, "incorrect information", Toast.LENGTH_SHORT).show();
            }
        });
        /*forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(login.this, "Salam", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}