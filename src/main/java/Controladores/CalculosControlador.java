/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Dosis;
import Modelos.PCR;
import Modelos.Persona;
import Modelos.Vacuna;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author José Padilla
 */
public class CalculosControlador {

    public static DefaultListModel rellenarListaVacuna(DefaultListModel modelo, List<Vacuna> datos) {
        modelo = new DefaultListModel();
        modelo.addElement("Sin seleccionar");
        for (Vacuna d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    public static DefaultListModel rellenarListaPCR(DefaultListModel modelo, List<PCR> datos) {
        modelo = new DefaultListModel();
        modelo.addElement("Sin seleccionar");
        for (PCR d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    public static DefaultListModel rellenarListaDosis(DefaultListModel modelo, List<Dosis> datos) {
        modelo = new DefaultListModel();
        modelo.addElement("Sin seleccionar");
        for (Dosis d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    public static DefaultTableModel rellenarTablaPersonas(DefaultTableModel modelo, List<Persona> datos) {
        modelo = new DefaultTableModel();

        Object Titulos[] = {"Id", "N", "Nombre Completo", "DUI/DUI Responsable", "Edad", "Sexo", "Última dosis", "Prueba PCR", "Estado", "Detalles"};
        modelo.setColumnIdentifiers(Titulos);
        modelo.getDataVector().removeAllElements();

        try {
            int i = 1;
            for (Persona d : datos) {
                Object rows[] = {
                    d.getId(),
                    i,
                    d.getNombres() + " " + d.getApellidos(),
                    d.getDui(),
                    d.isSexo() ? "Masculino" : "Femenino",
                    d.isFallecido() ? "Fallecido" : d.isRecuperado() ? "Recuperado" : d.isSintomas() ? "Con síntomas" : "Sin síntomas",
                    "Click para ver",};
                modelo.addRow(rows);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelo;
    }

    public static DefaultComboBoxModel rellenarListaVacuna(DefaultComboBoxModel modelo, List<Vacuna> datos) {
        modelo = new DefaultComboBoxModel();
        modelo.addElement("Sin seleccionar");
        for (Vacuna d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    public static DefaultComboBoxModel rellenarListaPCR(DefaultComboBoxModel modelo, List<PCR> datos) {
        modelo = new DefaultComboBoxModel();
        modelo.addElement("Sin seleccionar");
        for (PCR d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    public static DefaultComboBoxModel rellenarListaDosis(DefaultComboBoxModel modelo, List<Dosis> datos) {
        modelo = new DefaultComboBoxModel();
        modelo.addElement("Sin seleccionar");
        for (Dosis d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }
}
