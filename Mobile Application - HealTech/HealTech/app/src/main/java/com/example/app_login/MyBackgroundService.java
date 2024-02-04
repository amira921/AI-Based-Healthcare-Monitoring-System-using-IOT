package com.example.app_login;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.logging.Handler;

/*public class MyBackgroundService extends Service {

    private Handler handler = new Handler();
    private Runnable runnable;
    private String lastData = "";



    @Override
    public void onCreate()
    {
        super.onCreate();
        // Define the runnable that will periodically check the database
        runnable = new Runnable()
        {
            @Override
            public void run()
            {
                // Check the database for new data
                String newData = fetchDataFromDatabase();
                // If new data is available, send a notification and update the activity
                if (!newData.equals(lastData))
                {
                    sendNotification(newData);
                    updateActivity(newData);
                    lastData = newData;
                }

                // Schedule the next check in 1 minute
                handler.postDelayed(this, 60 * 1000);
            }
        };
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        // Start the service
        handler.post(runnable);
        return START_STICKY;
    }



    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // Stop the service
        handler.removeCallbacks(runnable);
    }


    private String fetchDataFromDatabase()
    {
        // Connect to the database using PHP or some other server-side language
        // and retrieve the latest data
        // ...
        return latestData;
    }



    private void sendNotification(String newData)
    {
        // Create a notification with the new data and send it
        // ...
    }



    private void updateActivity(String newData)
    {
        // Broadcast an intent with the new data that the activity can receive
        Intent intent = new Intent("com.example.myapp.NEW_DATA");
        intent.putExtra("data", newData);
        sendBroadcast(intent);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }


}
*/
