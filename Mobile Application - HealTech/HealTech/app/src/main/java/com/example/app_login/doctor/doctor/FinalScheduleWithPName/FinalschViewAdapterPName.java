package com.example.app_login.doctor.doctor.FinalScheduleWithPName;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app_login.R;

import java.util.ArrayList;

public class FinalschViewAdapterPName extends ArrayAdapter<FinalScheduledPNamedata> {

    public FinalschViewAdapterPName(@NonNull Context context, ArrayList<FinalScheduledPNamedata> arrayList) {

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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.fschedule_patient_list_raw, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        FinalScheduledPNamedata schData = getItem(position);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView day_value = currentItemView.findViewById(R.id.day_value);
        day_value.setText(String.valueOf(schData.getDay()) );

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView date_value = currentItemView.findViewById(R.id.date_value);
        date_value.setText(String.valueOf(schData.getDate()));

        // then according to the position of the view assign the desired TextView 3 for the same
        TextView hours_value = currentItemView.findViewById(R.id.hours_value);
        hours_value.setText(String.valueOf(schData.getHours()));

        // then according to the position of the view assign the desired TextView 4 for the same
        TextView patient_name_value = currentItemView.findViewById(R.id.patient_name_value);
        patient_name_value.setText(String.valueOf(schData.getpName() + " " +schData.getSpName()));/////////////////////////////////////

        // then return the recyclable view
        return currentItemView;

    }

}
