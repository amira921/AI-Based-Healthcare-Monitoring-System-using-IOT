package com.example.app_login.patient.patient.HistoryOfVitalsPatient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app_login.R;
import com.example.app_login.patient.patient.HistoryOfVitalsPatient.HisPatientData;

import java.util.ArrayList;

public class HisPatientViewAdapter extends ArrayAdapter<HisPatientData> {
    public HisPatientViewAdapter(@NonNull Context context, ArrayList<HisPatientData> arrayList) {

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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.his_patient_list_raw, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        HisPatientData pData = getItem(position);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView p_bloodpressure_value = currentItemView.findViewById(R.id.p_bloodpressure_value);
        p_bloodpressure_value.setText(String.valueOf(pData.getBpressure()) );

        // then according to the position of the view assign the desired TextView 3 for the same
        TextView p_oxygen_value = currentItemView.findViewById(R.id.p_oxygen_value);
        p_oxygen_value.setText(String.valueOf(pData.getPoxgen()));

        // then according to the position of the view assign the desired TextView 3 for the same
        TextView p_temperature_value = currentItemView.findViewById(R.id.p_temperature_value);
        p_temperature_value.setText(String.valueOf(pData.getPtemp()));

        // then according to the position of the view assign the desired TextView 3 for the same
        TextView p_heartrate_value = currentItemView.findViewById(R.id.p_heartrate_value);
        p_heartrate_value.setText(String.valueOf(pData.getPheartrate()));

        // then according to the position of the view assign the desired TextView 3 for the same
        TextView p_time_value= currentItemView.findViewById(R.id.p_time_value);
        p_time_value.setText(String.valueOf(pData.getTime()));

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView p_date_value = currentItemView.findViewById(R.id.p_date_value);
        p_date_value.setText(String.valueOf(pData.getDate()));
        // then return the recyclable view
        return currentItemView;

    }

}
