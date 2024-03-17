package com.example.app_login.doctor.doctor.ChatGpt;

import android.app.MediaRouteButton;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app_login.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    List<Message> messageList;
    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View chatView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_item,null);
        MyViewHolder myViewHolder = new MyViewHolder(chatView);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        Message message = messageList.get(i);

        if(message.getSentBy().equals(Message.SENT_BY_ME)){
            myViewHolder.leftChatView.setVisibility(View.GONE);
            myViewHolder.rightChatView.setVisibility(View.VISIBLE);
            myViewHolder.rightTextView.setText(message.getMessage());
        }else{
            myViewHolder.rightChatView.setVisibility(View.GONE);
            myViewHolder.leftChatView.setVisibility(View.VISIBLE);
            myViewHolder.leftTextView.setText(message.getMessage());
        }

    }

    @Override
    public int getItemCount()
    {
        return messageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout leftChatView,rightChatView;
        TextView leftTextView,rightTextView;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            leftChatView  = itemView.findViewById(R.id.left_chat_view);
            rightChatView = itemView.findViewById(R.id.right_chat_view);
            leftTextView = itemView.findViewById(R.id.left_chat_text_view);
            rightTextView = itemView.findViewById(R.id.right_chat_text_view);

        }
    }
}
