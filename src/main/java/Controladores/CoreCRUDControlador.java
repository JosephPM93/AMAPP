/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//Importancion de librerias
import Modelos.*;
import java.util.List;

/**
 *
 * @author José Padilla
 */
public class CoreCRUDControlador {
    //Metodos CRUD por cada modelo

    //Creacion de objecto con la conexion a DB
    private Conexion con = new Conexion();

    /*
    ============================================
        MÉTODOS SELECT PARA TABLAS DE LA BD
    ============================================
    */
    
    //Creacion de metodo select dosis
    public List<Dosis> SelectDosis() {
        con.getSession().beginTransaction();
        List<Dosis> ListaDatos = con.getSession().createQuery("from dosis").getResultList();
        return ListaDatos;
    }
    
    //Creacion de metodo select PCR
    public List<PCR> SelectPCR() {
        con.getSession().beginTransaction();
        List<PCR> ListaDatos = con.getSession().createQuery("from pcr").getResultList();
        return ListaDatos;
    }
    
    //Creacion de metodo select PCR
    public List<Persona> SelectPersona() {
        con.getSession().beginTransaction();
        List<Persona> ListaDatos = con.getSession().createQuery("from persona").getResultList();
        return ListaDatos;
    }

    
    /*
    ============================================
        MÉTODOS INSERT PARA TABLAS DE LA BD
    ============================================
    */
    
    //Creacion de metodo Insert dosis
    public boolean Insert(Dosis dato) {
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
    
    //Creacion de metodo Insert PCR
    public boolean Insert(PCR dato) {
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
    
    //Creacion de metodo Insert PCR
    public boolean Insert(Persona dato) {
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
    
    /*
    ============================================
        MÉTODOS UPDATE PARA TABLAS DE LA BD
    ============================================
    */
    
    
    //Creacion de metodo Update dosis
    public boolean Update(Dosis dato) {
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
    
    //Creacion de metodo Update PCR
    public boolean Update(PCR dato) {
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
    
    //Creacion de metodo Update PCR
    public boolean Update(Persona dato) {
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
    
    /*
    ============================================
        MÉTODOS DELETE PARA TABLAS DE LA BD
    ============================================
    */

    //Creacion de metodo Delete dosis
    public boolean Delete(Dosis dato) {
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

    //Creacion de metodo Delete PCR
    public boolean Delete(PCR dato) {
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
    
    //Creacion de metodo Delete PCR
    public boolean Delete(Persona dato) {
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
}
