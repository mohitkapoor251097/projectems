package com.smart.entities;

import javax.persistence.*;

@Entity
public class Attendence {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  int aid;

    private  String doa;

    private  String name;

    private  long code;
    private String time;

    @ManyToOne
    private Contact contact;


    public Attendence() {
    }


    public Attendence(int aid, String doa, String name, long code, String time, Contact contact) {
        this.aid = aid;
        this.doa = doa;
        this.name = name;
        this.code = code;
        this.time = time;
        this.contact = contact;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
