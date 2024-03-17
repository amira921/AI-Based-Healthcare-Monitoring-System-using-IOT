package com.example.app_login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class MyBroadcastReceiver extends BroadcastReceiver {

    private TextView textView;

    public MyBroadcastReceiver(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String newData = intent.getStringExtra("data");
        textView.setText(newData);
    }
}