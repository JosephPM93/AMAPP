/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Libs;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;

/**
 *
 * @author José Padilla
 */
public class Validaciones {
    /**
     * Metodo para validar si un JTextField esta vacio
     * @return boolean 
     **/
    public boolean EsVacio(JTextField jtf) {
        return jtf.getText().trim().equals("");
    }
    /**
     * Metodo para validar si un JDateChooser esta vacio
     * @return boolean 
     **/
    public boolean EsVacio(JDateChooser jdc) {
        return jdc.getDate() == null;
    }
    /**
     * Metodo para validar si un String esta vacio
     * @param text
     * @return boolean 
     **/
    public boolean EsVacio(String text) {
        return text == null || text.trim().equals("");
    }
    /**
     * Metodo para validar un numero decimal
     * @param jtf
     * @return boolean 
     **/
    public boolean JTextField_EsNumeroDecimal(JTextField jtf) {
        try {
            double test = Double.parseDouble(jtf.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
