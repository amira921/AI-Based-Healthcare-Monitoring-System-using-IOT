package com.example.app_login.patient.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.app_login.MySingleton;
import com.example.app_login.R;
import com.example.app_login.patient.patient.PatientHomeVitals.HomepActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SymptomsActivity extends AppCompatActivity{

    String ID;
    ImageView heartburn,stomachUlcer,vomiting ,nausea,diarrhea,incontinence,bleeding,bloating,
            constipation,sorethroat,headache,sneezing,stuffynose,cough,breathlessness,dizz,bodyaches,skinirritation;

    int B_heartburn=0,B_stomachUlcer=0,B_vomiting=0 ,B_nausea=0,B_diarrhea=0,B_incontinence=0,B_bleeding=0,B_bloating=0,
            B_constipation=0,B_sorethroat=0,B_headache=0,B_sneezing=0,B_stuffynose=0,B_cough=0,B_breathlessness=0,B_dizz=0,B_bodyaches=0,B_skinirritation=0;


    ArrayList<View> views=new ArrayList<>();
    ArrayList<String> descriptions=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_symptoms);

        // page title
        getSupportActionBar().setTitle("Symptoms");

        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        ID= sharedPref.getString("patient_id", "");

        // to show back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Teal2)));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
//        select symptom action(circle)
        heartburn=findViewById(R.id.heartburn);
        heartburn.setContentDescription("heart-burn");
        heartburn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_heartburn=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_heartburn=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        stomachUlcer=findViewById(R.id.stomachUlcer);
        stomachUlcer.setContentDescription("stomach-ulcer");
        stomachUlcer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_stomachUlcer=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_stomachUlcer=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        vomiting=findViewById(R.id.vomiting);
        vomiting.setContentDescription("vomiting");
        vomiting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_vomiting=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_vomiting=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        nausea=findViewById(R.id.nausea);
        nausea.setContentDescription("nausea");
        nausea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_nausea=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_nausea=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        diarrhea=findViewById(R.id.diarrhea);
        diarrhea.setContentDescription("diarrhea");
        diarrhea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_diarrhea=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_diarrhea=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        incontinence=findViewById(R.id.incontinence);
        incontinence.setContentDescription("incontinence");
        incontinence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_incontinence=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_incontinence=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        bleeding=findViewById(R.id.bleeding);
        bleeding.setContentDescription("bleeding");
        bleeding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_bleeding=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_bleeding=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        bloating=findViewById(R.id.bloating);
        bloating.setContentDescription("bloating");
        bloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_bloating=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_bloating=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        constipation=findViewById(R.id.constipation);
        constipation.setContentDescription("constipation");
        constipation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_constipation=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_constipation=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        sorethroat=findViewById(R.id.sorethroat);
        sorethroat.setContentDescription("sore-throat");
        sorethroat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_sorethroat=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_sorethroat=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        headache=findViewById(R.id.headache);
        headache.setContentDescription("headache");
        headache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_headache=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_headache=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        sneezing=findViewById(R.id.sneezing);
        sneezing.setContentDescription("sneezing");
        sneezing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_sneezing=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_sneezing=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        stuffynose=findViewById(R.id.stuffynose);
        stuffynose.setContentDescription("stuffy-nose");
        stuffynose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_stuffynose=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_stuffynose=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        cough=findViewById(R.id.cough);
        cough.setContentDescription("cough");
        cough.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_cough=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_cough=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        breathlessness=findViewById(R.id.breathlessness);
        breathlessness.setContentDescription("breathlessness");
        breathlessness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_breathlessness=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_breathlessness=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        dizz=findViewById(R.id.dizz);
        dizz.setContentDescription("dizziness");
        dizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_dizz=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_dizz=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        bodyaches=findViewById(R.id.bodyaches);
        bodyaches.setContentDescription("body-ahes");
        bodyaches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_bodyaches=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_bodyaches=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

        skinirritation=findViewById(R.id.skinirritation);
        skinirritation.setContentDescription("skin-irritation");
        skinirritation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (views.contains(view)){
                    view.setBackgroundResource(R.drawable.ovalbutton);
                    view.setSelected(false);
                    B_skinirritation=0;
                    views.remove(view);
                    //Toast.makeText(this, "unselected", Toast.LENGTH_SHORT).show();
                }else{
                    views.add(view);
                    view.setBackgroundResource(R.drawable.selectedimg);
                    view.setSelected(true);
                    B_skinirritation=1;
                    // Toast.makeText(this, "selected  "+view.getContentDescription(), Toast.LENGTH_SHORT).show();
                }                  }
        });

    }

///////////////////////////////////////////
    //listview (menu buttoon done)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.donesym,menu);
        return true;
    }

    //selected checkboxs items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id =item.getItemId();
        if(id== R.id.donesym)
        {
            if(views.isEmpty()){
                Toast.makeText(this, "you should select your symptoms first", Toast.LENGTH_SHORT).show();
            }else{
                add_update_symptoms(ID,B_heartburn,B_stomachUlcer,B_vomiting,B_nausea,B_diarrhea,B_incontinence,B_bleeding,B_bloating,B_constipation,B_sorethroat,B_headache,B_sneezing,B_stuffynose,B_cough,B_breathlessness,B_dizz,B_bodyaches,B_skinirritation);
            }

        }
        return super.onOptionsItemSelected(item);
    }
/////////////////////////////////////////////////////////////////
//    connection
    private void add_update_symptoms( String ID, int B_heartburn,int B_stomachUlcer,int B_vomiting ,int B_nausea,int B_diarrhea,int B_incontinence,int B_bleeding,int B_bloating,int  B_constipation,int B_sorethroat,int B_headache,int B_sneezing,int B_stuffynose,int B_cough,int B_breathlessness,int B_dizz,int B_bodyaches,int B_skinirritation){

        ProgressDialog progressDialog=new ProgressDialog(SymptomsActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Registering New Account");
        progressDialog.show();
        String url="http://192.168.1.91/connection/Add_Symptoms.php";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate= sdf.format(new Date());
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("successfully Added") || response.equals("successfully updated")){
                    progressDialog.dismiss();
                    Toast.makeText(SymptomsActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SymptomsActivity.this, HomepActivity.class));
                    finish();
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(SymptomsActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SymptomsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();

                param.put("patient_id",ID);
                param.put("heart_burn", String.valueOf(B_heartburn));
                param.put("stomach_ulcer", String.valueOf(B_stomachUlcer));
                param.put("vomiting", String.valueOf(B_vomiting));
                param.put("nausea", String.valueOf(B_nausea));
                param.put("diarrhea", String.valueOf(B_diarrhea));
                param.put("incontinence", String.valueOf(B_incontinence));
                param.put("bleeding", String.valueOf(B_bleeding));
                param.put("bloating", String.valueOf(B_bloating));
                param.put("constipation", String.valueOf(B_constipation));
                param.put("sore_throat", String.valueOf(B_sorethroat));
                param.put("headache", String.valueOf(B_headache));
                param.put("sneezing", String.valueOf(B_sneezing));
                param.put("stuffy_nose", String.valueOf(B_stuffynose));
                param.put("cough", String.valueOf(B_cough));
                param.put("breathlessness", String.valueOf(B_breathlessness));
                param.put("dizziness", String.valueOf(B_dizz));
                param.put("body_ahes", String.valueOf(B_bodyaches));
                param.put("skin_irritation", String.valueOf(B_skinirritation));
                param.put("date", currentDate);
                return param;
            }
        };

        /* time out and retry policy ..*/{
            request.setRetryPolicy(new DefaultRetryPolicy(9000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getInstance(SymptomsActivity.this).addToRequestQueue(request);
        }
    }
}