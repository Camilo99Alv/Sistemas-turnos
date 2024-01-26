/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parcial2.Controller;

import com.mycompany.parcial2.Conexion.Crud;
import com.mycompany.parcial2.Model.Personas;
import com.mycompany.parcial2.Views.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class WindowController implements ActionListener {
    private Ventana ventana;
    
    public WindowController() {
        this.ventana = new Ventana();
        this.ventana.getAgregar().addActionListener(this);
        this.ventana.getDelete().addActionListener(this);
        this.ventana.getBuscar().addActionListener(this);
        this.ventana.getActualizar().addActionListener(this);
        this.ventana.getSeeAll().addActionListener(this);
        
        fillTable(Personas.getAll());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.ventana.getAgregar()) {
            Personas persona  = new Personas();
            actualizarInformacion(persona);
            List<Personas> personas = Crud.select(Personas.searchPerson(persona.getIdentification()));
            if (personas.size() == 0) {
                Crud.CRUD("INSERT INTO personas" + persona.toString());
                fillTable(Personas.getAll());
            }
            else {
                JOptionPane.showMessageDialog(null, "Persona agregada anteriormente.");
            }
        }
        else if (e.getSource() == this.ventana.getDelete()) {
            Integer row = this.ventana.getJTable1().getSelectedRow();
            Integer id = (Integer) this.ventana.getJTable1().getValueAt(row, 1);
            Crud.CRUD(Personas.toDelete(id));
            fillTable(Personas.getAll());
        }
        else if (e.getSource() == this.ventana.getBuscar()) {
            Integer identificacion = Integer.parseInt(this.ventana.getSearchId().getText());
            List<Personas> busqueda = Crud.select(Personas.searchPerson(identificacion));
            switch (busqueda.size()) {
                case 0: JOptionPane.showMessageDialog(null, "No se encuentra a la persona."); break;
                case 1: fillTable(Personas.searchPerson(identificacion)); break;
                default: JOptionPane.showMessageDialog(null, "Error crítico.");
            }
        }
        else if (e.getSource() == this.ventana.getActualizar()) {
            Personas persona = new Personas();
            actualizarInformacion(persona);
            Crud.CRUD(persona.updateData());
            fillTable(Personas.getAll());
        }
        else if (e.getSource() == this.ventana.getSeeAll()) {
            fillTable(Personas.getAll());
        }
    }
    
    
    private void actualizarInformacion(Personas persona) {
        persona.setIdentification(Integer.parseInt(ventana.getId().getText()));
        persona.setTipo_identificacion((String) ventana.getTipo().getSelectedItem());
        persona.setName(ventana.getNombre().getText());
        persona.setStatus((String) ventana.getStatus().getSelectedItem());
        persona.setCredit_value(Integer.parseInt(ventana.getValor().getText()));
    }
    
    private void clean() {
        ventana.getId().setText("");
        ventana.getTipo().setSelectedIndex(0);
        ventana.getNombre().setText("");
        ventana.getStatus().setSelectedIndex(0);
        ventana.getValor().setText("");
    }
    
    
    private void fillTable(String sentence) {
        List<Personas> personas = Crud.select(sentence);
        ventana.getTabla().setRowCount(personas.size());
        
        Integer counter = 0;
        for (Personas persona: personas) {
            this.ventana.getJTable1().setValueAt(persona.getTipo_identificacion(), counter, 0);
            this.ventana.getJTable1().setValueAt(persona.getIdentification(), counter, 1);
            this.ventana.getJTable1().setValueAt(persona.getName(), counter, 2);
            this.ventana.getJTable1().setValueAt(persona.getStatus(), counter, 3);
            this.ventana.getJTable1().setValueAt(persona.getCredit_value(), counter, 4);
            counter++;
        }
    }
}
