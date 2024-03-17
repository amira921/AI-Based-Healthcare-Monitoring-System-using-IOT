package com.example.app_login.signlogin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class NewPassword extends AppCompatActivity {

    Button update;
    EditText newPassword,confirm_newPassword,ID_Password;
    String newPassword_t,confirm_newPassword_t,ID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        ID_Password = findViewById(R.id.mail_new_password);
        update = findViewById(R.id.Submit_new_pass_btn);
        newPassword = findViewById(R.id.new_password);

        confirm_newPassword = findViewById(R.id.confirm_new_password);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = ID_Password.getText().toString();
                newPassword_t = newPassword.getText().toString();
                confirm_newPassword_t = confirm_newPassword.getText().toString();
                if(! newPassword_t.equals(confirm_newPassword_t)) {
                    Toast.makeText(NewPassword.this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
                }
                else if(newPassword_t.isEmpty() || confirm_newPassword_t.isEmpty() || ID.isEmpty()){
                    Toast.makeText(NewPassword.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    New_Password(newPassword_t, ID);
                }
            }
        });
    }
    private void New_Password(String password, String ID) {
        ProgressDialog progressDialog = new ProgressDialog(NewPassword.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Confirming Information");
        progressDialog.show();
        String url = "http://192.168.1.91/connection/update_password.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("Updated")){
                    progressDialog.dismiss();
                    Intent intent = new Intent(NewPassword.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(NewPassword.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(NewPassword.this, error.toString() , Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();

                param.put("password", password);
                param.put("medical_id", ID);
                param.put("national_id", ID);
                return param;
            }
        };

        /* time out and retry policy ..*/ {
            request.setRetryPolicy(new DefaultRetryPolicy(9000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getInstance(NewPassword.this).addToRequestQueue(request);
        }
    }
}