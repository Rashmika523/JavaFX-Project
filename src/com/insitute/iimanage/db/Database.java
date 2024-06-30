package com.insitute.iimanage.db;

import com.insitute.iimanage.model.Student;
import com.insitute.iimanage.model.User;
import com.insitute.iimanage.util.security.PasswordManager;

import java.util.ArrayList;

public class Database {

    public static ArrayList<User> userTable = new ArrayList();
    public static ArrayList<Student> studentTable = new ArrayList();

    static {
        userTable.add(new User(
                "Amal",
                "Perera",
                "amal@gmail.com",
                new PasswordManager().encrypt("1234")
        ));
    }

}
