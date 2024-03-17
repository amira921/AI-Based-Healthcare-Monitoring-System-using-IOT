package com.example.app_login.patient.patient.finetuned_model;

public class MessageModel {
    public static String SENT_BY_ME = "me";
    public static String SENT_BY_BOT="bot";

    String messageModel;
    String sentByModel;

    public MessageModel(String messageModel, String sentByModel) {
        this.messageModel = messageModel;
        this.sentByModel = sentByModel;
    }

    public String getMessageModel() {
        return messageModel;
    }

    public void setMessageModel(String messageModel) {
        this.messageModel = messageModel;
    }

    public String getSentByModel() {
        return sentByModel;
    }

    public void setSentByModel(String sentByModel) {
        this.sentByModel = sentByModel;
    }
}
