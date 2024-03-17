package com.example.app_login.doctor.doctor.Symptomsdoctor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.app_login.R;
import com.example.app_login.doctor.doctor.Symptomsdoctor.SymDataD;

import java.util.ArrayList;


public class SymViewAdapter  extends ArrayAdapter<SymDataD>
{
    public SymViewAdapter(@NonNull Context context,  ArrayList<SymDataD> arrayList)
    {
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
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_sym_doctor, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        SymDataD symData = getItem(position);
////////////////////////////////////////////////////////////////////////////////
        // assign the desired TextView for the same

        TextView hburn_value = currentItemView.findViewById(R.id.hburn_value);
        if(String.valueOf(symData.getHeartb()).equals("1")) {
            hburn_value.setText("True");
        }else{
            hburn_value.setText("False");
        }


        TextView stomulcer_value = currentItemView.findViewById(R.id.stomulcer_value);
        if(String.valueOf(symData.getStoulcer()).equals("1")) {
            stomulcer_value.setText("True");
        }else{
            stomulcer_value.setText("False");
        }

        TextView vom_value = currentItemView.findViewById(R.id.vom_value);
        if(String.valueOf(symData.getVomi()).equals("1")) {
            vom_value.setText("True");
        }else{
            vom_value.setText("False");
        }

        TextView naus_value = currentItemView.findViewById(R.id.naus_value);
        if(String.valueOf(symData.getNau()).equals("1")) {
            naus_value.setText("True");
        }else{
            naus_value.setText("False");
        }


        TextView dia_value = currentItemView.findViewById(R.id.dia_value);
        if(String.valueOf(symData.getDiarr()).equals("1")) {
            dia_value.setText("True");
        }else{
            dia_value.setText("False");
        }


        TextView incon_value = currentItemView.findViewById(R.id.incon_value);
        if(String.valueOf(symData.getIncon()).equals("1")) {
            incon_value.setText("True");
        }else{
            incon_value.setText("False");
        }

        TextView bleed_value = currentItemView.findViewById(R.id.bleed_value);
        if(String.valueOf(symData.getBleedd()).equals("1")) {
            bleed_value.setText("True");
        }else{
            bleed_value.setText("False");
        }


        TextView bloat_value = currentItemView.findViewById(R.id.bloat_value);
        if(String.valueOf(symData.getBloatt()).equals("1")) {
            bloat_value.setText("True");
        }else{
            bloat_value.setText("False");
        }


        TextView constt_value = currentItemView.findViewById(R.id.constt_value);
        if(String.valueOf(symData.getCons()).equals("1")) {
            constt_value.setText("True");
        }else{
            constt_value.setText("False");
        }


        TextView sthroat_value = currentItemView.findViewById(R.id.sthroat_value);
        if(String.valueOf(symData.getSothroat()).equals("1")) {
            sthroat_value.setText("True");
        }else{
            sthroat_value.setText("False");
        }


        TextView head_value = currentItemView.findViewById(R.id.head_value);
        if(String.valueOf(symData.getHeadd()).equals("1")) {
            head_value.setText("True");
        }else{
            head_value.setText("False");
        }


        TextView sneez_value = currentItemView.findViewById(R.id.sneez_value);
        if(String.valueOf(symData.getSneez()).equals("1")) {
            sneez_value.setText("True");
        }else{
            sneez_value.setText("False");
        }


        TextView snose_value = currentItemView.findViewById(R.id.snose_value);
        if(String.valueOf(symData.getStuffyn()).equals("1")) {
            snose_value.setText("True");
        }else{
            snose_value.setText("False");
        }



        TextView coughh_value = currentItemView.findViewById(R.id.coughh_value);
        if(String.valueOf(symData.getCoughh()).equals("1")) {
            coughh_value.setText("True");
        }else{
            coughh_value.setText("False");
        }



        TextView breath_value = currentItemView.findViewById(R.id.breath_value);
        if(String.valueOf(symData.getBreathh()).equals("1")) {
            breath_value.setText("True");
        }else{
            breath_value.setText("False");
        }


        TextView dizzin_value = currentItemView.findViewById(R.id.dizzin_value);
        if(String.valueOf(symData.getDizzinn()).equals("1")) {
            dizzin_value.setText("True");
        }else{
            dizzin_value.setText("False");
        }


        TextView baches_value = currentItemView.findViewById(R.id.baches_value);
        if(String.valueOf(symData.getBachee()).equals("1")) {
            baches_value.setText("True");
        }else{
            baches_value.setText("False");
        }


        TextView sirritation_value = currentItemView.findViewById(R.id.sirritation_value);
        if(String.valueOf(symData.getSkinirri()).equals("1")) {
            sirritation_value.setText("True");
        }else{
            sirritation_value.setText("False");
        }



        TextView sdate_value = currentItemView.findViewById(R.id.sdate_value);
        sdate_value.setText(String.valueOf(symData.getSydate()) );




        ///////////////////////////////////////////////////////////////
        //System.out.println("textsssssssssssssssssss ----> "+baches_value+sirritation_value);

        // then return the recyclable view
        return currentItemView;

    }

}
