/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//Importancion de librerias
import Modelos.Conexion;
import Modelos.Dosis;
import Modelos.Vacuna;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Padilla
 */
public class CoreCRUDControlador {
    //Metodos CRUD por cada modelo

    //Creacion de objecto con la conexion a DB
    private Conexion con = new Conexion();

    //Creacion de metodo select dosis
    public List<Dosis> SelectDosis() {
        con.getSession().beginTransaction();
        List<Dosis> ListaDatos = con.getSession().createQuery("from dosis").getResultList();
        return ListaDatos;
    }

    //Creacion de metodo Insert dosis
    public boolean InsertDosis(Dosis dato) {
        try {
            con.getSession().beginTransaction();
            con.getSession().save(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    //Creacion de metodo Delete dosis
    public boolean DeleteDosis(Dosis dato) {
        try {
            con.getSession().beginTransaction();
            con.getSession().delete(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    //Creacion de metodo Update dosis
    public boolean UpdateDosis(Dosis dato) {
        try {
            con.getSession().beginTransaction();
            con.getSession().update(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;
        
        } catch (Exception e) {
            return false;
        }
    }
}
