package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class next_fragment extends AppCompatActivity {
    ImageView bck;
     TextView txtview;
     Button incbtn,rebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        bck = findViewById(R.id.backoption);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openimge();
            }
        });
        setui();
        incbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newtext= Integer.toString(Integer.parseInt(txtview.getText().toString())+1);
                txtview.setText(newtext);

            }
        });



    }
    public void resetTextview(View view){
        txtview.setText("0");
    }
    public void setui(){
        txtview=findViewById(R.id.textView3);
        incbtn=findViewById(R.id.button);

        rebtn=findViewById(R.id.button3);


    }
    public void decrease(){
        Intent yeshtagal;
        yeshtagal = new Intent(next_fragment.this,
                login2.class);
        Toast.makeText(this, "loged out", Toast.LENGTH_SHORT).show();
        startActivity(yeshtagal);
        finish();
    }
    public void reset(){
        Intent yeshtagal;
        yeshtagal = new Intent(next_fragment.this,
                login2.class);
        Toast.makeText(this, "loged out", Toast.LENGTH_SHORT).show();
        startActivity(yeshtagal);
        finish();
    }

    public void openimge(){
        Intent yeshtagal;
        yeshtagal = new Intent(next_fragment.this,
                login2.class);
        Toast.makeText(this, "loged out", Toast.LENGTH_SHORT).show();
        startActivity(yeshtagal);
        finish();
    }

}