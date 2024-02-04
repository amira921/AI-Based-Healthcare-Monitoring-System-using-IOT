package com.example.app_login.doctor.doctor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.app_login.doctor.doctor.FinalScheduleWithPName.FinalScheduleActivityWthPName;
import com.example.app_login.doctor.doctor.HisTabelID.PatientidHActivity;
import com.example.app_login.doctor.doctor.SymIdTabel.PatientidSymActivity;
import com.example.app_login.R;
import com.example.app_login.doctor.doctor.ChatGpt.ChatActivity;
import com.example.app_login.doctor.doctor.schedule.ScheduleActivity;

public class HistoryAndSymptomsActivity extends AppCompatActivity {

    private FloatingActionButton floatingbutton;
    Button symbutton,hisbutton, movesym ,movehis;
    String dId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyandsymptoms);


        // page title
        getSupportActionBar().setTitle("History");
        //Actionbar color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bijcolor3)));

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //buttons movements
        symbutton=findViewById(R.id.sym_button);
        hisbutton=findViewById(R.id.his_button);

        symbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryAndSymptomsActivity.this, PatientidSymActivity.class));
                SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                dId = sharedPref.getString("doctor_id", "");
                //Toast.makeText(HistoryAndSymptomsActivity.this, dId, Toast.LENGTH_SHORT).show();
            }
        });

        hisbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryAndSymptomsActivity.this, PatientidHActivity.class));
                //get doc id
                SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                dId = sharedPref.getString("doctor_id", "");
                //Toast.makeText(HistoryAndSymptomsActivity.this, dId, Toast.LENGTH_SHORT).show();

            }
        });

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        //floating button
        floatingbutton =findViewById(R.id.fab);

        floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movementFun();
            }
        });

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Bottom Navigation
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        //selected home item
        bottomNavigationView.setSelectedItemId(R.id.history);
        //switching between Activities
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext() , HomeActivity.class));
                    return true;

                case R.id.history:
                    return true;

                case R.id.calender:
                    startActivity(new Intent(getApplicationContext() , FinalScheduleActivityWthPName.class));
                    return true;

                case R.id.chat:
                    startActivity(new Intent(getApplicationContext() , ChatActivity.class));
                    return true;
            }

            return false;
        });

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    //////////////////////////////////////////////////////Functions/////////////////////////////////////////////////

    private void movementFun() {

        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        //declaration layouts
        LinearLayout scheduleLayout= dialog.findViewById(R.id.layoutschedule);
        LinearLayout videoLayout=dialog.findViewById(R.id.layoutvideo);
        //declaration canel button
        ImageView cancelbutton= dialog.findViewById(R.id.cancelbutton);


        //movement&dialog message
        scheduleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(HistoryAndSymptomsActivity.this, "creating and editing schedule", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HistoryAndSymptomsActivity.this , ScheduleActivity.class));
            }
        });

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(HistoryAndSymptomsActivity.this, "you are enter a meeting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HistoryAndSymptomsActivity.this , VideoActivity.class));

            }
        });

        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
}