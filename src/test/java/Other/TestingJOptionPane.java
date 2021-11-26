/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import javax.swing.JOptionPane;

/**
 *
 * @author José Padilla
 */
public class TestingJOptionPane {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "¡Hubo un problema!", "Error", JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
        JOptionPane.showInputDialog(null, "Ingrese el nombre Dosis", "Ingresar datos", JOptionPane.QUESTION_MESSAGE);
        JOptionPane.showMessageDialog(null, "¡No se ha seleccionado nada!", "Error", JOptionPane.WARNING_MESSAGE);
    }
}
