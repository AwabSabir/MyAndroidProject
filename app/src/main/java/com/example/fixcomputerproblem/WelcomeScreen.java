package com.example.fixcomputerproblem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
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

public class WelcomeScreen extends AppCompatActivity {
    private Button loginbtn, registrationbtn, loginbtn2;
    private EditText email,password;
    private  static  String url="https://roboinfo31.000webhostapp.com/fetch_array_data_databace2.php";
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
        email=view.findViewById(R.id.emaiLogi);
        password=view.findViewById(R.id.passwordInput);
        loginbtn2=view.findViewById(R.id.userpresslogin);
        loginbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fetchData();

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

    public void fetchData(){
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equalsIgnoreCase("Data found")){
                            Intent intent=new Intent(getApplication(),HomeScreen.class);
                            intent.putExtra("Eamil", email.getText().toString());
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Welcome", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("Error msage",error.getMessage());
            }
        }

        ){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
               params.put("email", email.getText().toString());
               params.put("password",password.getText().toString());

                return params;
            }
        };
        RequestQueue i= Volley.newRequestQueue(this);
        i.add(request);
    }

}