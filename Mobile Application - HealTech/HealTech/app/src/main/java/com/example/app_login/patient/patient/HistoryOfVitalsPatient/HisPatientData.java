package com.example.app_login.patient.patient.HistoryOfVitalsPatient;

public class HisPatientData {
    private String bpressure,date,time,ptemp,pheartrate,poxgen;

    public HisPatientData(String bpressure, String poxgen, String ptemp, String pheartrate, String time, String date) {
        this.bpressure = bpressure;
        this.poxgen = poxgen;
        this.ptemp = ptemp;
        this.pheartrate = pheartrate;
        this.time = time;
        this.date = date;
    }

    public String getBpressure() {
        return bpressure;
    }

    public void setBpressure(String bpressure) {
        this.bpressure = bpressure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPtemp() {
        return ptemp;
    }

    public void setPtemp(String ptemp) {
        this.ptemp = ptemp;
    }

    public String getPheartrate() {
        return pheartrate;
    }

    public void setPheartrate(String pheartrate) {
        this.pheartrate = pheartrate;
    }

    public String getPoxgen() {
        return poxgen;
    }

    public void setPoxgen(String poxgen) {
        this.poxgen = poxgen;
    }
}
