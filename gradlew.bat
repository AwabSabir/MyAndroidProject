package com.example.fargmentsworking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class preview_data extends AppCompatActivity {
private EditText email;
private ListView printdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_data);
        email=(EditText)findViewById(R.id.email);

    }

    private  void insertData(){
        StringRequest request=new StringRequest(Request.Method.POST, "https://roboinfo31.000webhostapp.com/fetch_array_data_databace.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        try {
                            JSONArray username =new JSONArray(response);
                            for(int i=0; i<username.length(); i++){
                                 JSONObject jsonObject=username.getJSONObject(i);

                                 String email=jsonObject.getString("email");
                                 String fullname=jsonObject.getString("fullname");

                                 
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(get