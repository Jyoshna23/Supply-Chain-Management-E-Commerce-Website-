package com.example.supplychain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
    String SQLURL = "jdbc:mysql://localhost:3306/supplychain?useSSl=false";
    String UserName = "root";
    String password = "J8106705268";

    Connection con = null;

    DatabaseConnection() {
        try {
            con = DriverManager.getConnection(SQLURL, UserName, password);
            if(con != null)
            {
                System.out.println("DATABASECONNECTION ESTABLISHED");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query){
        ResultSet res = null;
        try{
            Statement statement = con.createStatement();
            res = statement.executeQuery(query);
            return res;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public int executeUpdate(String query) throws Exception{
        int res = 0;
        try {
            Statement statement = con.createStatement();
            res = statement.executeUpdate(query);
            return res;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }
}
