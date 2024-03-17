package com.example.app_login.doctor.doctor.FScheduleDoc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app_login.dbconnection.Config;
import com.example.app_login.R;
import com.example.app_login.dbconnection.RequestHandler;
import com.example.app_login.doctor.doctor.FinalScheduleWithPName.FinalScheduleActivityWthPName;
import com.example.app_login.doctor.doctor.schedule.DailyScheduleActivity;
import com.example.app_login.doctor.doctor.schedule.ScheduleActivity;
import com.example.app_login.doctor.doctor.schedule.WeekScheduleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FinalScheduleDoc extends AppCompatActivity {

    //table
    private ListView listView;
    private String JSON_STRING;
    ArrayList<String> selected;
    ArrayList<FScheduleDataDoc> list;
    String idd,dId;
    ArrayList<String> ids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_schedule_doc2);

        // page title
        getSupportActionBar().setTitle("Final Schedule");
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        //get doctor id
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        dId = sharedPref.getString("doctor_id", "");
        //Toast.makeText(FinalScheduleDoc.this, dId, Toast.LENGTH_SHORT).show();
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        //connection
        listView = (ListView) findViewById(R.id.listtable_final_schedule_doc);
        selected= new ArrayList<>();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundResource(R.color.gray);
                if(selected.contains(String.valueOf(position))){
                    selected.remove(String.valueOf(position));
                    view.setBackgroundResource(R.color.white);
                }else{
                    selected.add(String.valueOf(position));
                }
            }
        });
        //listView.setOnItemClickListener(this);
        getJSON();
        try {
            showdfSch();
        }catch (Exception e) {
            System.out.println("Exception main              -------->     :   "+e);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    ///////////////////////////////////Functions///////////////////////////////////////////////////////////////////
    //connection
    //get function(Final doctor Schedule)
    private void showdfSch()
    {
        JSONObject jsonObject = null;
        list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                String dday = jo.getString(Config.TAG_DAY);
                String ddate = jo.getString(Config.TAG_DATE);
                String dhours = jo.getString(Config.TAG_HOUR);
                String id = jo.getString(Config.TAG_WD_ID);
                FScheduleDataDoc fschdatadoc=new FScheduleDataDoc(dday,ddate,dhours,id);
                list.add(fschdatadoc);
                System.out.println("list hash ------------>  "+list.get(i).getDday()+" , "+list.get(i).getDdate()+" , "+list.get(i).getDhour() +" , "+list.get(i).getId()  );
                idd=list.get(i).getId();

            }

        } catch (JSONException e) {
            System.out.println("Exception              -------->     :   "+e);
            e.printStackTrace();
        }

        FScheduleViewAdapterDoc numbersArrayAdapter = new FScheduleViewAdapterDoc(this, list);
        listView.setAdapter(numbersArrayAdapter);
    }
/////////////////////////////////////////////////////////////////

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FinalScheduleDoc.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showdfSch();
                System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"+ s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_ALLFSD,dId);
                System.out.println("result in backgrpound ----------------->  "+s);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //delete function
    private void deleteAppoinment(){
        class DeleteAppoinment extends AsyncTask<Void,Void,String>
        {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(FinalScheduleDoc.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(FinalScheduleDoc.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_DELETE, idd);
                return s;
            }
        }

        DeleteAppoinment de = new DeleteAppoinment();
        de.execute();
    }

    ////////////////////////////////////////////////////////
    private void confirmDeleteAppoin()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this Appoinment?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteAppoinment();
                        //      startActivity(new Intent(FinalScheduleActivity.this,ViewAllEmployee.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //menu for month ,week , day and delete button
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.month_week_day,menu);
        getMenuInflater().inflate(R.menu.delete_button,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {

            case R.id.fschedule:
                return true;
            case R.id.day:
                startActivity(new Intent(FinalScheduleDoc.this, DailyScheduleActivity.class));
                return true;

            case R.id.week:
                startActivity(new Intent(FinalScheduleDoc.this, WeekScheduleActivity.class));
                return true;

            case R.id.month:
                startActivity(new Intent(FinalScheduleDoc.this, ScheduleActivity.class));
                return true;

            case R.id.delete:{
               // Toast.makeText(this, "in Button delete ", Toast.LENGTH_SHORT).show();
                //delete coonection function
                confirmDeleteAppoin();
                for(int i=0 ;i<selected.size();i++){

                    // Toast.makeText(this, "remove position "+list.get(Integer. valueOf(selected.get(i))), Toast.LENGTH_SHORT).show();
                    int pos=Integer. valueOf(selected.get(i));
                    list.remove(pos);


                }
                FScheduleViewAdapterDoc numbersArrayAdapter = new FScheduleViewAdapterDoc(this, list);
                listView.setAdapter(numbersArrayAdapter);

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}