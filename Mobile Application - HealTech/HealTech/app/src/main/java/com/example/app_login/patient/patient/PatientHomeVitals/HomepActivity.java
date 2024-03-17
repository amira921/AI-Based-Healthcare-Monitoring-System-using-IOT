package com.example.app_login.patient.patient.PatientHomeVitals;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.app_login.R;
import com.example.app_login.dbconnection.Config;
import com.example.app_login.dbconnection.RequestHandler;
import com.example.app_login.patient.patient.CalenderpActivity;
import com.example.app_login.patient.patient.HistoryOfVitalsPatient.HistorypActivity;
import com.example.app_login.patient.patient.MeetingpActivity;
import com.example.app_login.patient.patient.SymptomsActivity;
import com.example.app_login.patient.patient.finetuned_model.ChatpActivity;
import com.example.app_login.signlogin.MainActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomepActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {


    String ID;
    private String JSON_STRING;
    ListView listView;


    private MeowBottomNavigation bottomNavigationp;
    public FloatingActionButton floatingsymptomsbutton;
    ImageView menu2;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar

        setContentView(R.layout.activity_homep);

        //declaration Video button in bar
        ImageView videobutton= findViewById(R.id.video_p);

        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        ID = sharedPref.getString("patient_id", "");

        // Toast.makeText(this, ID, Toast.LENGTH_SHORT).show();


        //connection
        listView = (ListView) findViewById(R.id.Home_Patient_Vitals);
        listView.setOnItemClickListener(this);
        getJSON();
        try {
            showpPHomeHistory();
        }catch (Exception e) {
            System.out.println("Exception main              -------->     :   "+e);
        }


        //move to Video Activity
        videobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomepActivity.this, MeetingpActivity.class));

            }
        });

        //menu &sheetbutton in bar
        menu2=findViewById(R.id.menu_p);
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movementpatientFun();

            }
        });


        //FloatingActionButton(Scan ,Symptoms)
        floatingsymptomsbutton =findViewById(R.id.floatingsymptomsbutton);

        floatingsymptomsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomepActivity.this, SymptomsActivity.class));
            }
        });


//        bp = findViewById(R.id.BP);
//        o = findViewById(R.id.O);
//        t = findViewById(R.id.T);
//        hr = findViewById(R.id.HR);

//
//        getJSON();
//        try {
//            vitals_home(ID);
//        }catch (Exception e) {
//            //  Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
//        }


        //Bottom Navigation declaratin
        bottomNavigationp=findViewById(R.id.bottomNavigationp);

        bottomNavigationp.show(3,true);

        //Bottom Navigation icons
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
                        map(new View(HomepActivity.this));
                        break;

                    case 2:
                        startActivity(new Intent(HomepActivity.this, CalenderpActivity.class));
                        break;

                    case 3:
                        break;

                    case 4:
                        startActivity(new Intent(HomepActivity.this, HistorypActivity.class));
                        break;

                    case 5:
                        startActivity(new Intent(HomepActivity.this, ChatpActivity.class));
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
                        map(new View(HomepActivity.this));
                        break;

                }

            }
        });


        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 2:
                        startActivity(new Intent(HomepActivity.this,CalenderpActivity.class));
                        break;

                }

            }
        });



        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 3:
                        break;

                }

            }
        });



        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 4:
                        startActivity(new Intent(HomepActivity.this,HistorypActivity.class));
                        break;

                }

            }
        });



        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 5:
                        startActivity(new Intent(HomepActivity.this, ChatpActivity.class));
                        break;

                }

            }
        });

    }


//    coonection
    private void showpPHomeHistory() {
        JSONObject jsonObject = null;
        ArrayList<HomePatientVitalsData> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String hpressure = jo.getString(Config.TAG_PHPRESSURE);
                String hoxgen = jo.getString(Config.TAG_PHOXGEN);
                String htemp = jo.getString(Config.TAG_PHTEMP);
                String hheartrate = jo.getString(Config.TAG_PHHEARTRATE);




                HomePatientVitalsData phomehisdata=new HomePatientVitalsData(hpressure,hoxgen,htemp,hheartrate);
                list.add(phomehisdata);
//                 System.out.println("list hash ------------>  "+list.get(i).getHpressure()+" , "+list.get(i).getHoxgen()+" , "+list.get(i).getHtemp()+" , "+list.get(i).getHheartrate());
            }

        } catch (JSONException e) {
            System.out.println("Exception              -------->     :   "+e);
            e.printStackTrace();
        }

        HomePatientVitalsViewAdapter numbersArrayAdapter = new HomePatientVitalsViewAdapter(this, list);
        listView.setAdapter(numbersArrayAdapter);
    }

    ////////////////////////////////////////
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(HomepActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showpPHomeHistory();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ALLPV,ID);
                System.out.println("result in backgrpound ----------------->  "+s);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    ///////////////////////////////////////////////////////////////////////////////////

//    map function
    public void map(View view) {

        Intent in=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Nearest+Hospital"));
        startActivity(in);
    }

    //menu sheetbutton
    // movement Function
    private void movementpatientFun() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetpatientsetting_layout);
        LinearLayout layoutpatientlogout = dialog.findViewById(R.id.layoutpatientlogout);




        layoutpatientlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(HomepActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomepActivity.this, MainActivity.class));


            }
        });


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}