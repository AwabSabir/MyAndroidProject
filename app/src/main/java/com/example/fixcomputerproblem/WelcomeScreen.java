package com.example.fixcomputerproblem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeScreen extends AppCompatActivity {
    private Button loginbtn, registrationbtn, loginbtn2;
    private EditText username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        registrationbtn=(Button)findViewById(R.id.regisrationBtn);

        registrationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplication(), User_Registration.class);
                startActivity(intent);
            }
        });



        loginbtn=(Button)findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog();
            }
        });
    }
    private  void showDialog(){
        AlertDialog.Builder alert;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            alert = new AlertDialog.Builder(this,android.R.style.Theme_Material_Dialog_Alert);
        }
        else{
            alert =new AlertDialog.Builder(this);
        }
        LayoutInflater inflater= getLayoutInflater();
        View view=inflater.inflate(R.layout.login_screen_popup,null);

        //now just use variable like this and get data
        username=view.findViewById(R.id.emaiLogi);
        loginbtn2=view.findViewById(R.id.userpresslogin);
        loginbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(),HomeScreen.class);
                startActivity(intent);
            }
        });
        alert.setView(view);

        alert.setCancelable(false);

        AlertDialog dialog= alert.create();
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}