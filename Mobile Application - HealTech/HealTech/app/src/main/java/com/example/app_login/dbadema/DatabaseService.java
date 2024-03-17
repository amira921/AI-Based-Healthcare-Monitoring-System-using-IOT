package com.example.app_login.dbadema;

import android.app.IntentService;
import android.content.Intent;

public class DatabaseService extends IntentService {
    public DatabaseService() {
        super("DatabaseService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Code to retrieve data from PHPMyAdmin database
        // and update local database or UI
    }
}