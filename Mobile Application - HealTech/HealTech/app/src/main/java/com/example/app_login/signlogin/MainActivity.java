package com.example.app_login.signlogin;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.app_login.MySingleton;
import com.example.app_login.R;
import com.example.app_login.doctor.doctor.HomeActivity;
import com.example.app_login.patient.patient.PatientHomeVitals.HomepActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView button_forgot_password, button_log_in, button_sign_up, movebutton,mvpbutton ;

    EditText email,password;
    String email_text,password_text;
    String pId;
    String dId;

//    android:background="@drawable/pop_bg"

    CheckBox loginState;
    int doctor_or_patient;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

    /*    // those patient / doctor buttons
        mvpbutton=findViewById(R.id.mvpbutton);
        movebutton=findViewById(R.id.mvbutton);*/

        email=findViewById(R.id.Login_email);
        password=findViewById(R.id.Login_password);
        button_log_in=findViewById(R.id.Login_btn);
        button_sign_up=findViewById(R.id.signup_btn);
        button_forgot_password = (TextView) findViewById(R.id.forgot_password_btn);

     /*   mvpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomepActivity.class));
            }
        });
        movebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
*/
        // forgot password button
        button_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, F_P.class));
            }
        });
        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PopUp.class));
            }
        });
        button_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_text = email.getText().toString();
                password_text = password.getText().toString();

                if(TextUtils.isEmpty(email_text)|| TextUtils.isEmpty(password_text)){
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    login(email_text,password_text);
                }
            }

        });
    }

    @Override
    public void onBackPressed(){
        System.exit(0);
    }

    private void login(String email, String password) {
        //System.out.println("llllllllllllllllllllllllllllllllllllllll");
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Logging In");
        progressDialog.show();
        String url = "http://192.168.1.91/connection/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("response"+" "+ response);

                if(response.contains("P-") || response.contains("D-")){
                    // differentiate between doctor and patient UI
                    // patient
                    if (response.contains("P-")) {
                        // do not know yet how to keep logged in. Nothing is working
                        //  if(loginState.isChecked()){
                        //}
                        //   doctor_or_patient = 1;
                        pId=response.substring(2);
                        //Toast.makeText(MainActivity.this, "patient -> "+pId, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        //  startActivity(new Intent(MainActivity.this, HomepActivity.class));
                        Intent intent = new Intent(MainActivity.this, HomepActivity.class);
//                        //patient id
                        intent.putExtra("P_ID", pId);
                        startActivity(intent);

                        editor.putString("patient_id", pId);
                        editor.commit();
                    }
                    // doctor
                    else if (response.contains("D-")) {
                        //  doctor_or_patient = 0;
                        dId=response.substring(2);
                      //  Toast.makeText(MainActivity.this, "Doctor -> "+dId, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        //System.out.println("docccccccccccccccccccccccccccccccccc");
                        //startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        //doc id
                       // Toast.makeText(MainActivity.this, "main ->"+dId , Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                        editor.putString("doctor_id", dId);
                        editor.commit();



                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.toString() , Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();

                param.put("email", email);
                param.put("password", password);
                return param;
            }
        };

        /* time out and retry policy ..*/ {
            request.setRetryPolicy(new DefaultRetryPolicy(9000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
        }
    }
}