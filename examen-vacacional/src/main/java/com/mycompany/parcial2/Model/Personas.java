package com.mycompany.parcial2.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identification;
    
    @Column(name = "document_type")
    private String tipo_identificacion;
    private String name;
    private String status;
    private Integer credit_value;
    
    
    public String toString() {
        return "(identification, document_type, name, status, credit_value) VALUES (" + identification + ", '" + tipo_identificacion
                + "', '" + name + "', '" + status + "', " + credit_value + ");";
    }
    
    public static String toDelete(Integer id) {
        return String.format("DELETE FROM personas WHERE identification = %d", id);
    }
    
    public static String getAll() {
        return String.format("SELECT * FROM personas;");
    }
    
    public static String searchPerson(Integer id) {
        return String.format("SELECT * FROM personas WHERE identification = %d;", id);
    }
    
    public String updateData() {
        return String.format("UPDATE personas "
                + "SET "
                    + "name='%s', "
                    + "status='%s', "
                    + "credit_value=%d "
                + "WHERE identification = %d;"
                , name, status, credit_value, identification);
    }
}
