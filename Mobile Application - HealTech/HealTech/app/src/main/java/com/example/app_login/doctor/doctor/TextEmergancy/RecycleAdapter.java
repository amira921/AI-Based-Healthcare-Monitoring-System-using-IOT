package com.example.app_login.doctor.doctor.TextEmergancy;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_login.R;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class RecycleAdapter extends
        RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.recycle_row, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;

    }////////////////////////////////////////////

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        // Get the data model based on position
        MyData data = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(data.getName());
        ImageView img = viewHolder.imgmessage;
        img.setBackgroundResource(R.drawable.ic_noun_warning_2145621);


    }////////////////////////////////////////////////////////////////

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public ImageView imgmessage;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(com.example.app_login.R.id.patient_name);
            imgmessage = (ImageView) itemView.findViewById(R.id.message_img);
        }
    }/////////////////////////////////////////////////////////////////////////////////
    private List<MyData> mContacts;
    //////////////////////////////////////////////////////////
    // Pass in the contact array into the constructor
    public RecycleAdapter(List<MyData> contacts) {
        mContacts = contacts;
    }}