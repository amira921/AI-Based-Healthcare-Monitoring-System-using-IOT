package com.example.app_login.doctor.doctor.schedule;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_login.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

    private final ArrayList<LocalDate> days;
    private final onItemListener onItemListener;

    public CalendarAdapter(ArrayList<LocalDate> days, CalendarAdapter.onItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }
/*The code then checks the size of the days list to determine whether the adapter is in month view or week view.
If there are more than 15 days in the days list (implying that it is a month with more than two weeks),
then the height of the view is set to 1/6th of the height of the parent view.
Otherwise, if it is a week view, the height of the view is set to the full height of the parent view.*/
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calender_of_schedule_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        if(days.size() > 15) //month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);

        else //week view
            layoutParams.height = (int) parent.getHeight();


        return new CalendarViewHolder( view, onItemListener ,days);

    }
 /*If the current date is the same as the selected date (which is stored in the CalenderUtlis.selectedDate field),
 the parentView of the holder is set to a particular drawable resource that represents a selected item in the schedule.*/
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        final LocalDate date = days.get(position);
        if(date == null)
            holder.dayOfMonth.setText("");
        else
        {
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(CalenderUtlis.selectedDate))
                holder.parentView.setBackgroundResource(R.drawable.selected_item_schedule_shape);

        }
    }

//day Size
    @Override
    public int getItemCount()
    {
        return days.size();
    }
//specifies the position of the clicked item in the list
    public interface onItemListener
    {
        void onItemClick(int position,LocalDate date);
    }
}
