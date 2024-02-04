package com.example.app_login.patient.patient.finetuned_model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app_login.R;

import java.util.List;

public class MessageModelAdapter extends RecyclerView.Adapter<MessageModelAdapter.MyViewHolder>{



    List<MessageModel> messageListModel;
    public MessageModelAdapter(List<MessageModel> messageListModel) {
        this.messageListModel = messageListModel;
    }

    @NonNull
    @Override
    public MessageModelAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View chatView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item_model,null);
        MessageModelAdapter.MyViewHolder myViewHolder = new MessageModelAdapter.MyViewHolder(chatView);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MessageModelAdapter.MyViewHolder myViewHolder, int i)
    {
        MessageModel message = messageListModel.get(i);

        if(message.getSentByModel().equals(MessageModel.SENT_BY_ME)){
            myViewHolder.leftChatViewM.setVisibility(View.GONE);
            myViewHolder.rightChatViewM.setVisibility(View.VISIBLE);
            myViewHolder.rightTextViewM.setText(message.getMessageModel());
        }else{
            myViewHolder.rightChatViewM.setVisibility(View.GONE);
            myViewHolder.leftChatViewM.setVisibility(View.VISIBLE);
            myViewHolder.leftTextViewM.setText(message.getMessageModel());
        }

    }

    @Override
    public int getItemCount()
    {
        return messageListModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout leftChatViewM,rightChatViewM;
        TextView leftTextViewM,rightTextViewM;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            leftChatViewM  = itemView.findViewById(R.id.left_chat_view_model);
            rightChatViewM = itemView.findViewById(R.id.right_chat_view_model);
            leftTextViewM = itemView.findViewById(R.id.left_chat_text_view_model);
            rightTextViewM = itemView.findViewById(R.id.right_chat_text_view_model);

        }
    }


}
