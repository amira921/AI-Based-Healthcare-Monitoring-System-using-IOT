package com.example.app_login.doctor.doctor.FScheduleDoc;

public class FScheduleDataDoc {

    private String dday,ddate,dhour,id;

    public FScheduleDataDoc(String dday, String ddate, String dhour,String id) {
        this.dday = dday;
        this.ddate = ddate;
        this.dhour = dhour;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDday() {
        return dday;
    }

    public void setDday(String dday) {
        this.dday = dday;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getDhour() {
        return dhour;
    }

    public void setDhour(String dhour) {
        this.dhour = dhour;
    }
}
