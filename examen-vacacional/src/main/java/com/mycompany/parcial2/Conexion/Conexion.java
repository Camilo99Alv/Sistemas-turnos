package com.mycompany.parcial2.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {
    private static String database = "jdbc:mysql://localhost:3306/personas";
    private static String user = "root";
    private static String password = "1234";
    
    public static Connection Conectar() {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(database, user, password);
            
            Statement stmt=con.createStatement();  
            
            ResultSet rs=stmt.executeQuery("show databases;");
            return con;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }  
}
