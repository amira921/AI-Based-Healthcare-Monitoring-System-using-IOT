package com.example.app_login.signlogin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.app_login.MySingleton;
import com.example.app_login.R;

import java.util.HashMap;
import java.util.Map;

public class F_P extends AppCompatActivity {
    Button confirm_details;
    EditText email, hospital_doctor_id;

    String email_text,hospital_doctor_id_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.forgot_email);
        hospital_doctor_id = findViewById(R.id.doctor_id_forgot_password);
        confirm_details = findViewById(R.id.Submit_pass_btn);

        Random random = new Random();
        String OTP = String.format("%04d", random.nextInt(10000));

        email_text = email.getText().toString();
        hospital_doctor_id_text = hospital_doctor_id.getText().toString();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My OTP", "My OTP", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        confirm_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                confirm(email_text,hospital_doctor_id_text, OTP);

            }
        });
    }
    private void confirm(String email, String doctor_id_or_hospital, String OTP) {
        ProgressDialog progressDialog = new ProgressDialog(F_P.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Confirming Information");
        progressDialog.show();
        String url = "http://192.168.1.91/connection/confirm_info_for_new_password.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("Confirmed")){
                    progressDialog.dismiss();
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(F_P.this, "My OTP");
                    builder.setContentTitle("Your OTP is here");
                    builder.setContentText("Please Enter " + OTP);
                    builder.setSmallIcon(R.drawable.app_icon);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(F_P.this);
                    managerCompat.notify(1,builder.build());
                    Intent intent = new Intent(F_P.this, Confirm_OTP.class);
                    intent.putExtra("otp", OTP);
                    startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(F_P.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(F_P.this, error.toString() , Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();

                param.put("email", email);
                param.put("doctor_id", doctor_id_or_hospital);
                param.put("hospital_clinic_id", doctor_id_or_hospital);
                return param;
            }
        };

        /* time out and retry policy ..*/ {
            request.setRetryPolicy(new DefaultRetryPolicy(9000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getInstance(F_P.this).addToRequestQueue(request);
        }
    }
}
