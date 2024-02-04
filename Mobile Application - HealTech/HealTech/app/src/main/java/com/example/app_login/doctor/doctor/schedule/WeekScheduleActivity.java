package com.example.app_login.doctor.doctor.schedule;

import static com.example.app_login.doctor.doctor.schedule.CalenderUtlis.daysInWeekArray;
import static com.example.app_login.doctor.doctor.schedule.CalenderUtlis.monthYearFromDate;

import android.content.Intent;
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
import com.example.app_login.doctor.doctor.FScheduleDoc.FinalScheduleDoc;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekScheduleActivity extends AppCompatActivity implements CalendarAdapter.onItemListener {

    private TextView monthYearText;
    private RecyclerView calenderRecyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_schedule);

        // page title
        getSupportActionBar().setTitle("Weekly Schedule");
        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //actiobar color
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Teal2)));

    /////////////////////////////////////////////////////////////////////////////////////////////////////
        initWidgets();
        setWeekView();
    //////////////////////////////////////////////////////////
    }

////////////////////////////////////////////Functions///////////////////////////////////////////////
    private void initWidgets()
    {
        calenderRecyclerView=findViewById(R.id.calenderRecyclerView);
        monthYearText=findViewById(R.id.monthYearTV);
    }

///////////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWeekView()
    {

        monthYearText.setText(monthYearFromDate(CalenderUtlis.selectedDate));
        ArrayList<LocalDate> days= daysInWeekArray(CalenderUtlis.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days,this);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),7);
        calenderRecyclerView.setLayoutManager(layoutManager);
        calenderRecyclerView.setAdapter(calendarAdapter);
    }

/////////////////////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousWeekAction(View view)
    {
        CalenderUtlis.selectedDate = CalenderUtlis.selectedDate.minusWeeks(1);
        setWeekView();


    }
////////////////////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextWeekAction(View view)
    {
        CalenderUtlis.selectedDate = CalenderUtlis.selectedDate.plusWeeks(1);
        setWeekView();
    }
////////////////////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date)
    {

        CalenderUtlis.selectedDate=date;
        setWeekView();
        String message = "Selected Date " + date + " " + monthYearFromDate(CalenderUtlis.selectedDate);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        startActivity(new Intent(WeekScheduleActivity.this, DailyScheduleActivity.class));
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
                startActivity(new Intent(WeekScheduleActivity.this,ScheduleActivity.class));
                return true;
            case R.id.day:
                startActivity(new Intent(WeekScheduleActivity.this, DailyScheduleActivity.class));
                return true;

            case R.id.fschedule:
                startActivity(new Intent(WeekScheduleActivity.this, FinalScheduleDoc.class));
                return true;

            case R.id.week:
                return true;



        }
        return super.onOptionsItemSelected(item);
    }
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}


