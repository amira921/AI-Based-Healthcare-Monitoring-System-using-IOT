package com.example.app_login.doctor.doctor.FinalScheduleWithPName;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import android.widget.ListView;
import android.widget.Toast;

import com.example.app_login.dbconnection.Config;
import com.example.app_login.R;
import com.example.app_login.dbconnection.RequestHandler;
import com.example.app_login.doctor.doctor.ChatGpt.ChatActivity;
import com.example.app_login.doctor.doctor.HistoryAndSymptomsActivity;
import com.example.app_login.doctor.doctor.HomeActivity;
import com.example.app_login.doctor.doctor.VideoActivity;
import com.example.app_login.doctor.doctor.schedule.ScheduleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FinalScheduleActivityWthPName extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //floating button
    private FloatingActionButton floatingbutton;
    //table
    private ListView listView;
    private String JSON_STRING;
    String dId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_schedule_pname);

        // page title
        getSupportActionBar().setTitle("Appointments");
        //////////////////////////////////////////////////////////////////////////////////////////////////
        //get doctor id
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        dId = sharedPref.getString("doctor_id", "");
      //  Toast.makeText(FinalScheduleActivityWthPName.this, dId, Toast.LENGTH_SHORT).show();

        ////////////////////////////////////////////////////////////////////////////////////////////////

        //connection
        listView = (ListView) findViewById(R.id.listtable_final_schedule);
        listView.setOnItemClickListener(this);
        getJSON();
        try {
            showpfsch();
        }catch (Exception e) {
            System.out.println("Exception main              -------->     :   "+e);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////
        //UI


        //floating button

        floatingbutton =findViewById(R.id.fab);

        floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movementFun();
            }
        });

        /////////////////////////////////////////////////////////
        //bottomnavigation
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        //selected home item
        bottomNavigationView.setSelectedItemId(R.id.calender);
        //switching between Activities
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext() , HomeActivity.class));
                    return true;

                case R.id.history:
                    startActivity(new Intent(getApplicationContext() , HistoryAndSymptomsActivity.class));
                    return true;

                case R.id.calender:
                    return true;

                case R.id.chat:
                    startActivity(new Intent(getApplicationContext() , ChatActivity.class));
                    return true;
            }

            return false;
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////
    }


    /////////////////////////////////////////////////Functions//////////////////////////////////////////
    //connection
    private void showpfsch()
    {
        JSONObject jsonObject = null;
        ArrayList<FinalScheduledPNamedata> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String day = jo.getString(Config.TAG_DAY);
                String date = jo.getString(Config.TAG_DATE);
                String hours = jo.getString(Config.TAG_HOUR);
                String pName = jo.getString(Config.TAG_P_NAME);
                String spName = jo.getString(Config.TAG_P_SNAME);
                // String id = jo.getString(Config.TAG_P_ID);
                // String spName = jo.getString(Config.TAG_P_SNAME);
                FinalScheduledPNamedata fschdata=new FinalScheduledPNamedata(day,date,hours,pName,spName);
                list.add(fschdata);
                // System.out.println("list hash ------------>  "+list.get(i).getDay()+" , "+list.get(i).getDate()+" , "+list.get(i).getHours()+" , "+list.get(i).getpName()+" , "+list.get(i).getSpName());
            }

        } catch (JSONException e) {
            System.out.println("Exception              -------->     :   "+e);
            e.printStackTrace();
        }

        FinalschViewAdapterPName numbersArrayAdapter = new FinalschViewAdapterPName(this, list);
        listView.setAdapter(numbersArrayAdapter);
    }

////////////////////////////////////////////////////////

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FinalScheduleActivityWthPName.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showpfsch();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ALLFS,dId);
                System.out.println("result in backgrpound ----------------->  "+s);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    //////////////////////////////////////////////////////////
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Intent intent = new Intent(this, SymptomsDActivity.class);
        FinalScheduledPNamedata schData=(FinalScheduledPNamedata)parent.getItemAtPosition(position);
        //intent.putExtra("patient_id", schData.getId());
        //startActivity(intent);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //UI
    //floating button
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
                Toast.makeText(FinalScheduleActivityWthPName.this, "creating and editing schedule", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FinalScheduleActivityWthPName.this , ScheduleActivity.class));
            }
        });

        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(FinalScheduleActivityWthPName.this, "you are enter a meeting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(FinalScheduleActivityWthPName.this , VideoActivity.class));

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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}