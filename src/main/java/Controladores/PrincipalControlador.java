/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Vistas.PrincipalVista;

/**
 *
 * @author José Padilla
 */
public class PrincipalControlador {

    static CoreCRUDControlador core = new CoreCRUDControlador();

    public static void main(String[] args) {
        new PrincipalVista(core.SelectVacuna(), core.SelectPCR(), core.SelectDosis(), core.SelectPersona()).setVisible(true);
        
    }
}
