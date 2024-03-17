package com.example.app_login.doctor.doctor.Historydoctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.app_login.dbconnection.Config;
import com.example.app_login.R;
import com.example.app_login.dbconnection.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryDActivity extends AppCompatActivity {
    private ListView listView;
    private String JSON_STRING;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_dactivity);

        // page title
        getSupportActionBar().setTitle("Patient Vitals");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bijcolor3)));
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //get patient id from HisidActivity
        Intent intent1 = getIntent();
        id = intent1.getStringExtra("patient_id");
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //for connection
        listView = (ListView) findViewById(R.id.listtableh);
        //listView.setOnItemClickListener(this);
        getJSON();

        try {
            showPHis();
        }catch (Exception e) {
            System.out.println("Exception main              -------->     :   "+e);
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
///////////////////////////////////////////////////Functions////////////////////////////////////////////////////////
    private void showPHis()
    {
        JSONObject jsonObject = null;
        ArrayList<HistoryData> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                String temp = jo.getString(Config.TAG_TEMP);
                String oxg = jo.getString(Config.TAG_OXGEN);
                String hrate = jo.getString(Config.TAG_PULSE);
                String pres = jo.getString(Config.TAG_PRESSURE);
                String htime = jo.getString(Config.TAG_TIME);
                String hdate = jo.getString(Config.TAG_HDATE);
                HistoryData hisdata=new HistoryData(temp,oxg,hrate,pres,htime,hdate);
                list.add(hisdata);
                //System.out.println("list hash ------------>  "+list.get(i).getTemp()+" , "+list.get(i).getOxg()+" , "+list.get(i).getHrate()+" , "+list.get(i).getPres());
            }

        } catch (JSONException e) {
            System.out.println("Exception              -------->     :   "+e);
            e.printStackTrace();
        }

        HistroyViewAdapter numbersArrayAdapter = new HistroyViewAdapter(this, list);
        listView.setAdapter(numbersArrayAdapter);
    }
/////////////////////////////////////////////
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(HistoryDActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showPHis();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ALLH,id);
                System.out.println("result in backgrpound ----------------->  "+s);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}