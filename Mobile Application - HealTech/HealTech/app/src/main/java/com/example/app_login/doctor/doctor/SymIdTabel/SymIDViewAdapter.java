package com.example.app_login.doctor.doctor.SymIdTabel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.app_login.R;
import com.example.app_login.doctor.doctor.SymIdTabel.SymIDdate;

import java.util.ArrayList;

public class SymIDViewAdapter extends ArrayAdapter<SymIDdate> {
    public SymIDViewAdapter(@NonNull Context context, ArrayList<SymIDdate> arrayList)
    {
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.sym_id_list_row, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        SymIDdate sData = getItem(position);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView id_value = currentItemView.findViewById(R.id.id_value);
        id_value.setText(String.valueOf(sData.getId()) );

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView name_value = currentItemView.findViewById(R.id.name_value);
        name_value.setText(String.valueOf(sData.getName())+"  "+String.valueOf(sData.getSname())) ;

        // then return the recyclable view
        return currentItemView;

    }
}
