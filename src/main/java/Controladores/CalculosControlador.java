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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author José Padilla
 */
public class CalculosControlador {

    /**
     * Este metodo es para el llenamiento de la lista vacunas
     *
     * @param modelo - Establecimiento del modelo para JTable
     * @param datos - Lista de tipo objeto de la clase Vacuna
     * @return modelo - Retorna el modelo de la tabla
     *
     */
    public static DefaultListModel rellenarListaVacuna(DefaultListModel modelo, List<Vacuna> datos) {
        modelo = new DefaultListModel();
        modelo.addElement("Sin seleccionar");
        for (Vacuna d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para el llenamiento de la lista PCR
     *
     * @param modelo - Establecimiento del modelo para JTable
     * @param datos - Lista de tipo objeto de la clase PCR
     * @return modelo - Retorna el modelo de la tabla
     *
     */
    public static DefaultListModel rellenarListaPCR(DefaultListModel modelo, List<PCR> datos) {
        modelo = new DefaultListModel();
        modelo.addElement("Sin seleccionar");
        for (PCR d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para el llenamiento de la lista Dosis
     *
     * @param modelo - Establecimiento del modelo para JTable
     * @param datos - Lista de tipo objeto de la clase Dosis
     * @return modelo - Retorna el modelo de la tabla
     *
     */
    public static DefaultListModel rellenarListaDosis(DefaultListModel modelo, List<Dosis> datos) {
        modelo = new DefaultListModel();
        modelo.addElement("Sin seleccionar");
        for (Dosis d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para el llenamiento de la lista Personas
     *
     * @param modelo - Establecimiento del modelo para JTable
     * @param datos - Lista de tipo objeto de la clase Persona
     * @return modelo - Retorna el modelo de la tabla
     *
     */
    public static DefaultTableModel rellenarTablaPersonas(DefaultTableModel modelo, List<Persona> datos) {
        modelo = new DefaultTableModel();

        Object Titulos[] = {"Id", "N", "Nombre Completo", "DUI/DUI Responsable", "Edad", "Sexo", "Última dosis", "Prueba PCR", "Estado"};
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
                    d.getF_nacimiento(),
                    d.isSexo() ? "Masculino" : "Femenino",
                    "vacio",
                    "vacio",
                    d.isFallecido() ? "Fallecido" : d.isRecuperado() ? "Recuperado" : d.isSintomas() ? "Con síntomas" : "Sin síntomas",
                };
                modelo.addRow(rows);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelo;
    }

    /**
     * Este metodo es para la seleccion del ComboBox Vacuna
     *
     * @param modelo - Establecimiento del modelo para ComboBox
     * @param datos - Lista de tipo objeto de la clase Vacuna
     * @return modelo - Retorna el modelo del ComboBox
     *
     */
    public static DefaultComboBoxModel rellenarListaVacuna(DefaultComboBoxModel modelo, List<Vacuna> datos) {
        modelo = new DefaultComboBoxModel();
        modelo.addElement("Sin seleccionar");
        for (Vacuna d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para la seleccion del ComboBox PCR
     *
     * @param modelo - Establecimiento del modelo para ComboBox
     * @param datos - Lista de tipo objeto de la clase PCR
     * @return modelo - Retorna el modelo del ComboBox
     *
     */
    public static DefaultComboBoxModel rellenarListaPCR(DefaultComboBoxModel modelo, List<PCR> datos) {
        modelo = new DefaultComboBoxModel();
        modelo.addElement("Sin seleccionar");
        for (PCR d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para la seleccion del ComboBox Dosis
     *
     * @param modelo - Establecimiento del modelo para ComboBox
     * @param datos - Lista de tipo objeto de la clase Dosis
     * @return modelo - Retorna el modelo del ComboBox
     *
     */
    public static DefaultComboBoxModel rellenarListaDosis(DefaultComboBoxModel modelo, List<Dosis> datos) {
        modelo = new DefaultComboBoxModel();
        modelo.addElement("Sin seleccionar");
        for (Dosis d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    public Dosis buscarEnListaDosis(List<Dosis> datos, int id) {
        Dosis res = null;
        for (Dosis d : datos) {
            if (d.getId() == id) {
                res = d;
                break;
            }
        }
        return res;
    }

    public PCR buscarEnListaPCR(List<PCR> datos, int id) {
        PCR res = null;
        for (PCR d : datos) {
            if (d.getId() == id) {
                res = d;
                break;
            }
        }
        return res;
    }

    public Vacuna buscarEnListaVacuna(List<Vacuna> datos, int id) {
        Vacuna res = null;
        for (Vacuna d : datos) {
            if (d.getId() == id) {
                res = d;
                break;
            }
        }
        return res;
    }
}
