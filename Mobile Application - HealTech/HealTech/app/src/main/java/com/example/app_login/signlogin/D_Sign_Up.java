package com.example.app_login.signlogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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

public class D_Sign_Up extends AppCompatActivity {

    private Button sign_up_btn;
    private EditText F_name_text,S_name_text,email_text,medical_id_text,hospital_or_clinic_id_text,password_text,confirm_password_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_d_sign_up);

        sign_up_btn = findViewById(R.id.signup_btn_D);
        F_name_text = findViewById(R.id.SignUp_F_name_box);
        S_name_text = findViewById(R.id.SignUp_S_name_box);
        email_text = findViewById(R.id.SignUp_email);
        medical_id_text = findViewById(R.id.SignUp_Medical_ID);
        hospital_or_clinic_id_text = findViewById(R.id.SignUp_hospital_or_clinic_ID);
        password_text = findViewById(R.id.SignUp_pass);
        confirm_password_text = findViewById(R.id.SignUp_Confirm_pass);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String F_name = F_name_text.getText().toString();
                String S_name = S_name_text.getText().toString();
                String email = email_text.getText().toString();
                String password = password_text.getText().toString();
                String confirm_password = confirm_password_text.getText().toString();
                String medical_id = medical_id_text.getText().toString();
                String hospital_or_clinic_id = hospital_or_clinic_id_text.getText().toString();


                if(! password.equals(confirm_password)) {
                    Toast.makeText(D_Sign_Up.this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(F_name) || TextUtils.isEmpty(F_name) || TextUtils.isEmpty(email)|| TextUtils.isEmpty(medical_id) || TextUtils.isEmpty(hospital_or_clinic_id) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm_password)){
                    Toast.makeText(D_Sign_Up.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    registernewaccount(F_name,S_name,email,medical_id,hospital_or_clinic_id,password);
                }

            }
        });


    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(D_Sign_Up.this, MainActivity.class));
    }

    private void registernewaccount(String F_name_text, String S_name_text, String email_text, String medical_id_text, String hospital_or_clinic_id_text, String password_text){

        Toast.makeText(this, "in register", Toast.LENGTH_SHORT).show();
        ProgressDialog progressDialog=new ProgressDialog(D_Sign_Up.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Registering New Account");
        progressDialog.show();
        String url="http://192.168.1.91/connection/D_register.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("successfully Registered")){
                    progressDialog.dismiss();
                    Toast.makeText(D_Sign_Up.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(D_Sign_Up.this,MainActivity.class));
                    finish();
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(D_Sign_Up.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(D_Sign_Up.this, error.toString(), Toast.LENGTH_LONG).show();
              //  System.out.println("erorrrrrrrrrrrrrrrrrr");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();

                param.put("medical_id", medical_id_text);
                param.put("first_name", F_name_text);
                param.put("second_name", S_name_text);
                param.put("email", email_text);
                param.put("hospital_clinic_id", hospital_or_clinic_id_text);
                param.put("password", password_text);
                return param;
            }
        };

        /* time out and retry policy ..*/{
            request.setRetryPolicy(new DefaultRetryPolicy(9000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getInstance(D_Sign_Up.this).addToRequestQueue(request);
        }
    }
}