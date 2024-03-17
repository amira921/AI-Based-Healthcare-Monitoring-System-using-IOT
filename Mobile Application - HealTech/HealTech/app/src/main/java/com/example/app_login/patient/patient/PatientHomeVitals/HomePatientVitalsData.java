package com.example.app_login.patient.patient.PatientHomeVitals;

public class HomePatientVitalsData {

    private String hpressure,hoxgen,htemp,hheartrate;

    public HomePatientVitalsData(String bpressure, String poxgen, String ptemp, String pheartrate) {
        this.hpressure = bpressure;
        this.hoxgen = poxgen;
        this.htemp = ptemp;
        this.hheartrate = pheartrate;
    }

    public String getHpressure() {
        return hpressure;
    }

    public void setHpressure(String hpressure) {
        this.hpressure = hpressure;
    }

    public String getHoxgen() {
        return hoxgen;
    }

    public void setHoxgen(String hoxgen) {
        this.hoxgen = hoxgen;
    }

    public String getHtemp() {
        return htemp;
    }

    public void setHtemp(String htemp) {
        this.htemp = htemp;
    }

    public String getHheartrate() {
        return hheartrate;
    }

    public void setHheartrate(String hheartrate) {
        this.hheartrate = hheartrate;
    }
}
