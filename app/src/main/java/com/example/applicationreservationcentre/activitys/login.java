package com.example.applicationreservationcentre.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationreservationcentre.R;
import com.example.applicationreservationcentre.models.account;
import com.example.applicationreservationcentre.models.compoment_;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import eightbitlab.com.blurview.BlurView;
import okhttp3.internal.platform.Platform;

public class login extends AppCompatActivity {

    Button button_login;
    EditText password;
    TextView txt_signup;
    String test_email;
    String test_password;
    String img_account;
    String nom;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myref;
    BottomSheetDialog diyalog;
    View view1;
    //create account
    TextView invalide;
    ImageFilterView add_picture;
    ShapeableImageView img;
    Button button_create;
    StorageReference storageRef ;
    DatabaseReference ref ;
    Uri dat;
    EditText txt_nom;
    EditText txt_cin;
    EditText txt_tele;
    EditText txt_email;
    EditText txt_password;
    EditText txt_password2;
    private StorageTask mUploadTask;
    Snackbar snackbar;
    compoment_ compoment = new compoment_();
    boolean find;
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getlocal();
        setContentView(R.layout.activity_login);
        storageRef = FirebaseStorage.getInstance().getReference();
        ref = FirebaseDatabase.getInstance().getReference("Comptes");
        final EditText email;
        //forget_password = findViewById(R.id.forget);
        button_login = findViewById(R.id.button_login);
        button_login.setEnabled(true);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        txt_signup = findViewById(R.id.txt_signup);
        //
        diyalog = new BottomSheetDialog(login.this , R.style.BottomSheetDialogTheme);
        view1 = LayoutInflater.from(this).
                inflate(R.layout.activity_create_account , findViewById(R.id.llm));
        diyalog.setContentView(view1);

        invalide=findViewById(R.id.invalide);
        invalide.setVisibility(View.INVISIBLE);
        txt_nom=view1.findViewById(R.id.txt_nom);
        txt_cin=view1.findViewById(R.id.txt_cin);
        txt_tele=view1.findViewById(R.id.txt_tele);
        txt_email=view1.findViewById(R.id.txt_email);
        txt_password=view1.findViewById(R.id.txt_password);
        txt_password2=view1.findViewById(R.id.txt_password2);
        button_create=view1.findViewById(R.id.button_create);
        add_picture=view1.findViewById(R.id.add_picture);
        final EditText phone_number = view1.findViewById(R.id.txt_tele);
        img=view1.findViewById(R.id.img);
        add_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent();
                it.setType("image/*");
                it.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(it,1);
            }
        });
        phone_number.setText("+212 ");
        Selection.setSelection(phone_number.getText(), phone_number.getText().length());
        phone_number.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().startsWith("+212 ")){
                    phone_number.setText("+212 ");
                    Selection.setSelection(phone_number.getText(), phone_number.getText().length());

                }

            }
        });
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(txt_nom.getText().toString())){txt_nom.setError("required nom");}
                else txt_nom.setError(null);
                if(TextUtils.isEmpty(txt_cin.getText().toString())) txt_cin.setError("required cin");
                else txt_cin.setError(null);
                if(!(txt_tele.length() == 14)) txt_tele.setError("format indefini");
                else txt_tele.setError(null);
                if(!Patterns.EMAIL_ADDRESS.matcher(txt_email.getText().toString()).matches()){txt_email.setError("Email badly formated");}
                else txt_email.setError(null);
                if(TextUtils.isEmpty(txt_password.getText().toString())) txt_password.setError("required password");
                else txt_password.setError(null);
                if(!txt_password2.getText().toString().equalsIgnoreCase(txt_password.getText().toString())) txt_password2.setError("corriger le mot de passe");

                if(!TextUtils.isEmpty(txt_password2.getText().toString())) ;
                else txt_password2.setError("doen't match with origin password");
                firestore.collection("Comptes").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot q) {
                        boolean find = false;
                        if(!q.isEmpty()){
                            for(DocumentSnapshot item : q){
                                if(txt_email.getText().toString().equalsIgnoreCase(""+item.getString("email"))){
                                    txt_email.setError("This email al ready exits");
                                    find = true;
                                    return;
                                }
                            }
                            if(!find){
                                if(!TextUtils.isEmpty(txt_nom.getText().toString())&&
                                        !TextUtils.isEmpty(txt_cin.getText().toString())&&
                                        !TextUtils.isEmpty(txt_password.getText().toString())&&
                                        !TextUtils.isEmpty(txt_password2.getText().toString())&&
                                        txt_password2.getText().toString().equalsIgnoreCase(txt_password.getText().toString())&&
                                        txt_tele.length() == 14&&
                                        Patterns.EMAIL_ADDRESS.matcher(txt_email.getText().toString()).matches()){
                                    if(dat!=null){

                                        Dialog verification = new Dialog(v.getContext());
                                        verification.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        verification.setContentView(R.layout.verification);
                                        verification.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                        Button vtele = verification.findViewById(R.id.button_verefication_tele);
                                        Button vemail = verification.findViewById(R.id.button_verefication_email);
                                        verification.show();
                                        vemail.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                String username="amiine.amiine123@gmail.com";
                                                String password="amine12356";//zvadnvuswrwehdve
                                                Properties props =System.getProperties();

                                                props.put("mail.smtp.ssl.enable" , "true");
                                                props.put("mail.smtp.host" , "smtp.gmail.com");
                                                props.put("mail.smtp.port" , "465");
                                                props.put("mail.smtp.auth" , "true");
                                                javax.mail.Session session =javax.mail.Session.getInstance(props ,
                                                        new javax.mail.Authenticator(){
                                                            @Override
                                                            protected PasswordAuthentication getPasswordAuthentication() {
                                                                return new PasswordAuthentication(username , password);
                                                            }
                                                        });
                                                MimeMessage message = new MimeMessage(session);
                                                try {
                                                    message.addRecipient(Message.RecipientType.TO , new InternetAddress("naboulsiamine10062001@gmail.com"));
                                                    message.setSubject("aa");
                                                    message.setText("aaa");


                                                    Thread thread = new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            try {
                                                                Transport.send(message);
                                                                Toast.makeText(login.this, "Send", Toast.LENGTH_SHORT).show();
                                                            } catch (Exception e) {
                                                                Toast.makeText(login.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });

                                                } catch (AddressException ex) {
                                                    Toast.makeText(login.this, ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
                                                } catch (MessagingException e) {
                                                    Toast.makeText(login.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                        vtele.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                ActivityCompat.requestPermissions(login.this ,new String[]{
                                                        Manifest.permission.SEND_SMS , Manifest.permission.READ_SMS
                                                } , PackageManager.PERMISSION_GRANTED);
                                                SmsManager mysms =SmsManager.getDefault();
                                                mysms.sendTextMessage("5555555522" , null ,"aminub" ,null , null);
                                                Toast.makeText(login.this, "Send", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        upload_img(dat);
                                    }
                                    else{
                                        AlertDialog al = new AlertDialog.Builder(v.getContext()).setTitle("Picture").setMessage("Do you want to continu without picture").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dat = Uri.parse("android.resource://com.example.applicationreservationcentre/drawable/profil_img");
                                                upload_img(dat);
                                            }
                                        }).show();
                                    }
                                }
                            }
                        }


                    }
                });

            }
        });

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_nom.setText("");
                txt_cin.setText("");
                txt_tele.setText("+212");
                txt_email.setText("");
                txt_password.setText("");
                txt_password2.setText("");
                txt_email.setError(null);
                img.setImageResource(R.drawable.person_add_img);
                dat = null;
                diyalog.show();
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
                snackbar =Snackbar.make(view , "" , Snackbar.LENGTH_SHORT);
                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                View v = LayoutInflater.from(login.this).inflate(R.layout.snacktemplate , null);
                layout.addView(v , 0);
                snackbar.show();
                firestore.collection("Comptes").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot q) {
                        find = false;
                        if(!q.isEmpty()){
                            //button_login.setEnabled(false);
                            for(DocumentSnapshot item : q){
                                if(email.getText().toString().equalsIgnoreCase(""+item.getString("email"))
                                        &&password.getText().toString().equalsIgnoreCase(""+item.getString("password"))){
                                    invalide.setVisibility(View.INVISIBLE);
                                    test_email =item.getString("email").toString();
                                    test_password =item.getString("password");
                                    nom =item.getString("nom");
                                    img_account =item.getString("ref");
                                    compoment.set__nom(nom);
                                    compoment.set__email(test_email);
                                    compoment.set__img(img_account);
                                    compoment.set__id(item.getId().toString());
                                    Intent it = new Intent(login.this , MainActivity.class);
                                    startActivity(it);
                                    email.setError(null);
                                    password.setError(null);
                                    email.setText("");
                                    password.setText("");
                                    find = true;
                                    return;
                                }
                            }
                            if(!find) {
                                again();
                            }
                        }else{
                        }
                    }
                });
                /*if(TextUtils.isEmpty(test_email)&&TextUtils.isEmpty(test_password)){

                }*/
            }
        });
        /*forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(login.this, "Salam", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
    public void again(){
        invalide.setVisibility(View.VISIBLE);
        button_login.setEnabled(true);
        snackbar.dismiss();

    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void upload_img(Uri dat) {
        StorageReference fileReference = storageRef.child(txt_cin.getText().toString()+"." + getFileExtension(dat));
        Dialog dialog = new Dialog(this);
        mUploadTask =fileReference.putFile(dat).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dialog.dismiss();
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        others(uri.toString());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        });
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.progress_image_upload);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();


    }
    private void others(String s) {
        String tele ="";
        for (int i =0 ; i <= txt_tele.getText().length()-1;i++){
            if(i>=5){
                tele+=txt_tele.getText().charAt(i);
            }
        }
        account c = new account(
                ""+txt_email.getText(),
                ""+txt_password.getText(),
                ""+txt_nom.getText(),
                ""+tele.toString(),
                ""+s.toString(),"","","","");
        firestore.collection("Comptes").document(""+txt_cin.getText()).set(c);

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.created);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        Button btn_ok = dialog.findViewById(R.id.button_ok_created);
        dialog.show();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                diyalog.dismiss();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK){
            if(requestCode ==1 && data!=null && data.getData()!=null){
                dat =data.getData();
                img.setImageURI(dat);

            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void setlocal(String lang) {
        Locale l = new Locale(lang);
        Locale.setDefault(l);
        Configuration config = new Configuration();
        config.locale =l;
        getBaseContext().getResources().updateConfiguration(config , getBaseContext().getResources().getDisplayMetrics() );
        SharedPreferences.Editor editor = getSharedPreferences("settings_",MODE_PRIVATE).edit();
        editor.putString("my_lang" , lang);
        editor.apply();

    }
    private void getlocal() {
        SharedPreferences preferences = getSharedPreferences("settings_",MODE_PRIVATE);
        String lang =  preferences.getString("my_lang" ,"");
        setlocal(lang);
    }
}