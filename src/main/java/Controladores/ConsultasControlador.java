/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.swing.JOptionPane;

/**
 *
 * @author José Padilla
 */
public class ConsultasControlador {

    public void DatosGuardadosMensaje() {
        JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
    }

    public void MensajeError() {
        JOptionPane.showMessageDialog(null, "¡Hubo un problema!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void ValorNoValidoMensaje() {
        JOptionPane.showMessageDialog(null, "¡Valor ingresado no válido!", "Error", JOptionPane.WARNING_MESSAGE);
    }
    
    public void NoHaySeleccionMensaje(){
        JOptionPane.showMessageDialog(null, "¡No se ha seleccionado nada!", "Error", JOptionPane.WARNING_MESSAGE);
    }
    
}
