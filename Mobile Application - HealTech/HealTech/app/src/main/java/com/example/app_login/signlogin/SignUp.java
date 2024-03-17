package com.example.app_login.signlogin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private Button sign_up_btn;
    private EditText F_name_text,S_name_text,email_text,national_id_text,emergancy_num_1_text,emergancy_num_2_text,password_text,confirm_password_text,gender_text,birthdate_text,doctor_id_text,weight_text,height_text,blood_type_text;
    private RadioGroup diabete_r_group,blood_pressure_r_group,liver_disease_r_group,pregnant_r_group;
    private RadioButton checked_diabetes,checked_blood_pressure,checked_liver_disease,checked_pregnant;

    private int checked_diabetes_id,checked_blood_pressure_id,checked_liver_disease_id,checked_pregnant_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sign_up_btn = findViewById(R.id.signup_btn);
        F_name_text = findViewById(R.id.SignUp_F_name_box);
        S_name_text = findViewById(R.id.SignUp_S_name_box);
        email_text = findViewById(R.id.SignUp_email);
        national_id_text = findViewById(R.id.SignUp_National_ID);
        emergancy_num_1_text = findViewById(R.id.SignUp_first_Emergency_Number);
        emergancy_num_2_text = findViewById(R.id.SignUp_second_Emergency_Number);
        password_text = findViewById(R.id.SignUp_pass);
        confirm_password_text = findViewById(R.id.SignUp_Confirm_pass);
        gender_text = findViewById(R.id.SignUp_Gender);
        birthdate_text = findViewById(R.id.SignUp_Date);
        doctor_id_text = findViewById(R.id.SignUp_doctor_id);
        weight_text = findViewById(R.id.SignUp_Weight);
        height_text = findViewById(R.id.SignUp_Height);
        blood_type_text = findViewById(R.id.SignUp_BloodType);
        diabete_r_group = findViewById(R.id.diabetes_r_group);
        blood_pressure_r_group = findViewById(R.id.blood_pressure_r_group);
        liver_disease_r_group = findViewById(R.id.liver_r_group);
        pregnant_r_group = findViewById(R.id.preg_r_group);


        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String F_name = F_name_text.getText().toString();
                String S_name = S_name_text.getText().toString();
                String email = email_text.getText().toString();
                String national_id = national_id_text.getText().toString();
                String emergancy_num_1 = emergancy_num_1_text.getText().toString();
                String emergancy_num_2 = emergancy_num_2_text.getText().toString();
                String password = password_text.getText().toString();
                String confirm_password = confirm_password_text.getText().toString();
                String gender = gender_text.getText().toString();
                String birthdate = birthdate_text.getText().toString();
                String weight = weight_text.getText().toString();
                String height = height_text.getText().toString();
                String blood_type = blood_type_text.getText().toString();
                String doctor_id = doctor_id_text.getText().toString();
                String diabetes = "3";
                String blood_pressure = "3";
                String liver_disease = "3";
                String pregnant = "3";

                /*Radio Group get checked button id and set its value in its string variable */    {
                    checked_diabetes_id = diabete_r_group.getCheckedRadioButtonId();
                    checked_blood_pressure_id = blood_pressure_r_group.getCheckedRadioButtonId();
                    checked_liver_disease_id = liver_disease_r_group.getCheckedRadioButtonId();
                    checked_pregnant_id = pregnant_r_group.getCheckedRadioButtonId();

                    checked_diabetes = findViewById(checked_diabetes_id);
                    checked_blood_pressure = findViewById(checked_blood_pressure_id);
                    checked_liver_disease = findViewById(checked_liver_disease_id);
                    checked_pregnant = findViewById(checked_pregnant_id);

                    diabetes = checked_diabetes.getText().toString();
                    blood_pressure = checked_blood_pressure.getText().toString();
                    liver_disease = checked_liver_disease.getText().toString();
                    pregnant = checked_pregnant.getText().toString();
                }

               if(! password.equals(confirm_password)) {
                   Toast.makeText(SignUp.this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
               }
               else if(diabetes.equals("3") || blood_pressure.equals("3")  || liver_disease.equals("3")  || pregnant.equals("3") ){
                   Toast.makeText(SignUp.this, "All fields are required!", Toast.LENGTH_SHORT).show();
               }
                else if(TextUtils.isEmpty(F_name) || TextUtils.isEmpty(F_name) || TextUtils.isEmpty(email)|| TextUtils.isEmpty(national_id) || TextUtils.isEmpty(emergancy_num_1) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm_password) || TextUtils.isEmpty(gender) || TextUtils.isEmpty(birthdate) || TextUtils.isEmpty(weight) || TextUtils.isEmpty(height) || TextUtils.isEmpty(blood_type)){
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    registernewaccount(F_name,S_name,email,national_id,emergancy_num_1,emergancy_num_2,password,gender,birthdate,weight,height,blood_type,diabetes,blood_pressure,liver_disease,pregnant,doctor_id);
                }

            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(SignUp.this, MainActivity.class));
    }

    private void registernewaccount(String F_name_text, String S_name_text, String email_text, String national_id_text, String emergancy_num_1_text, String emergancy_num_2_text, String password_text, String gender_text, String birthdate_text, String weight_text, String height_text, String blood_type_text, String diabetes_text, String blood_pressure_text, String liver_disease_text, String pregnant_text, String doctor_id_text){

        ProgressDialog progressDialog=new ProgressDialog(SignUp.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Registering New Account");
        progressDialog.show();
        String url="http://192.168.1.91/connection/P_register.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("successfully Registered")){
                    progressDialog.dismiss();
                    Toast.makeText(SignUp.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this,MainActivity.class));
                    finish();
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(SignUp.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SignUp.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();

                param.put("national_id", national_id_text);
                param.put("first_name", F_name_text);
                param.put("second_name", S_name_text);
                param.put("email", email_text);
                param.put("first_emergancy_number", emergancy_num_1_text);
                param.put("second_emergancy_number", emergancy_num_2_text);
                param.put("password", password_text);
                param.put("gender", gender_text);
                param.put("birthdate", birthdate_text);
                param.put("weight", weight_text);
                param.put("height", height_text);
                param.put("blood_type", blood_type_text);
                param.put("blood_pressure",blood_pressure_text);
                param.put("pregnant",pregnant_text);
                param.put("liver",liver_disease_text);
                param.put("diabetes", diabetes_text);
                param.put("doctor_id",doctor_id_text);
                return param;
            }
        };

        /* time out and retry policy ..*/{
            request.setRetryPolicy(new DefaultRetryPolicy(9000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getInstance(SignUp.this).addToRequestQueue(request);
        }
    }
    }