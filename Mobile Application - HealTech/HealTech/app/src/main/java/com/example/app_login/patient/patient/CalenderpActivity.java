package com.example.app_login.patient.patient;

import static com.example.app_login.doctor.doctor.schedule.CalenderUtlis.daysInMonthArray;
import static com.example.app_login.doctor.doctor.schedule.CalenderUtlis.monthYearFromDate;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.app_login.R;
import com.example.app_login.doctor.doctor.schedule.CalendarAdapter;
import com.example.app_login.doctor.doctor.schedule.CalenderUtlis;
import com.example.app_login.patient.patient.HistoryOfVitalsPatient.HistorypActivity;
import com.example.app_login.patient.patient.PatientHomeVitals.HomepActivity;
import com.example.app_login.patient.patient.finetuned_model.ChatpActivity;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalenderpActivity extends AppCompatActivity implements CalendarAdapter.onItemListener  {

    private MeowBottomNavigation bottomNavigationp;
    private TextView monthYearTextp;
    Button select;
    private RecyclerView calenderRecyclerViewp;
    String ID_P;
    private String JSON_STRING, time = "empty";
    String ID_D;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8, checked;

    ArrayList<String> arr_id = new ArrayList<>(),arr_date = new ArrayList<>(),arr_time = new ArrayList<>();
    ArrayList<RadioButton> arr_R_B = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calenderp);

        // page title
        getSupportActionBar().setTitle("Calender");

        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        ID_D = sharedPref.getString("doctor_id", "");
        ID_P= sharedPref.getString("patient_id", "");

        // to show back button
       //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //actiobar color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bijcolor2)));



//connection
//       getJSON();
//        try {
//            get_dates_times();
//        }catch (Exception e) {
//           //   Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
//        }

//////////////////////////////////////////////////////////////
        //Calender

        // function call for declaration parameters (calender)
        initWidgets();
        //make selectedDate from Calender Utlis class LocalDate.now
        CalenderUtlis.selectedDate = LocalDate.now();

        // function call for set Month(calender)
        setMonthView();


////////////////////////////////////////////////////////////

        //bottomNavigation
        bottomNavigationp=findViewById(R.id.bottomNavigationp);
        bottomNavigationp.show(2,true);

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
                        map(new View(CalenderpActivity.this));
                        break;

                    case 2:
                        break;

                    case 3:
                        startActivity(new Intent(CalenderpActivity.this, HomepActivity.class));
                        break;

                    case 4:
                        startActivity(new Intent(CalenderpActivity.this, HistorypActivity.class));
                        break;

                    case 5:
                        startActivity(new Intent(CalenderpActivity.this, ChatpActivity.class));
                        break;

                }


            }
        });

         ////////////////////////////////////////////////////////////////
        //Show icons clear and bigger
        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 1:
                        map(new View(CalenderpActivity.this));
                        break;

                }

            }
        });


        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 2:
                        break;

                }

            }
        });



        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 3:
                        startActivity(new Intent(CalenderpActivity.this, HomepActivity.class));
                        break;

                }

            }
        });



        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 4:
                        startActivity(new Intent(CalenderpActivity.this, HistorypActivity.class));
                        break;

                }

            }
        });



        bottomNavigationp.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                switch (item.getId()){
                    case 5:
                        startActivity(new Intent(CalenderpActivity.this, ChatpActivity.class));
                        break;

                }

            }
        });



    }


//map function
    public void map(View view) {

        Intent in=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Nearest+Hospital"));
        startActivity(in);
    }

 ///////////////////////////////////////////////////////////////////////////////////////////////////////////
//    calender functions
//  set month function
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {

        monthYearTextp.setText(monthYearFromDate(CalenderUtlis.selectedDate));
        ArrayList<LocalDate> daysInMonth= daysInMonthArray(CalenderUtlis.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, (CalendarAdapter.onItemListener) this);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),7);
        calenderRecyclerViewp.setLayoutManager(layoutManager);
        calenderRecyclerViewp.setAdapter(calendarAdapter);
    }

    private void initWidgets() {

        calenderRecyclerViewp=findViewById(R.id.calenderRecyclerViewp);
        monthYearTextp=findViewById(R.id.monthYearTVp);
    }


    //to go previous Month
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthActionP(View view)
    {
        CalenderUtlis.selectedDate= CalenderUtlis.selectedDate.minusMonths(1);
        setMonthView();

    }


    //to go next Month
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthActionP(View view)
    {
        CalenderUtlis.selectedDate= CalenderUtlis.selectedDate.plusMonths(1);
        setMonthView();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date) {
        if(date!=null)
        {
            CalenderUtlis.selectedDate=date;
            setMonthView();
            String message = "Selected Date " + date + " " + monthYearFromDate(CalenderUtlis.selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            movementFun();

        }
    }
    private void movementFun() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout_calender_patient);
        //declaration layouts
        rg = findViewById(R.id.select_r_g);
        select=findViewById(R.id.button);


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalenderpActivity.this,HomepActivity.class));

            }});

//
//        select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                int id = rg.getCheckedRadioButtonId();
//                checked = findViewById(id);
//                time = checked.getText().toString();
//              //  String date_s = date.toString();
//
//              if(time.equals("empty"))
//              {
//                 Toast.makeText(CalenderpActivity.this, "Choose a Time", Toast.LENGTH_SHORT).show();
//              }else {
//                 //setmeet(time, date_s, ID_D, ID_P);
//
//
//              }
//            }
//        });

        //declaration canel button
        ImageView cancelbutton = dialog.findViewById(R.id.cancelbutton);
        //movement&dialog message

        cancelbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////

//connection
//    private void get_dates_times() {
//            JSONObject jsonObject = null;
//            try {
//                jsonObject = new JSONObject(JSON_STRING);
//                JSONArray result = jsonObject.getJSONArray("A");
//                for(int i = 0; i<result.length(); i++){
//                    JSONObject jo = result.getJSONObject(i);
//
//                    // to get only this patient's ID's data
//                    String ID_back = jo.getString("doctor_id");
//                    if(ID_back.equals(ID_D)){
//                        arr_id.set(i, jo.getString("id"));
//                        arr_date.set(i, jo.getString("date"));
//                        arr_time.set(i, jo.getString("time"));
//                    }}
//
//            } catch (JSONException e) {
//                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
//            }
//
//
//    }
//
//    private void getJSON(){
//        class GetJSON extends AsyncTask<Void,Void,String> {
//
//            ProgressDialog loading;
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(CalenderpActivity.this,"Fetching Data","Wait...",false,false);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                JSON_STRING = s;
//                get_dates_times();
//            }
//
//            @Override
//            protected String doInBackground(Void... v) {
//                RequestHandler rh = new RequestHandler();
//                String s = rh.sendGetRequestParam("http://192.168.1.91/connection/get_dates.php");
//                System.out.println("result in backgrpound ----------------->  "+s);
//                return s;
//            }
//        }
//        GetJSON gj = new GetJSON();
//        gj.execute();
//    }
//
//
//
//
//    private void setmeet(String time, String date, String ID_D, String ID_P) {
//
//        ProgressDialog progressDialog=new ProgressDialog(CalenderpActivity.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminate(false);
//        progressDialog.setTitle("Scheduling New Meeting");
//        progressDialog.show();
//        String url="http://192.168.1.91/connection/set_meet.php";
//        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if(response.equals("successfully Scheduled")){
//                    progressDialog.dismiss();
//                    Toast.makeText(CalenderpActivity.this, response, Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(CalenderpActivity.this, HomepActivity.class));
//                    finish();
//                }
//                else {
//                    progressDialog.dismiss();
//                    Toast.makeText(CalenderpActivity.this, response, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressDialog.dismiss();
//                Toast.makeText(CalenderpActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> param = new HashMap<>();
//                String ID = "152";
//           /* for(int i=0; i< arr_date.size(); i++) {
//                if (arr_date.get(i) == date && arr_time.get(i) == time){
//                    ID = arr_id.get(i);
//                }
//            }*/
//                param.put("patient_id", ID_P);
//                param.put("doctor_id", ID_D);
//                param.put("work_days_id", ID);
//                return param;
//            }
//        };
//
//        /* time out and retry policy ..*/{
//            request.setRetryPolicy(new DefaultRetryPolicy(9000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//            MySingleton.getInstance(CalenderpActivity.this).addToRequestQueue(request);
//        }}
}