package com.example.app_login.doctor.doctor.HisTabelID;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app_login.R;
import com.example.app_login.doctor.doctor.HisTabelID.HisIDdata;

import java.util.ArrayList;

public class HisIDViewAdapter extends ArrayAdapter<HisIDdata> {

    public HisIDViewAdapter(@NonNull Context context,  ArrayList<HisIDdata> arrayList) {
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.his_id_list_row, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        HisIDdata hData = getItem(position);
        //list row
        // then according to the position of the view assign the desired TextView 1 for the same
        TextView idh_value = currentItemView.findViewById(R.id.idh_value);
        idh_value.setText(String.valueOf(hData.getId()) );

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView nameh_value = currentItemView.findViewById(R.id.nameh_value);
        nameh_value.setText(String.valueOf(hData.getName())+"  "+String.valueOf(hData.getSname()) );

        // then return the recyclable view
        return currentItemView;

    }
}
