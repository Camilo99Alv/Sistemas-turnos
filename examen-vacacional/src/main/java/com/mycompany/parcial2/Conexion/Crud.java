package com.mycompany.parcial2.Conexion;

import com.mycompany.parcial2.Model.Personas;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Crud {
    
    public static boolean CRUD  (String sentence){
        boolean register = false;
        Statement st = null;
        Connection con = null;
        try {
            con=Conexion.Conectar();
            st = con.createStatement();
            st.execute(sentence);
            register = true;
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return register;
    }
    
    
    public static List<Personas> select(String sentence){
        Connection con = null;
        Statement st = null;
        ResultSet set = null;
        List<Personas> personas = new ArrayList();
        
        try {
            con= Conexion.Conectar();
            st = con.createStatement();
            set = st.executeQuery(sentence);
            while (set.next()) {
                Personas nodo = new Personas();
                nodo.setIdentification(set.getInt(1));
                nodo.setTipo_identificacion(set.getString(2));
                nodo.setName(set.getString(3));
                nodo.setStatus(set.getString(4));
                nodo.setCredit_value(set.getInt(5));
                personas.add(nodo);
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return personas;
    }
}
