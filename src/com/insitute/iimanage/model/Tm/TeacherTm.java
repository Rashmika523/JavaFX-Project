package com.insitute.iimanage.model.Tm;

import javafx.scene.control.Button;

public class TeacherTm {

    private String id;
    private String name;
    private String address;
    private String contact;
    private Button button;

    public TeacherTm() {
    }

    public TeacherTm(String id, String name, String address, String contact, Button button) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.button = button;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
