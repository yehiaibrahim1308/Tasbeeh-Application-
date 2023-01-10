package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {

    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        handler
         */
        //the splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent i;
                i = new Intent(MainActivity.this, login2.class);
                startActivity(i);
                finish();
            }
            //the delay time
        },5000);
        }
    }
