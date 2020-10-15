package com.example.fixcomputerproblem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class Activate_Software extends AppCompatActivity {
    private  static  String url="https://roboinfo31.000webhostapp.com/sendRequest.php";
    private  String email;
    private RadioButton msofice, idm, other;
    private  String requestType;
    private EditText comments;
    private Button submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate__software);
        email=getIntent().getStringExtra("Email");

        msofice=findViewById(R.id.msWord);
        idm=findViewById(R.id.idm);
        other=findViewById(R.id.other);
        comments=findViewById(R.id.comment);
        submitbtn=findViewById(R.id.submiActivation);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitdate();
            }
        });




    }
    private  void  submitdate(){
        if(msofice.isChecked()){
            requestType="Activate MS Office";
        }
        else if(idm.isChecked()){
            requestType="Activate IDM";
        }
        else if(other.isChecked()){
            Toast.makeText(getApplicationContext(),
                    "kindly give the blew  software and etc deail for activation", Toast.LENGTH_LONG).show();
            requestType="other";
        }

        Intent intent=new Intent(getApplicationContext(), HomeScreen.class);
        insertData();
        startActivity(intent);
    }
    public void insertData(){
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        Log.d("REsponse", response);
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
                params.put("email", email);
                params.put("request", requestType );
                params.put("comments",comments.getText().toString());
                return params;
            }
        };
        RequestQueue i= Volley.newRequestQueue(this);
        i.add(request);
    }
}