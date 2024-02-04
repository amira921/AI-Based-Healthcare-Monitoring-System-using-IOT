package com.example.app_login.doctor.doctor.schedule;

import static com.example.app_login.doctor.doctor.schedule.CalenderUtlis.daysInMonthArray;
import static com.example.app_login.doctor.doctor.schedule.CalenderUtlis.monthYearFromDate;

import com.example.app_login.doctor.doctor.FScheduleDoc.FinalScheduleDoc;
import com.example.app_login.doctor.doctor.HomeActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_login.R;


import java.time.LocalDate;
import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity implements CalendarAdapter.onItemListener {

    private TextView monthYearText;
    private RecyclerView calenderRecyclerView;
    String dId;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        // page title
        getSupportActionBar().setTitle("Create Schedule");
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //actiobar color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Teal2)));


     /////////////////////////////////////////////////////////////////////////////////////////////////////
        // function call for declaration parameters (calender)
        initWidgets();
        //make selectedDate from Calender Utlis class LocalDate.now
        CalenderUtlis.selectedDate =LocalDate.now();
        // function call for set Month(calender)
        setMonthView();


///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

///////////////////////////////////Functions///////////////////////////////////////////////////////////////////


    //calender id connect
    private void initWidgets()
    {
        calenderRecyclerView=findViewById(R.id.calenderRecyclerView);
        monthYearText=findViewById(R.id.monthYearTV);
    }

    //////////////////
    //calender
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView()
    {

        monthYearText.setText(monthYearFromDate(CalenderUtlis.selectedDate));
        ArrayList<LocalDate> daysInMonth= daysInMonthArray(CalenderUtlis.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),7);
        calenderRecyclerView.setLayoutManager(layoutManager);
        calenderRecyclerView.setAdapter(calendarAdapter);
    }

    //////////////////////////
    //selected a specific date
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date)
    {

        if(date!=null)
        {
            CalenderUtlis.selectedDate=date;
            setMonthView();

            String message = "Selected Date " + date + " " + monthYearFromDate(CalenderUtlis.selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
            dId = sharedPref.getString("doctor_id", "");

            Intent intent = new Intent(ScheduleActivity.this, DailyScheduleActivity.class);
            //doc id
           // Toast.makeText(this, ""+dId, Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }


        /*   if(!dayText.equals(""))
        {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(CalenderUtlis.selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }*/

    }

    /////////////////////////
    //to go previous Month
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view)
    {
        CalenderUtlis.selectedDate= CalenderUtlis.selectedDate.minusMonths(1);
        setMonthView();

    }

    ////////////////////////
    //to go next Month
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view)
    {
        CalenderUtlis.selectedDate= CalenderUtlis.selectedDate.plusMonths(1);
        setMonthView();

    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////

    //menu for month ,week and day

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.month_week_day,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.month:
                return true;
            case R.id.day:
                startActivity(new Intent(ScheduleActivity.this, DailyScheduleActivity.class));
                return true;

            case R.id.week:
                startActivity(new Intent(ScheduleActivity.this,WeekScheduleActivity.class));
                return true;

            case R.id.fschedule:
                startActivity(new Intent(ScheduleActivity.this, FinalScheduleDoc.class));
                return true;



        }
        return super.onOptionsItemSelected(item);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
}