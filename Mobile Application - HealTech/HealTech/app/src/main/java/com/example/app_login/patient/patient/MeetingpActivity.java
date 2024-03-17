package com.example.app_login.patient.patient;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MeetingpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_meetingp);
        zoom(MeetingpActivity.this);

        // page title
      /*  getSupportActionBar().setTitle("Meeting");
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
    }
    private void zoom(Context context) {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("zoomus://join?action=join&confno=73494462573&pwd=2kX8qc"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                startActivity(intent);
            } catch(Exception e) {
                Toast.makeText(this, e.toString() , Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}