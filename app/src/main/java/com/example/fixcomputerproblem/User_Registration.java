package com.example.fixcomputerproblem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import java.util.Calendar;

public class User_Registration extends AppCompatActivity {
  private Button registerbtn, dobSelectbtn;
  private AwesomeValidation awesomeValidation;
  private EditText fistname, lastname, password, cpassword,   email;
  private RadioGroup gander;
  private  DatePickerDialog.OnDateSetListener onDateSetListener;
   private  String date;

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

                // Intent intent=new Intent(getApplication(), WelcomeScreen.class);
             //   startActivity(intent);
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
                date=i+ "/" + i1+"/" + i2;
       }
   };


    }
    private void submitForm() {
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Validation Successfull", Toast.LENGTH_LONG).show();

            //process the data further
        }
    }

}