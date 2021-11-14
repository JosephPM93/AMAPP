/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudTest;

import Modelos.Conexion;
import Modelos.Vacuna;
import java.util.List;

/**
 *
 * @author José Padilla
 */
public class Select {
    static Conexion con = new Conexion();
    public static void main(String[] args) {
        con.getSession().beginTransaction();
        List<Vacuna> listVacuna = con.getSession().createQuery("from vacuna").getResultList();
        for (Vacuna v: listVacuna) {
            System.out.println(v.getNombre());
        }
        con.getSession().close();
    }
}
