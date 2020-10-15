package com.example.fixcomputerproblem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User_Registration extends AppCompatActivity {
  private Button registerbtn, dobSelectbtn;
  private AwesomeValidation awesomeValidation;
  private EditText fistname, lastname, password, cpassword,   email;
  private  RadioButton male, female;
  private  String gander;
  private  DatePickerDialog.OnDateSetListener onDateSetListener;
   private  String date, userConfromPassword;
   private  static  String url="https://roboinfo31.000webhostapp.com/mysqliConnect2.php";

   private  String setResponce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__registration);
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
        //initalizing view objectis
        fistname=findViewById(R.id.firstName);
        lastname=findViewById(R.id.LName);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.cPassword);
        email=findViewById(R.id.emaiInput);
        male=findViewById(R.id.maleRadio);
        female=findViewById(R.id.femaleRadio);






        //adding validation
        awesomeValidation.addValidation(this,R.id.firstName,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",
                R.string.nameerror);
        awesomeValidation.addValidation(this,R.id.LName,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",
                R.string.nameerror);
        awesomeValidation.addValidation(this,R.id.emaiInput, Patterns.EMAIL_ADDRESS, R.string.nameerror);



        registerbtn=findViewById(R.id.regisrationBtn1);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(view==registerbtn){
                    submitForm();

                }
                if(female.isChecked()){
                    gander="Female";
                }
                else if(male.isChecked()){
                    gander="Male";
                }






            }
        });

        dobSelectbtn=findViewById(R.id.datepickerBtn);

        dobSelectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
               DatePickerDialog dialog=new DatePickerDialog(User_Registration.this, android.R.style.Theme_Holo_Dialog_MinWidth,onDateSetListener,
                       year,month, day);
               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
   onDateSetListener=new DatePickerDialog.OnDateSetListener() {
       @Override
       public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                date=i+ "-" + i1+"-" + i2;
                dobSelectbtn.setText(date);
       }
   };


    }
    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
           // Toast.makeText(this, setResponce, Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getApplication(), WelcomeScreen.class);

            //process the data furthe
            String UserPassword, confromPassword;
            UserPassword=password.getText().toString();

            confromPassword=cpassword.getText().toString();
             if(setPassword(UserPassword,confromPassword)){
                 userConfromPassword=confromPassword;
                insertData();
                 startActivity(intent);
             }
             else {
                 Toast.makeText(getApplicationContext(),"Both password not same", Toast.LENGTH_SHORT).show();
             }

        }

    }
    public  boolean setPassword(String pas, String pas1){
       return  pas.equals(pas1);
    }

     public void insertData(){
         StringRequest request=new StringRequest(Request.Method.POST, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                      //   setResponce=response;
                       //  Log.d("REsponse", response);
                     }
                 }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                 Log.d("Error msage",error.getMessage());
             }
         }

         ){
             protected Map<String, String> getParams() throws AuthFailureError{
                 Map<String,String> params=new HashMap<String, String>();
                 params.put("email", email.getText().toString());
                 params.put("fname", fistname.getText().toString());
                 params.put("lname", lastname.getText().toString());
                 params.put("password", userConfromPassword);
                 params.put("dob", date);
                 params.put("gander",getGander(female,male));
                 return params;
             }
         };
         RequestQueue i= Volley.newRequestQueue(this);
         i.add(request);
     }

     private  String getGander(RadioButton btn1, RadioButton btn2){
        if(btn1.isChecked()){
            return  btn1.getText().toString();
        }
        else if (btn2.isChecked()){
            return  btn2.getText().toString();
        }
        return  "select your gander First";
     }





}