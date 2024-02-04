package com.example.app_login.doctor.doctor.Historydoctor;

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

public class HistroyViewAdapter extends ArrayAdapter<HistoryData> {

    // invoke the suitable constructor of the ArrayAdapter class
    public HistroyViewAdapter(@NonNull Context context, ArrayList<HistoryData> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.listrow_his_doctor, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        HistoryData hData = getItem(position);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView temp = currentItemView.findViewById(R.id.temp);
        temp.setText(String.valueOf(hData.getTemp()) );

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView press = currentItemView.findViewById(R.id.press);
        press.setText(String.valueOf(hData.getPres()) );
        // then according to the position of the view assign the desired TextView 3 for the same
        TextView oxgen = currentItemView.findViewById(R.id.oxgen);
        oxgen.setText(String.valueOf(hData.getOxg()) );
        // then according to the position of the view assign the desired TextView 4 for the same
        TextView hrate = currentItemView.findViewById(R.id.hrate);
        hrate.setText(String.valueOf(hData.getHrate()) );
        // then according to the position of the view assign the desired TextView 5 for the same
        TextView htime = currentItemView.findViewById(R.id.htime);
        htime.setText(String.valueOf(hData.getHtime()) );
        // then according to the position of the view assign the desired TextView 6 for the same
        TextView hdate = currentItemView.findViewById(R.id.hdate);
        hdate.setText(String.valueOf(hData.getHdate()) );

        // then return the recyclable view
        return currentItemView;
    }

}
