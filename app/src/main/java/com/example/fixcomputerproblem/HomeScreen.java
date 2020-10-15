package com.example.fixcomputerproblem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class HomeScreen extends AppCompatActivity {
    boolean doubleBackpress=false;
    private Button contactbtn, followUS, viewProfile, activatebtn, installSoftware;
    private  String getUserEmail;
    private ImageView insallWindow;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserEmail=getIntent().getStringExtra("Eamil");
        setContentView(R.layout.activity_home_screen);
        contactbtn=(Button)findViewById(R.id.contactUSbtn);
        contactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contact=new Intent(Intent.ACTION_DIAL);
                contact.setData(Uri.parse("tel:+923400435320"));
                startActivity(contact);
            }
        });

        followUS=(Button)findViewById(R.id.followusbtn);
        followUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openFacebook=new Intent(Intent.ACTION_VIEW);
                openFacebook.setData(Uri.parse("https://www.facebook.com/awab.sabir"));
                startActivity(openFacebook);
            }
        });

        viewProfile=findViewById(R.id.viewProfile);
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), UserViewDeatil.class);
                intent.putExtra("useEmailGet", getUserEmail);
                startActivity(intent);
            }
        });

        insallWindow=findViewById(R.id.windowintall);
        insallWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),UserSelectTypeWindow.class);
                intent.putExtra("Email", getUserEmail);
                startActivity(intent);
            }
        });
        activatebtn=findViewById(R.id.activatebtn);
        activatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Activate_Software.class);
                intent.putExtra("Email", getUserEmail);
                startActivity(intent);
            }
        });
        installSoftware=findViewById(R.id.instaSoftware);
        installSoftware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte=new Intent(getApplicationContext(),UserSelectTypeWindow.class);
                inte.putExtra("Email", getUserEmail);
                startActivity(inte);
            }
        });



    }

    @Override
    public void onBackPressed() {
        if(doubleBackpress){
            super.onBackPressed();

            return;
        }
        this.doubleBackpress=true;
        Toast.makeText(getApplicationContext(),"Press one more back time to exit ",Toast.LENGTH_SHORT).show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackpress=false;
            }
        },2000);

    }




}