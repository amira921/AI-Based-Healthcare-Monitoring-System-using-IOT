package com.example.app_login.doctor.doctor.HisTabelID;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_login.dbconnection.Config;
import com.example.app_login.R;
import com.example.app_login.dbconnection.RequestHandler;
import com.example.app_login.doctor.doctor.Historydoctor.HistoryDActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PatientidHActivity extends AppCompatActivity  implements ListView.OnItemClickListener
{

    private ListView listView;
    private String JSON_STRING;
    String dId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientid_hactivity);

        // page title
        getSupportActionBar().setTitle("");
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // actionbar color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bijcolor3)));
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //get doc id
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        dId = sharedPref.getString("doctor_id", "");
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //for connection
        listView = (ListView) findViewById(R.id.listtable_patient_id_his);
        listView.setOnItemClickListener(this);
        getJSON();
        try {
            showIdPHis();
        }catch (Exception e) {
            System.out.println("Exception main              -------->     :   "+e);
        }
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
///////////////////////////////////////Function////////////////////////////////////////////////////////////////
    private void showIdPHis()
    {
        JSONObject jsonObject = null;
        ArrayList<HisIDdata> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                String id = jo.getString(Config.TAG_P_ID);
                String name = jo.getString(Config.TAG_P_NAME);
                String sname = jo.getString(Config.TAG_P_SNAME);
                HisIDdata hisIDdata=new HisIDdata(id,name,sname);
                list.add(hisIDdata);
                System.out.println("list hash ------------>  "+list.get(i).getId()+" , "+list.get(i).getName());
            }

        } catch (JSONException e) {
            System.out.println("Exception              -------->     :   "+e);
            e.printStackTrace();
        }

        HisIDViewAdapter numbersArrayAdapter = new HisIDViewAdapter(this, list);
        listView.setAdapter(numbersArrayAdapter);
    }

    /////////////////////////////////////////////////////////////////////

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(PatientidHActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showIdPHis();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ALL,dId);
                System.out.println("result in backgrpound ----------------->  "+s);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
//////////////////////////////////////////////
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(PatientidHActivity.this, HistoryDActivity.class);
        HisIDdata hisData=(HisIDdata)parent.getItemAtPosition(position);
        intent.putExtra("patient_id", hisData.getId());
        startActivity(intent);
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

