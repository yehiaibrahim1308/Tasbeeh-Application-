package com.example.first;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login2 extends Activity  implements View.OnClickListener{
    // here i am indentifying the variables that i will use in the future
    ImageView imag;
    EditText login;
    TextView forgot ;
    EditText password;//create object form edit text//
    Button login_bu, registration32;//create object frombutton//
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedinstancestate) {
        //setting a variable and connecting it to an id
        super.onCreate(savedinstancestate);

        setContentView(R.layout.acticity_login);
        login = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login_bu = findViewById(R.id.button_login);

         registration32 = findViewById(R.id.button_registration);
        registration32.setOnClickListener(this);
        imag = findViewById(R.id.picture2);

        //animations time lapse
        imag.animate().setDuration(2000).setStartDelay(3000);
        //pop up msg when pressed or actions taken on it
        imag.setOnClickListener(this);
        //calling a function where actions will take place in it

        //calling a function where actions will take place in it
       login_bu.setOnClickListener(this);
       mauth = FirebaseAuth.getInstance();
    }
    //here is a validation process starts where it vaidates  the email and password that the user will input
    private void validateemailaddress() {
        String emailInput = login.getText().toString();
        String passInput = password.getText().toString();
        String passauto = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        //this is where if everything is true and following the rules it will be accepted
        if (emailInput.isEmpty() ) {
            login.setError(" required");
            login.requestFocus();
            return;
        }
        //if both of them are empty
         if (passInput.isEmpty()) {
            password.setError(" required");
            password.requestFocus();
            return;
        } //if one of them are empty

        //if the email is not valid and the password is written
         if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches() ) {
            login.setError(" invalid");
            login.requestFocus();
            return;
        }
        if (!(passInput.length()>6)) {
            password.setError(" must be more than 6 charcaters");
            password.requestFocus();
            return;
        }
      mauth.signInWithEmailAndPassword(emailInput,passInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()){
                  startActivity(new Intent(login2.this,next_fragment.class));
                  Toast.makeText(login2.this, "welcome ", Toast.LENGTH_SHORT).show();
              }else{
                  Toast.makeText(login2.this, "email or password is  wrong", Toast.LENGTH_SHORT).show();
              }
          }
      });

    }




    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.picture2:
                Toast.makeText(this, "please login to enjoy our services", Toast.LENGTH_SHORT).show();

            case R.id.button_registration:
                startActivity(new Intent(this,act3.class));
            case R.id.button_login:
                validateemailaddress();
        }

    }
}
