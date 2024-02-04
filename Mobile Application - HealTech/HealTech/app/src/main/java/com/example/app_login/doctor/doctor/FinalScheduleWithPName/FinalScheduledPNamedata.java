package com.example.app_login.doctor.doctor.FinalScheduleWithPName;

public class FinalScheduledPNamedata {


    private String day,date,hours,pName,spName;
    //pName,spName;

    public FinalScheduledPNamedata(String day, String date, String hours, String pName ,String spName) {
        this.day = day;
        this.date = date;
        this.hours = hours;
        this.pName = pName;
        this.spName = spName;
    }



    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

     public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/
}
