package com.example.app_login.doctor.doctor.HisTabelID;

public class HisIDdata {

    private String id;
    private String name ,sname;


    public HisIDdata(String id, String name, String sname) {
        this.id = id;
        this.name = name;
        this.sname = sname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}
}
