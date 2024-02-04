package com.example.app_login.doctor.doctor.Symptomsdoctor;

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

public class SymptomsDActivity extends AppCompatActivity {

    private ListView listView;
    private String JSON_STRING;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsymptoms);


        // page title
        getSupportActionBar().setTitle("Patient Symptoms");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bijcolor3)));
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //get patient id
        Intent in=getIntent();
        id=in.getStringExtra("patient_id");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        listView = (ListView) findViewById(R.id.listtable_Symdoctor);
        // listView.setOnItemClickListener(this);
        getJSON();

        try {
            showPSym();
        }catch (Exception e) {
            System.out.println("Exception main              -------->     :   "+e);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    /////////////////////////////////////////////////////////////Functions//////////////////////////////////////////////////////////////
//Connection
    private void showPSym()
    {
        JSONObject jsonObject = null;
        ArrayList<SymDataD> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            System.out.println("jsonnnnnnnnnnnn------->"+jsonObject.toString());
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String stoulcer = jo.getString(Config.TAG_STOMACH_ULCER);
                String heartb = jo.getString(Config.TAG_HBURN);
                String vomi = jo.getString(Config.TAG_VOMITING);
                String nau = jo.getString(Config.TAG_NAUSEA);
                String diarr = jo.getString(Config.TAG_DIARRHEA);
                String incon = jo.getString(Config.TAG_INCONTINENCE);
                String bloatt = jo.getString(Config.TAG_BLOAT);
                String bleedd = jo.getString(Config.TAG_BLEED);
                String coughh = jo.getString(Config.TAG_COUGH);
                String sneez = jo.getString(Config.TAG_SNEEZ);
                String stuffyn = jo.getString(Config.TAG_S_NOSE);
                String sothroat = jo.getString(Config.TAG_S_THROAT);
                String breathh = jo.getString(Config.TAG_BREATH);
                String headd = jo.getString(Config.TAG_HEADACHE);
                String dizzinn = jo.getString(Config.TAG_DIZZ);
                String bachee = jo.getString(Config.TAG_BODY_ACHES);
                String skinirri = jo.getString(Config.TAG_SKIN_IRRI);
                String cons = jo.getString(Config.TAG_CONSTIPATION);
                String sdate = jo.getString(Config.TAG_SDATE);

                SymDataD symdata=new SymDataD(stoulcer,heartb,vomi,nau,diarr,incon,
                        bloatt,bleedd,coughh,sneez,stuffyn,sothroat,
                        breathh,headd,dizzinn,bachee,skinirri,cons ,sdate);

                list.add(symdata);
                System.out.println("list hash ------------>  "+list.get(i).toString());
            }

        } catch (JSONException e) {
            System.out.println("Exception              -------->     :   "+e);
            e.printStackTrace();
        }

        SymViewAdapter numbersArrayAdapter = new SymViewAdapter(this, list);
        listView.setAdapter(numbersArrayAdapter);
    }

/////////////////////////////////////


    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SymptomsDActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showPSym();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ALLS,id);
                System.out.println("result in backgrpound ----------------->  "+s);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}

