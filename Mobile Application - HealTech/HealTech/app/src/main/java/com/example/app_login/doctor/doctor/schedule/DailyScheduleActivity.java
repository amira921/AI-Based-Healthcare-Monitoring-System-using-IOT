package com.example.app_login.doctor.doctor.schedule;

import static com.example.app_login.doctor.doctor.schedule.CalenderUtlis.selectedDate;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_login.dbconnection.Config;
import com.example.app_login.doctor.doctor.FScheduleDoc.FinalScheduleDoc;
import com.example.app_login.R;
import com.example.app_login.dbconnection.RequestHandler;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class DailyScheduleActivity extends AppCompatActivity {



    private TextView monthDayText;
    private TextView dayOfWeekTV;
    private Button btn;
    ArrayList<String> hours;
    HashMap<String,String> params = new HashMap<>();
    String itemSelected;
    String dayOfWeek;
    LocalDate date;
    String dId;


    //listview for working hours declaration
    ListView listViewHours;
    ArrayAdapter<String> adapter;
    // array contain working hours
    String[] arrayWorkHours={"8:00","9:00","10:00" ,"11:00" ,"12:00" ,
            "13:00" ,"14:00" ,"15:00" ,"16:00" ,"17:00" ,"18:00" ,
            "19:00" ,"20:00" ,"21:00" ,"22:00" ,"23:00" ,"24:00"  };

//////////////////////

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_schedule);

        // page title
        getSupportActionBar().setTitle("Working Hours");
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //actiobar color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Teal2)));

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //get doctor id
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        dId= sharedPref.getString("doctor_id", "");
        dId=dId.trim();
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //final schedule btn
        btn=findViewById(R.id.sch_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DailyScheduleActivity.this, FinalScheduleDoc.class));
                //Toast.makeText(DailyScheduleActivity.this, "jjjjjjjjjjjjjjjjjjj", Toast.LENGTH_SHORT).show();
            }
        });

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //listview for working hours reading IDs
        listViewHours =findViewById(R.id.listView_date_hours);

        //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,arrayWorkHours);
        adapter=new ArrayAdapter<String>(this,R.layout.textview_listview_color,arrayWorkHours);

        listViewHours.setAdapter(adapter);
        //listViewHours.setSelector(R.color.Teal4);
        listViewHours.setSmoothScrollbarEnabled(true);
        listViewHours.setSoundEffectsEnabled(true);
    /////////////////////////////////////////////////
                // function call for declaration parameters (calender)
                initWidgets();
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
/////////////////////////////////////////Functions//////////////////////////////////////////////////////////////////
    //Adding an day &date
    private void addAppoinment(){
        Toast.makeText(DailyScheduleActivity.this, "connecting", Toast.LENGTH_SHORT).show();
        class Appoinment extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                System.out.println("onPreExecute..............................");

                super.onPreExecute();
                loading = ProgressDialog.show(DailyScheduleActivity.this,"Adding...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                System.out.println("onPostExecute..............................");
                System.out.println("ssssssssssssssssss   "+ s);
                loading.dismiss();
                Toast.makeText(DailyScheduleActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {



                String res=new String();
               try {
                   //ArrayList<HashMap<String, String>> dateHours = new ArrayList<>();

                   for(int i=0;i<hours.size();i++){
                       HashMap<String,String> row=new HashMap<>();
                       row.put(Config.KEY_D_ID,dId);
                       row.put(Config.KEY_DATE,date.toString());
                       row.put(Config.KEY_DAY,dayOfWeek);
                       row.put(Config.KEY_HOUR,hours.get(i));
                       System.out.println("rowwwwwwwwww "+row);
                       RequestHandler rh = new RequestHandler();
                       res = rh.sendPostRequest(Config.URL_ADD, row);
                       // String res2 = rh.sendPostRequest(Config.URL_ADD_HOUR, hours);

                       System.out.println("post result     :    " + res);
                       //dateHours.add(row);
                   }

                   //Toast.makeText(DailyScheduleActivity.this, hours + "", Toast.LENGTH_SHORT).show();
                   //System.out.println("post result2     :    " + res2);

               }catch (Exception e){

                   System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeee  "+e);
               }
                return res;
                /*StringBuilder sb = new StringBuilder();
                try {
                    System.out.println("in try hady");
                    ArrayList<HashMap<String, String>> dateHours = new ArrayList<>();
                    System.out.println("hours size "+hours.size());

                    for(int i=0;i<hours.size();i++){
                        HashMap<String,String> row=new HashMap<>();
                        row.put(Config.KEY_D_ID,dId);
                        row.put(Config.KEY_DATE,date.toString());
                        row.put(Config.KEY_DAY,dayOfWeek);
                        row.put(Config.KEY_HOUR,hours.get(i));
                        System.out.println("rowwwwwwwwww "+row);
                        dateHours.add(row);
                    }
                    // Add data to the ArrayList of HashMaps
                    Gson gson = new Gson();
                    String json = gson.toJson(dateHours);

                    System.out.println("dateHoursssssssssssssssssssssssssssssssss-> "+json.toString());

                    URL url = new URL(Config.URL_ADD_DATE_HOURS);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpsURLConnection.HTTP_OK) {

                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        sb = new StringBuilder();
                        String response;
                        //Reading server response
                        while ((response = br.readLine()) != null){
                            sb.append(response);
                        }
                        System.out.println("response     :   "+response);
                    }

                    OutputStream os = conn.getOutputStream();
                    os.write(json.getBytes());
                    os.flush();
                    os.close();

                }catch (Exception e){



                }
                String s=new String(sb);
               return s;*/
            }
        }


        Appoinment ae = new Appoinment();
        System.out.println("aeeeeeeeeee "+ ae.toString());
        ae.execute();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //listview (menu buttoon done)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.done_menu,menu);
        return true;
    }

    //selected checkboxs items
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id =item.getItemId();
        if(id== R.id.done)
        {
           /* Toast.makeText(this, ""+listViewHours.getCheckedItemPosition(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "in Button ", Toast.LENGTH_SHORT).show();*/
            //show selected Hours

            hours=new ArrayList<>();
            for(int i=0; i<listViewHours.getCount();i++)
            {
                if(listViewHours.isItemChecked(i))
                {
                    //params.put(Config.KEY_HOUR,String.valueOf(listViewHours.getItemAtPosition(i)));


                    hours.add(listViewHours.getItemAtPosition(i).toString());

                }

            }
            System.out.println("hoursssssss------"+hours);


            addAppoinment();
            startActivity(new Intent(DailyScheduleActivity.this, ScheduleActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //calender id connect
    private void initWidgets()
    {
        monthDayText = findViewById(R.id.monthDayText);
        dayOfWeekTV = findViewById(R.id.dayOfWeekTV);
    }

//////////////////////////
    //calender
    //he setDayView() method that sets up the calendar to display the current day.
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        setDayView();
    }
/////////////////////////
    // display the selected day of a calendar.
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDayView() {
        monthDayText.setText(CalenderUtlis.monthDayFromDate(selectedDate));
        dayOfWeek = selectedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
        date=selectedDate;
        dayOfWeekTV.setText(dayOfWeek);

    }

////////////////////////////////
  /*  //to go previous day
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousDayAction(View view)
    {
        CalenderUtlis.selectedDate = CalenderUtlis.selectedDate.minusDays(1);
        setDayView();
    }
    //to go next day
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextDayAction(View view) {
        CalenderUtlis.selectedDate = CalenderUtlis.selectedDate.plusDays(1);
        setDayView();
    }*/

 //////////////////////////////////////////////////////////////////////////////////////////////
}