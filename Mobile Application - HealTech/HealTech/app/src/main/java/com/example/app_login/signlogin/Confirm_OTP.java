package com.example.app_login.signlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_login.R;

public class Confirm_OTP extends AppCompatActivity {
    Button button;
    EditText num_otp1,num_otp2,num_otp3,num_otp4;

    String Entered_OTP1,Entered_OTP2,Entered_OTP3,Entered_OTP4,Full_OTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_otp);

        Intent intent = getIntent();
        String OTP = intent.getStringExtra("otp");

        num_otp1 = findViewById(R.id.N1_OTP);
        num_otp2 = findViewById(R.id.N2_OTP);
        num_otp3 = findViewById(R.id.N3_OTP);
        num_otp4 = findViewById(R.id.N4_OTP);

        button =  findViewById(R.id.Submit_OTP_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entered_OTP1 = num_otp1.getText().toString();
                Entered_OTP2 = num_otp2.getText().toString();
                Entered_OTP3 = num_otp3.getText().toString();
                Entered_OTP4 = num_otp4.getText().toString();
                Full_OTP = Entered_OTP1+Entered_OTP2+Entered_OTP3+Entered_OTP4;
                if(Entered_OTP1.isEmpty() || Entered_OTP2.isEmpty() || Entered_OTP3.isEmpty() || Entered_OTP4.isEmpty()){
                    Toast.makeText(Confirm_OTP.this, "OTP is not Entered", Toast.LENGTH_SHORT).show();
                }
                else if(Full_OTP.equals(OTP)){
                    Toast.makeText(Confirm_OTP.this, "Confirmed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Confirm_OTP.this,NewPassword.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Confirm_OTP.this, "OTP is Not Correct", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Confirm_OTP.this,F_P.class);
                    startActivity(intent);
                }
            }
        });
    }
}
