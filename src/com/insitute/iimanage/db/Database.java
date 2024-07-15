package com.insitute.iimanage.db;

import com.insitute.iimanage.model.*;
import com.insitute.iimanage.util.security.PasswordManager;
import javafx.scene.control.Button;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Database {

    public static ArrayList<User> userTable = new ArrayList();
    public static ArrayList<Student> studentTable = new ArrayList();
    public static ArrayList<Teacher> teacherTable = new ArrayList<>();
    public static ArrayList<Course> courseTable = new ArrayList<>();
    public static ArrayList<Intake> intakeTable = new ArrayList<>();

    static {
        userTable.add(new User(
                "Amal",
                "Perera",
                "amal@gmail.com",
                new PasswordManager().encrypt("1234")
        ));

        intakeTable.add(new Intake(
                "I-1",
                "Summar",
                new Date(),
                "SE",
                false,
                new Button("Delete")
        ));

    }

}
