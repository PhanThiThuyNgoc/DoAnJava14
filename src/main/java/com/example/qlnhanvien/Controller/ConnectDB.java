package com.example.qlnhanvien.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class ConnectDB {
    private static String DB_URL = "jdbc:mysql://localhost:3306/duanquanly";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private Statement stmt;
    private Connection conn;

    public ConnectDB(){
        try {
            this.conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            this.stmt = conn.createStatement();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Connection getConn() {
        return conn;
    }


    public Statement getStmt() {
        return stmt;
    }
}

