package com.example.app_login.doctor.doctor.schedule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.app_login.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final ArrayList<LocalDate> days;
    public final View parentView;
    public final TextView dayOfMonth;
    private final CalendarAdapter.onItemListener onItemListener;

 //constructor takes in three parameters(View itemView, CalendarAdapter.onItemListener onItemListener , ArrayList<LocalDate> days)

 /*It then initializes two instance variables: parentView and dayOfMonth.
 These variables are references to views within the View object that was passed in.
 parentView represents the top-level view for the item,
 while dayOfMonth represents the view that displays the day of the month.*/

 /*he constructor initializes the days instance variable with the ArrayList of LocalDate objects that was passed in.
 This days list will be used by the CalendarViewHolder to determine the current date being displayed,
 as well as the position of the item within the RecyclerView*/



    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.onItemListener onItemListener , ArrayList<LocalDate> days) {
        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth= itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days=days;
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(),days.get(getAdapterPosition()));
    }
}
