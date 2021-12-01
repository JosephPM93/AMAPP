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
    /**
     * Metodo para mostrar que los datos han sido guardados con exito
     **/
    public void DatosGuardadosMensaje() {
        JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Metodo para mostrar que los datos han sido guardados con exito
     * @param mensaje
     **/
    public void DatosGuardadosMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, "Datos guardados correctamente "+mensaje, "Ok", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Metodo para mostrar que hubo un problema con el almacenamiento de los datos
     **/
    public void MensajeError() {
        JOptionPane.showMessageDialog(null, "¡Hubo un problema!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Metodo para mostrar que hubo un problema con el almacenamiento de los datos
     * @param mensaje
     **/
    public void MensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, "¡Hubo un problema!"+mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Metodo para mostrar que el valor ingresado no es valido
     **/
    public void ValorNoValidoMensaje() {
        JOptionPane.showMessageDialog(null, "¡Valor ingresado no válido!", "Error", JOptionPane.WARNING_MESSAGE);
    }
    /**
     * Metodo para mostrar que no puede dejar una seleccion en blanco
     **/
    public void NoHaySeleccionMensaje(){
        JOptionPane.showMessageDialog(null, "¡No se ha seleccionado nada!", "Error", JOptionPane.WARNING_MESSAGE);
    }
    
}
