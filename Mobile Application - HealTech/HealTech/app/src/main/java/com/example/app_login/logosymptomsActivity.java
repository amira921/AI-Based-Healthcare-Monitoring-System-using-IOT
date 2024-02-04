package com.example.app_login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.example.app_login.doctor.doctor.HomeActivity;
import com.example.app_login.patient.patient.SymptomsActivity;

public class logosymptomsActivity extends AppCompatActivity {

    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logosymptoms);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(logosymptomsActivity.this,SymptomsActivity.class));
                finish();

            }
        },7000);





    }
}