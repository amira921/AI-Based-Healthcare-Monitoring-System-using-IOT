package com.example.app_login.patient.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.app_login.R;

public class SettingpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingp);

        // page title
        getSupportActionBar().setTitle("Settings");
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}