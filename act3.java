package com.example.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class act3 extends Activity implements View.OnClickListener {

    // here i am indentifying the variables that i will use in the future
    ImageView back;
   Button registart;
   EditText email_signup,fullname,firstpassword,secondpassword;

     private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//setting a variable and connecting it to an id

        setContentView(R.layout.activity_registration);
        registart = findViewById(R.id.bu_registiration);
        registart.setOnClickListener(this);
        email_signup = findViewById(R.id.registration_email);
        fullname = findViewById(R.id.registration_name);
        firstpassword = findViewById(R.id.registration_password);
        secondpassword = findViewById(R.id.registration_replay_passwrod);
        back = findViewById(R.id.back_button);
        back.setOnClickListener(this);
        //here i am calling an class where it makes some actions when clicked
        mauth = FirebaseAuth.getInstance();


    }
    //the validation class where all the validation process takes place
    private void validation() {
        String email1 = email_signup.getText().toString().trim();
        String pass1 = firstpassword.getText().toString().trim();
        String pass2 = secondpassword.getText().toString().trim();
        String fuli = fullname.getText().toString().trim();
//if every rules that i wirote below in the condition is true





            //if all the above textfields are empty

         if (email1.isEmpty() ) {
            email_signup.setError(" required");
            email_signup.requestFocus();
            return;


        }
        //if everything is not empty but the email is not valid
        if ( !Patterns.EMAIL_ADDRESS.matcher(email1).matches() ) {
            email_signup.setError(" invalid");
            email_signup.requestFocus();
             return;
        }

         if (fuli.isEmpty()) {
            fullname.setError(" required");
            fullname.requestFocus();
             return;


        }
        if ( pass1.isEmpty()) {
            firstpassword.setError(" required");
            firstpassword.requestFocus();
             return;

        }
         if ( pass2.isEmpty() ) {
            secondpassword.setError(" required");
            secondpassword.requestFocus();
             return;


        }
         if (!(pass1.length()>6)){
            firstpassword.setError(" must be more than 6 charcaters");
            firstpassword.requestFocus();
             return;

        }
        if (!(pass2.length()>6)){
            secondpassword.setError(" must be more than 6 charcaters");
            secondpassword.requestFocus();
             return;

        }
         if(!(pass1.equals(pass2))){
            Toast.makeText(this, "passwords do not  match!", Toast.LENGTH_SHORT).show();
             return;
        }


       mauth.createUserWithEmailAndPassword(email1,pass1)
               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           user user =new user(email1,fuli,pass1,pass2);
                           FirebaseDatabase.getInstance().getReference("users")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if(task.isSuccessful()){
                                               Toast.makeText(act3.this, "user has been registerd", Toast.LENGTH_LONG).show();
                                               Intent yeshtagal;
                                               yeshtagal = new Intent(act3.this,
                                                       login2.class);
                                               startActivity(yeshtagal);
                                               finish();
                                           }
                                           else{
                                               Toast.makeText(act3.this, "user has not  been registerd", Toast.LENGTH_LONG).show();
                                           }
                                       }
                                   });
                       }
                       else{
                           Toast.makeText(act3.this, "user has not been registerd 2", Toast.LENGTH_LONG).show();
                       }
                   }
               });
        }




    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.back_button:
                startActivity(new Intent(this,login2.class));
                break;
             case R.id.bu_registiration:
                 validation();
                 break;

         }
    }
}
