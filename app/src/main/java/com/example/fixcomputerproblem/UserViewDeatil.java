package com.example.fixcomputerproblem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class UserViewDeatil extends AppCompatActivity {

   private String userEmailGet;
    private  static String Url="https://roboinfo31.000webhostapp.com/fetch_UserData.php";

    private TextView fullname, dateofbirth, userGander, email;
    private Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_deatil);
        fullname=findViewById(R.id.namePrint);
        dateofbirth=findViewById(R.id.dobPrint);
        email=findViewById(R.id.emailPrint);
        userGander=findViewById(R.id.ganderPrint);
        backbtn=findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HomeScreen.class);
                startActivity(intent);
            }
        });

          userEmailGet=getIntent().getStringExtra("useEmailGet");
        Log.d("Email is", userEmailGet);


        insertData();
        setText(email,userEmailGet);
    }

    private  void insertData(){
        StringRequest request=new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        Log.d("REsponse", response);
                        try {
                            JSONArray username = new JSONArray(response);
                            for (int i = 0; i < username.length(); i++) {
                                JSONObject jsonObject = username.getJSONObject(i);

                                String emailuse= jsonObject.getString("email");
                                String fname = jsonObject.getString("firstName");
                                String LastNAme = jsonObject.getString("lName");
                                String Datebirth = jsonObject.getString("date_of_birth");
                                String Gander = jsonObject.getString("gander");
                                String fulName=fname + " " + LastNAme;

                                setText(fullname,fulName);
                                setText(userGander,Gander);
                                setText(dateofbirth,Datebirth);
                            }
                            // uselist.setAdapter(new useAdapterClass(val2));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();


            }
        }

        ){
            protected Map<String ,String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email",userEmailGet);
                return params;



            }
        };
        RequestQueue i= Volley.newRequestQueue(getApplicationContext());
        i.add(request);

    }
    public void setText(TextView txt, String value){
        String previusValue=txt.getText().toString();
        txt.setText(previusValue+ " " + value);
    }


}