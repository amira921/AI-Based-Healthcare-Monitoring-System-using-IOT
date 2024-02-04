package com.example.app_login.patient.patient;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.app_login.R;
import com.example.app_login.patient.patient.HistoryOfVitalsPatient.HistorypActivity;
import com.example.app_login.patient.patient.PatientHomeVitals.HomepActivity;
import com.example.app_login.patient.patient.finetuned_model.ChatpActivity;

public class MapActivity extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigationp;
    LocationManager manager;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] perm={Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,perm,1);
        }
        else {
            manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, (LocationListener) this, null);
        }

        // page title
        getSupportActionBar().setTitle("Map");


        /* Bottom Navigation*/ {
            bottomNavigationp = findViewById(R.id.bottomNavigationp);
            bottomNavigationp.show(1, true);

            bottomNavigationp.add(new MeowBottomNavigation.Model(1, R.drawable.ic_map3));
            bottomNavigationp.add(new MeowBottomNavigation.Model(2, R.drawable.ic_calender));
            bottomNavigationp.add(new MeowBottomNavigation.Model(3, R.drawable.ic_house_solid));
            bottomNavigationp.add(new MeowBottomNavigation.Model(4, R.drawable.ic_history3));
            bottomNavigationp.add(new MeowBottomNavigation.Model(5, R.drawable.ic_chatp));
        }

        //Bottom Navigation selected item for movement to anthor activity
        bottomNavigationp.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        break;

                    case 2:
                        startActivity(new Intent(MapActivity.this, CalenderpActivity.class));
                        break;

                    case 3:
                        startActivity(new Intent(MapActivity.this, HomepActivity.class));
                        break;

                    case 4:
                        startActivity(new Intent(MapActivity.this, HistorypActivity.class));
                        break;

                    case 5:
                        startActivity(new Intent(MapActivity.this, ChatpActivity.class));
                        break;

                }


            }
        });

        //Show icons clear and bigger
        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        break;

                }

            }
        });

        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 2:
                        startActivity(new Intent(MapActivity.this,CalenderpActivity.class));
                        break;

                }

            }
        });

        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 3:
                        startActivity(new Intent(MapActivity.this,HomepActivity.class));
                        break;

                }

            }
        });

        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 4:
                        startActivity(new Intent(MapActivity.this,HistorypActivity.class));
                        break;

                }

            }
        });

        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 5:
                        startActivity(new Intent(MapActivity.this,ChatpActivity.class));
                        break;

                }

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
                try {
                    manager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, (LocationListener) this, null);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            else
                Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();

        }
    }
}