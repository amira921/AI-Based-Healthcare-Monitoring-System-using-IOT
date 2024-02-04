package com.example.app_login.doctor.doctor.FScheduleDoc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app_login.R;
import com.example.app_login.doctor.doctor.FScheduleDoc.FScheduleDataDoc;

import java.util.ArrayList;


public class FScheduleViewAdapterDoc extends ArrayAdapter<FScheduleDataDoc>{

    public FScheduleViewAdapterDoc(@NonNull Context context, ArrayList<FScheduleDataDoc> arrayList) {

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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.fschedule_list_row, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        FScheduleDataDoc dfschData = getItem(position);


        // then according to the position of the view assign the desired TextView 1 for the same
        TextView dday_value = currentItemView.findViewById(R.id.day_valuef);
        dday_value.setText(String.valueOf(dfschData.getDday()) );

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView ddate_value = currentItemView.findViewById(R.id.date_valuef);
        ddate_value.setText(String.valueOf(dfschData.getDdate()));

        // then according to the position of the view assign the desired TextView 3 for the same
        TextView dhours_value = currentItemView.findViewById(R.id.hours_valuef);
        dhours_value.setText(String.valueOf(dfschData.getDhour()));

        // then return the recyclable view
        return currentItemView;

    }

}
