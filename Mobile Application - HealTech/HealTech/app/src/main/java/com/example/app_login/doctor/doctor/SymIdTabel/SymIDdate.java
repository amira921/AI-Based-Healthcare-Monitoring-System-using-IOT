package com.example.app_login.doctor.doctor.SymIdTabel;

public class SymIDdate {

    private String id;
    private String name,sname;

    public SymIDdate(String id, String name, String sname) {
        this.id = id;
        this.name = name;
        this.sname = sname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
