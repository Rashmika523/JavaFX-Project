package com.insitute.iimanage.model;

public class Teacher {

    private String teacherId;
    private String name;
    private String address;
    private String contact;

    public Teacher() {
    }

    public Teacher(String teacherId, String name, String address, String contact) {
        this.teacherId = teacherId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
