package com.insitute.iimanage.model.Tm;

import javafx.scene.control.Button;

public class TechnolgiesTm {
    private int code;
    private String name;
    private Button button;

    public TechnolgiesTm() {
    }

    public TechnolgiesTm(int code, String name, Button button) {
        this.code = code;
        this.name = name;
        this.button = button;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
