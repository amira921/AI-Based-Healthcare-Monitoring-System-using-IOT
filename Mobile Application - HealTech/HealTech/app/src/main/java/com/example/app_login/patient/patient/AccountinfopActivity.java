package com.example.app_login.patient.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.app_login.R;

public class AccountinfopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountinfop2);

        // page title
        getSupportActionBar().setTitle("Account info");
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}