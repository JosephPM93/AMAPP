/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudTest;

import Modelos.Conexion;
import Modelos.Vacuna;

/**
 *
 * @author José Padilla
 */
public class Insert {
    static Conexion con = new Conexion();
    public static void main(String[] args) {
        con.getSession().beginTransaction();
        con.getSession().save(new Vacuna("Sinovac"));
        con.getSession().getTransaction().commit();
        con.getSession().close();
    }
}
