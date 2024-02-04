package com.example.app_login;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.example.app_login.signlogin.D_Sign_Up;
import com.example.app_login.signlogin.MainActivity;
import com.example.app_login.signlogin.PopUp;

import java.util.Timer;
import java.util.TimerTask;

public class logo_page_open extends AppCompatActivity {

    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page_open);

        getSupportActionBar().hide(); // hide the title bar



        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(logo_page_open.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}