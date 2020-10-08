package com.example.fixcomputerproblem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {
    boolean doubleBackpress=false;
    private Button contactbtn, followUS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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