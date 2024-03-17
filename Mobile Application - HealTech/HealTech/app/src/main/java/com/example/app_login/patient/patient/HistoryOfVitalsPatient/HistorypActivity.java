package com.example.app_login.patient.patient.HistoryOfVitalsPatient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.app_login.R;
import com.example.app_login.dbconnection.Config;
import com.example.app_login.dbconnection.RequestHandler;
import com.example.app_login.patient.patient.CalenderpActivity;
import com.example.app_login.patient.patient.finetuned_model.ChatpActivity;
import com.example.app_login.patient.patient.PatientHomeVitals.HomepActivity;
import com.example.app_login.patient.patient.MapActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistorypActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Button b;
    String ID;
    private String JSON_STRING;
    ListView listView;
    private MeowBottomNavigation bottomNavigationp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historyp);
        listView = findViewById(R.id.BP_data);

        // page title
        getSupportActionBar().setTitle("Vitals");
        // to show back button
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get patient id
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        ID = sharedPref.getString("patient_id", "");

        /////////////////////////////////////////////////////////////////////////////

        //connection
        listView = (ListView) findViewById(R.id.BP_data);
        listView.setOnItemClickListener(this);
        getJSON();
        try {
            showppHistory();
        }catch (Exception e) {
            System.out.println("Exception main              -------->     :   "+e);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        UI naigation

//        ( movement pages)
        bottomNavigationp=findViewById(R.id.bottomNavigationp);
        bottomNavigationp.show(4,true);

        bottomNavigationp.add(new MeowBottomNavigation.Model(1,R.drawable.ic_map3));
        bottomNavigationp.add(new MeowBottomNavigation.Model(2,R.drawable.ic_calender));
        bottomNavigationp.add(new MeowBottomNavigation.Model(3,R.drawable.ic_house_solid));
        bottomNavigationp.add(new MeowBottomNavigation.Model(4,R.drawable.ic_history3));
        bottomNavigationp.add(new MeowBottomNavigation.Model(5,R.drawable.ic_chatp));

        //Bottom Navigation selected item for movement to anthor activity
        bottomNavigationp.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        startActivity(new Intent(HistorypActivity.this, MapActivity.class));
                        map(new View(HistorypActivity.this));

                        break;

                    case 2:
                        startActivity(new Intent(HistorypActivity.this, CalenderpActivity.class));
                        break;

                    case 3:
                        startActivity(new Intent(HistorypActivity.this, HomepActivity.class));
                        break;

                    case 4:
                        startActivity(new Intent(HistorypActivity.this,HistorypActivity.class));
                        break;

                    case 5:
                        break;

                }


            }
        });
        /////////////////////////////////////////////////
        //Show icons clear and bigger
        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        startActivity(new Intent(HistorypActivity.this, MapActivity.class));
                        break;

                }

            }
        });


        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 2:
                        startActivity(new Intent(HistorypActivity.this,CalenderpActivity.class));
                        break;

                }

            }
        });



        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 3:
                        startActivity(new Intent(HistorypActivity.this, HomepActivity.class));
                        break;

                }

            }
        });



        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 4:
                        break;

                }

            }
        });



        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 5:
                        startActivity(new Intent(HistorypActivity.this, ChatpActivity.class));
                        break;

                }

            }
        });
    }


    //map fuction
    public void map(View view) {

        Intent in=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Nearest+Hospital"));
        startActivity(in);
    }




    ////////////////////////////////////////////////////////////////
//    connection


    private void showppHistory() {
        JSONObject jsonObject = null;
        ArrayList<HisPatientData> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String bpressure = jo.getString(Config.TAG_PPRESSURE);
                String poxgen = jo.getString(Config.TAG_POXGEN);
                String ptemp = jo.getString(Config.TAG_PTEMP);
                String pheartrate = jo.getString(Config.TAG_PHEARTRATE);
                String time = jo.getString(Config.TAG_PTIME);
                String date = jo.getString(Config.TAG_PHDATE);




                HisPatientData phisdata=new HisPatientData(bpressure,poxgen,ptemp,pheartrate,time,date);
                list.add(phisdata);
                 System.out.println("list hash ------------>  "+list.get(i).getBpressure()+" , "+list.get(i).getPoxgen()+" , "+list.get(i).getPtemp()+" , "+list.get(i).getPheartrate()+" , "+list.get(i).getTime()+" , "+list.get(i).getDate());
            }

        } catch (JSONException e) {
            System.out.println("Exception              -------->     :   "+e);
            e.printStackTrace();
        }

       HisPatientViewAdapter numbersArrayAdapter = new HisPatientViewAdapter(this, list);
        listView.setAdapter(numbersArrayAdapter);
    }


    /////////////////////////////////////////////////////////

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(HistorypActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showppHistory();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ALLPH,ID);
                System.out.println("result in backgrpound ----------------->  "+s);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


}