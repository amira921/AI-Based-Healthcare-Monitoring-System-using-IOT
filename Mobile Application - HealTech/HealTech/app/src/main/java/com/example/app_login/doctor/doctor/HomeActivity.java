package com.example.app_login.doctor.doctor;


import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.app_login.MyBackgroundService;
import com.example.app_login.MyBroadcastReceiver;
//import com.example.app_login.doctor.doctor.TextEmergancy.MyAdapter;
//import com.example.app_login.doctor.doctor.TextEmergancy.MyData;
import com.example.app_login.dbconnection.Config;
import com.example.app_login.dbconnection.RequestHandler;
import com.example.app_login.doctor.doctor.ChatGpt.ChatActivity;
import com.example.app_login.doctor.doctor.FinalScheduleWithPName.FinalScheduleActivityWthPName;
import com.example.app_login.doctor.doctor.Historydoctor.HistoryDActivity;
import com.example.app_login.doctor.doctor.Historydoctor.HistoryData;
import com.example.app_login.doctor.doctor.Historydoctor.HistroyViewAdapter;
import com.example.app_login.doctor.doctor.TextEmergancy.MyData;
import com.example.app_login.doctor.doctor.TextEmergancy.RecycleAdapter;
import com.example.app_login.signlogin.MainActivity;
import com.example.app_login.R;
import com.example.app_login.doctor.doctor.schedule.ScheduleActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;


public class HomeActivity extends AppCompatActivity   {



    private FloatingActionButton floatingbutton;
    private Button btn;
    DrawerLayout drawerLayout1;
    private ImageView menu;
    NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    ArrayList<MyData> data;
    String dId;
    private RecyclerView recyclerView;
    private String JSON_STRING;

    //check o database
    private MyBroadcastReceiver receiver;
    private IntentFilter intentFilter;
    private TextView textView;

    //for get
    private RecyclerView rvData;
///////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_home);

        //get doctor id
        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        dId = sharedPref.getString("doctor_id", "");

        //check on database
        // Start the background service
        //  Intent intent = new Intent(this, MyBackgroundService.class);
        //  startService(intent);

        // Register the broadcast receiver
        //   textView = findViewById(R.id.text_view);///////??????????????????????
        receiver = new MyBroadcastReceiver(textView);
        intentFilter = new IntentFilter("com.example.myapp.NEW_DATA");
        registerReceiver(receiver, intentFilter);
        ////////////////////////////////////////////////
        recyclerView = (RecyclerView) findViewById(R.id.rvData);
        //listView.setOnItemClickListener(this);
        getJSON();

        try {
            showPname();
        }catch (Exception e) {
            System.out.println("Exception main              -------->     :   "+e);
        }

       /* // Lookup the recyclerview in activity layout
         rvData = (RecyclerView) findViewById(R.id.rvData);
        // Initialize contacts
        data =new ArrayList<>();
        data.add(new MyData("Rada Karm"));
        data.add(new MyData("Jana Ahmed"));
        data.add(new MyData("Karem Adel"));
        // Create adapter passing in the sample user data
        RecycleAdapter adapter = new RecycleAdapter(data);
        // Attach the adapter to the recyclerview to populate items
        rvData.setAdapter(adapter);
        // Set layout manager to position the items
        rvData.setLayoutManager(new LinearLayoutManager(this));*/

      /*  //emergancy text arraylist
        List<MyData> dataList = new ArrayList<>();
        dataList.add(new MyData("Text 1a"));
        dataList.add(new MyData("Text 2a"));
        dataList.add(new MyData("Text 3a"));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(dataList));*/


/////////////////////////////////////////////////////////////////////////////////////////////

        //declartion img video , menu toolbar
        menu=findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                movementdocFun();
            }
        });

        //declaration Video button in bar
        ImageView videobutton= findViewById(R.id.video2);
        //move to Video Activity
        videobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(HomeActivity.this, VideoActivity.class));
                //showNotification();
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////
        //Floating button
        //Floating button declaration
        floatingbutton =findViewById(R.id.fab);

        //call function movement for dialog message and move to anthor activity
        floatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movementFun();
            }
        });
///////////////////////////////////////////////////////////////////////////////////////
        //button Navigation
        //declaration BottomNavigationView
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        //selected home item
        bottomNavigationView.setSelectedItemId(R.id.home);
        //switching between Activities
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId())
            {
                case R.id.home:
                    return true;
                case R.id.history:{
                    startActivity(new Intent(getApplicationContext() , HistoryAndSymptomsActivity.class));}
                return true;

                case R.id.calender:
                    startActivity(new Intent(getApplicationContext() , FinalScheduleActivityWthPName.class));
                    return true;

                case R.id.chat:
                    startActivity(new Intent(getApplicationContext() , ChatActivity.class));
                    return true;
            }

            return false;
        });
    }
    /////////////////////////////////////////////////////functions/////////////////////////////////////////////////
    //check on db
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the broadcast receiver
        unregisterReceiver(receiver);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //
//menu sheetbutton
// movement Function
// setting
    private void movementdocFun() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetdocsetting_layout);
        //declaration layouts
        //LinearLayout layoutpatientsetting = dialog.findViewById(R.id.layoutpatientsetting);
        // LinearLayout layoutpatientaccount = dialog.findViewById(R.id.layoutpatientaccount);
        //ImageView darkmoodbutton = dialog.findViewById(R.id.darkmoodbutton);
        //LinearLayout layoutpatientinvite = dialog.findViewById(R.id.layoutpatientinvited);

        LinearLayout layoutdoclogout = dialog.findViewById(R.id.layoutpatientlogoutd);
      /*  //movement&dialog message
        layoutpatientsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(HomepActivity.this, SettingpActivity.class));
                 }
        });

        layoutpatientaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(HomepActivity.this, AccountinfopActivity.class));
            }
              darkmoodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomepActivity.this, "Dark mood", Toast.LENGTH_SHORT).show();
             }
        });
        });*/

        layoutdoclogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(HomeActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });


    /*
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
*/
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    private void zoom(Context context) {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("zoomus://join?action=join&confno=73494462573&pwd=2kX8qc"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            try {
                startActivity(intent);
            } catch(Exception e) {
                Toast.makeText(this, e.toString() , Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //floating button
    // movement Function
    private void movementFun() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);
        //declaration layouts
        LinearLayout scheduleLayout = dialog.findViewById(R.id.layoutschedule);
        LinearLayout videoLayout = dialog.findViewById(R.id.layoutvideo);
        //declaration canel button
        ImageView cancelbutton = dialog.findViewById(R.id.cancelbutton);
        //movement&dialog message
        scheduleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(HomeActivity.this, "creating and editing schedule", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(HomeActivity.this, ScheduleActivity.class);
               // Toast.makeText(HomeActivity.this, "home ->"+dId , Toast.LENGTH_SHORT).show();
                //doc id
                intent.putExtra("D_ID", dId);
                startActivity(intent);
            }
        });
        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {   dialog.dismiss();
                zoom(HomeActivity.this);
                startActivity(new Intent(HomeActivity.this, VideoActivity.class));
            }
        });
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
/////////////////////////////////////////////////////////////////////////////////////////////////////

    //Notification Function
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showNotification()
    {
        Uri sound = Uri.parse("android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.sound);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(HomeActivity. this, "default_notification_channel_id" )
                .setSmallIcon(R.drawable. ic_launcher_foreground )
                .setContentTitle( "Test" )
                .setSound(sound)
                .setContentText( "Hello! This is my first pushnotification" ) ;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context. NOTIFICATION_SERVICE );
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes. CONTENT_TYPE_SONIFICATION )
                    .setUsage(AudioAttributes. USAGE_ALARM )
                    .build() ;
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new NotificationChannel( "NOTIFICATION_CHANNEL_ID" , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            notificationChannel.enableLights( true ) ;
            notificationChannel.setLightColor(Color. RED ) ;
            notificationChannel.enableVibration( true ) ;
            notificationChannel.setVibrationPattern( new long []{ 100 , 200 , 300 , 400 , 500 , 400 , 300 , 200 , 400 }) ;
            notificationChannel.setSound(sound , audioAttributes) ;
            mBuilder.setChannelId( "NOTIFICATION_CHANNEL_ID" ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;

        mNotificationManager.notify(( int ) System. currentTimeMillis () ,
                mBuilder.build()) ;
        Toast.makeText(this, "showeyyyyy", Toast.LENGTH_SHORT).show();
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void showPname()
    {
        JSONObject jsonObject = null;
        ArrayList<MyData> list = new ArrayList<>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);

                String fname = jo.getString(Config.TAG_P_NAME);
                String sname = jo.getString(Config.TAG_P_SNAME);
                MyData myData=new MyData(fname+" "+sname);
                list.add(myData);
                //System.out.println("list hash ------------>  "+list.get(i).getTemp()+" , "+list.get(i).getOxg()+" , "+list.get(i).getHrate()+" , "+list.get(i).getPres());
            }
            System.out.println("my data ---------------> "+list.toString());
        } catch (JSONException e) {
            System.out.println("Exception              -------->     :   "+e);
            e.printStackTrace();
        }

        RecycleAdapter recycleAdapter = new RecycleAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycleAdapter);
    }
    /////////////////////////////////////////////
    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(HomeActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showPname();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_TEXT,dId);
                System.out.println("result in backgrpound ----------------->  "+s);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


/////////////////////////////////////////////////////////////////////////////////////////////
}