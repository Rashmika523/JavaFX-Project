package com.insitute.iimanage.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //Step 01
    private static DBConnection dbConnection;

    //Step 02
    private Connection connection;

    //Step 03
    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdcb.Driver");
        connection=DriverManager.
                getConnection("jdbc:mysql://127.0.0.1:3306/iitmanage","root","1234");
    }

    //Step 04

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {

        if(dbConnection==null){
            dbConnection=new DBConnection();
        }

        return dbConnection;
    }

    //Step 05
    public Connection getConnection(){
        return connection;
    }

}
