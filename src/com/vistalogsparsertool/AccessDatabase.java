package com.vistalogsparsertool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class AccessDatabase {
    public static void main(String[] args) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:nVistaLogsDB");
            Statement st = con.createStatement();
            String name = "roseindia";
            String address = "delhi";
            int i = st.executeUpdate("insert into user(name,address) values('" + name + "','" + address + "')");
            System.out.println("Row is added");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}