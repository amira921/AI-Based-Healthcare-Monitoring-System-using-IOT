package com.example.app_login.doctor.doctor.Historydoctor;

public class HistoryData {


    private String temp, oxg, hrate,pres,htime,hdate;

    public HistoryData(String temp, String oxg, String hrate, String pres, String htime, String hdate) {
        this.temp = temp;
        this.oxg = oxg;
        this.hrate = hrate;
        this.pres = pres;
        this.htime = htime;
        this.hdate = hdate;
    }


    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getOxg() {
        return oxg;
    }

    public void setOxg(String oxg) {
        this.oxg = oxg;
    }

    public String getHrate() {
        return hrate;
    }

    public void setHrate(String hrate) {
        this.hrate = hrate;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getHtime() {
        return htime;
    }

    public void setHtime(String htime) {
        this.htime = htime;
    }

    public String getHdate() {
        return hdate;
    }

    public void setHdate(String hdate) {
        this.hdate = hdate;
    }
}
