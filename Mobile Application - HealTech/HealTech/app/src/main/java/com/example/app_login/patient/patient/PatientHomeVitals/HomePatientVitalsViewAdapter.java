package com.example.app_login.patient.patient.PatientHomeVitals;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app_login.R;
import com.example.app_login.patient.patient.PatientHomeVitals.HomePatientVitalsData;

import java.util.ArrayList;

public class HomePatientVitalsViewAdapter extends ArrayAdapter<HomePatientVitalsData> {

    public HomePatientVitalsViewAdapter(@NonNull Context context, ArrayList<HomePatientVitalsData> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.home_patient_vitals_listraw, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        HomePatientVitalsData homePatientData = getItem(position);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView H_bloodPressure_value = currentItemView.findViewById(R.id.H_bloodPressure_value);
        H_bloodPressure_value.setText(String.valueOf(homePatientData.getHpressure()) );

        // then according to the position of the view assign the desired TextView 3 for the same
        TextView p_oxygen_value = currentItemView.findViewById(R.id.H_oxgen_value);
        p_oxygen_value.setText(String.valueOf(homePatientData.getHoxgen()));

        // then according to the position of the view assign the desired TextView 3 for the same
        TextView p_temperature_value = currentItemView.findViewById(R.id.H_temp_value);
        p_temperature_value.setText(String.valueOf(homePatientData.getHtemp()));

        // then according to the position of the view assign the desired TextView 3 for the same
        TextView p_heartrate_value = currentItemView.findViewById(R.id.H_heartrate_value);
        p_heartrate_value.setText(String.valueOf(homePatientData.getHheartrate()));

        // then return the recyclable view
        return currentItemView;

    }


}
