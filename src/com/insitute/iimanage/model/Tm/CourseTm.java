package com.insitute.iimanage.model.Tm;

import javafx.scene.control.Button;

public class CourseTm {
    private String code;
    private String name;
    private String teacher;
    private Button btnTechList;
    private double cost;
    private Button btnOption;

    public CourseTm() {
    }

    public CourseTm(String code, String name, String teacher, Button btnTechList, double cost, Button btnOption) {
        this.code = code;
        this.name = name;
        this.teacher = teacher;
        this.btnTechList = btnTechList;
        this.cost = cost;
        this.btnOption = btnOption;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Button getBtnTechList() {
        return btnTechList;
    }

    public void setBtnTechList(Button btnTechList) {
        this.btnTechList = btnTechList;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Button getBtnOption() {
        return btnOption;
    }

    public void setBtnOption(Button btnOption) {
        this.btnOption = btnOption;
    }
}
