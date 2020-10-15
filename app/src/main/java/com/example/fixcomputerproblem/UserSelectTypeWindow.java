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
import java.util.PriorityQueue;

public class UserSelectTypeWindow extends AppCompatActivity {
   private RadioButton win10, win7, win8;
   private Button submitWindowbtn;
   private EditText comments;
   private  String  windowSelectDta;
    private  static  String url="https://roboinfo31.000webhostapp.com/sendRequest.php";
    private  String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        email=getIntent().getStringExtra("Email");
        Log.d("Email is", email);

        setContentView(R.layout.activity_user_select_type_window);
        win10=findViewById(R.id.window10);
        win7=findViewById(R.id.windwo7);
        win8=findViewById(R.id.windwo8_1);
        comments=findViewById(R.id.comment);
        submitWindowbtn=findViewById(R.id.submitWindow);
        submitWindowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_data();
            }
        });
    }

    private  void submit_data(){
        if(win7.isChecked()){
            windowSelectDta="Install Window 7";
        }
        else if(win10.isChecked()){
            windowSelectDta="Install window 10";
        }
        else if(win8.isChecked()){
            windowSelectDta="Install window 8.1";
        }
        if(comments.getText().toString().equals("null")){
            Toast.makeText(getApplicationContext(),"Comments not empity", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent submitWindow=new Intent(getApplicationContext(),HomeScreen.class);
            insertData();
            startActivity(submitWindow);

        }




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
                params.put("request", windowSelectDta );
                params.put("comments", comments.getText().toString());
                return params;
            }
        };
        RequestQueue i= Volley.newRequestQueue(this);
        i.add(request);
    }
}