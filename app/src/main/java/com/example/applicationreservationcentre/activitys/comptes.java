package com.example.applicationreservationcentre.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.models.compoment_;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class comptes extends AppCompatActivity {
    ProgressBar progressBar;
    private int progress_status=0;
    Handler handler = new Handler();
    ImageButton back;
    TextView prog_txt;
    TextView change_image;
    EditText txt_nom_account;
    EditText txt_categorie_account;
    EditText txt_numtele_account;
    TextInputEditText txt_email_account;
    TextInputEditText txt_adress_account;
    TextInputLayout txt_sexe_account;
    EditText txt_aniv_account;
    EditText txt_ville_account;
    EditText txt_cin_account;

    Uri img_selected_uri;
    ShapeableImageView img_account;
    String id_account;

    DatabaseReference ref ;

    String Nom;
    String Email;
    String telephone;
    String adresse;
    String anniversaire;
    String sexe;
    String ville;
    String Img;
    TextView title_nom;
    AutoCompleteTextView dropdown_text;
    DatePickerDialog.OnDateSetListener setListener;

    compoment_ compoment = new compoment_();
    int counter =10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comptes);

        ref = FirebaseDatabase.getInstance().getReference("Comptes");
        progressBar =findViewById(R.id.progress_bar);
        img_account =findViewById(R.id.img_account);
        back =findViewById(R.id.back);
        prog_txt =findViewById(R.id.prog_txt);
        change_image =findViewById(R.id.change_image);
        txt_sexe_account =findViewById(R.id.txt_sexe_account);
        txt_aniv_account =findViewById(R.id.txt_aniv_account);

        txt_nom_account =findViewById(R.id.txt_nom_account);
        txt_categorie_account =findViewById(R.id.txt_categorie_account);
        txt_numtele_account =findViewById(R.id.txt_numtele_account);
        txt_email_account =findViewById(R.id.txt_email_account);
        txt_adress_account =findViewById(R.id.txt_adress_account);
        txt_ville_account =findViewById(R.id.txt_ville_account);
        txt_cin_account =findViewById(R.id.txt_cin_account);

        dropdown_text =findViewById(R.id.dropdown_text);

        title_nom =findViewById(R.id.title_nom);
        Calendar c=Calendar.getInstance();
        final int year =c.get(Calendar.YEAR);
        final int month =c.get(Calendar.MONTH);
        final int day =c.get(Calendar.DAY_OF_MONTH);
        txt_aniv_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(comptes.this
                        , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener ,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                String date_piked = dayOfMonth+"/"+month+"/"+year;
                txt_aniv_account.setText(date_piked);
            }
        };

        String[] items = new  String[2];
        items[0] ="Homme";
        items[1] ="Femme";
        txt_sexe_account.getEditText().setSingleLine(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , R.layout.dropdown_item , items);
        dropdown_text.setAdapter(adapter);
        id_account = compoment.get__id();
        load_data();                                                 //**********************************
        ActionBar tl = getSupportActionBar();
        tl.hide();

        change_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent , 2);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Nom.equalsIgnoreCase(txt_nom_account.getText().toString())

                        &&Email.equalsIgnoreCase(txt_email_account.getText().toString())

                        &&telephone.equalsIgnoreCase(txt_numtele_account.getText().toString())

                        &&adresse.equalsIgnoreCase(txt_adress_account.getText().toString())

                        &&anniversaire.equalsIgnoreCase(txt_aniv_account.getText().toString())

                        &&ville.equalsIgnoreCase(txt_ville_account.getText().toString())

                        &&sexe.equalsIgnoreCase(txt_sexe_account.getEditText().getText().toString())

                        &&img_selected_uri == null)
                {
                    finish();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("Votre profil a été modifé.\nSouhaitez-vous le sauvegarder ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if(img_selected_uri != null){
                                update_img();
                                finish();
                            }
                            else{
                                update();
                                finish();
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    }).setCancelable(false);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (progress_status<counter){

                    progress_status+=1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress_status);
                            prog_txt.setText(progress_status+"%");
                        }
                    });
                    try {
                        Thread.sleep(25);

                    }catch (Exception e){
                        Toast.makeText(comptes.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).start();
    }
    private void load_data() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Nom = snapshot.child(id_account).child("nom").getValue(String.class);
                Email = snapshot.child(id_account).child("email").getValue(String.class);
                telephone = snapshot.child(id_account).child("tele").getValue(String.class);
                adresse = snapshot.child(id_account).child("adresse").getValue(String.class);
                ville = snapshot.child(id_account).child("ville").getValue(String.class);
                anniversaire = snapshot.child(id_account).child("anniv").getValue(String.class);
                sexe = snapshot.child(id_account).child("sexe").getValue(String.class);
                Img = snapshot.child(id_account).child("ref").getValue(String.class);

                if(!Nom.isEmpty()){
                    counter +=10;
                }

                if (!Email.isEmpty()){
                    counter +=10;
                }

                if (!telephone.isEmpty()){
                    counter +=10;
                }

                if (!adresse.isEmpty()){
                    counter +=10;
                }

                if (!anniversaire.isEmpty()){
                    counter +=10;
                }

                if (!sexe.isEmpty()){
                    counter +=10;
                }

                if (Img != null){
                    counter +=10;
                }

                if (!ville.isEmpty()){
                    counter +=10;
                }
                txt_nom_account.setText(Nom);
                title_nom.setText(Nom);
                txt_aniv_account.setText(anniversaire);
                txt_adress_account.setText(adresse);
                txt_numtele_account.setText(telephone);
                txt_email_account.setText(Email);
                Picasso.get().load(Img).into(img_account);
                txt_sexe_account.getEditText().setText(sexe);
                txt_ville_account.setText(ville);
                txt_cin_account.setText(id_account);
                counter+=10;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void update_img() {
        StorageReference reference = FirebaseStorage.getInstance().getReference(img_selected_uri+"." + getFileExtension(img_selected_uri));
        reference.putFile(img_selected_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        });
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                ref.child(""+id_account).child("ref").setValue(uri.toString());
                compoment.set__img(uri.toString());
                update(compoment.get__img());
                //Toast.makeText(comptes.this, ""+compoment.get__img(), Toast.LENGTH_SHORT).show();
                img_selected_uri = null;
            }
        });
    }

    private void update() {
        ref.child(""+id_account).child("nom").setValue(txt_nom_account.getText().toString());
        ref.child(""+id_account).child("email").setValue(txt_email_account.getText().toString());
        ref.child(""+id_account).child("nom").setValue(txt_nom_account.getText().toString());
        ref.child(""+id_account).child("tele").setValue(txt_numtele_account.getText().toString());
        ref.child(""+id_account).child("anniv").setValue(""+txt_aniv_account.getText().toString());
        ref.child(""+id_account).child("adresse").setValue(""+txt_adress_account.getText().toString());
        ref.child(""+id_account).child("sexe").setValue(""+txt_sexe_account.getEditText().getText().toString());
        ref.child(""+id_account).child("ville").setValue(""+txt_ville_account.getText().toString());
        compoment.set__nom(txt_nom_account.getText().toString());
        compoment.set__email(txt_email_account.getText().toString());
    }
    private void update(String img) {
        ref.child(""+id_account).child("ref").setValue(""+compoment.get__img().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 2 && data != null){
                img_selected_uri = data.getData();
                img_account.setImageURI(img_selected_uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}