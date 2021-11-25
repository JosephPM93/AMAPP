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
    
    //Creacion de metodo select Dosis
    public List<Dosis> SelectDosis() {
        con.setSession(con.getSessionFactory().openSession());
        con.getSession().beginTransaction();
        List<Dosis> ListaDatos = con.getSession().createQuery("from dosis").getResultList();
        con.getSession().close();
        return ListaDatos;
    }
    
    //Creacion de metodo select PCR
    public List<PCR> SelectPCR() {
        con.setSession(con.getSessionFactory().openSession());
        con.getSession().beginTransaction();
        List<PCR> ListaDatos = con.getSession().createQuery("from pcr").getResultList();
        con.getSession().close();
        return ListaDatos;
    }
    
    //Creacion de metodo select Persona
    public List<Persona> SelectPersona() {
        con.setSession(con.getSessionFactory().openSession());
        con.getSession().beginTransaction();
        List<Persona> ListaDatos = con.getSession().createQuery("from personas").getResultList();
        con.getSession().close();
        return ListaDatos;
    }
    
    //Creacion de metdo select para PersonaDosisVacuna
    public List<PersonaDosisVacuna> SelectPersonaDosisVacuna() {
        con.setSession(con.getSessionFactory().openSession());
        con.getSession().beginTransaction();
        List<PersonaDosisVacuna> ListaDatos = con.getSession().createQuery("from persona_dosis_vacuna").getResultList();
        con.getSession().close();
        return ListaDatos;
    }
    
    //Creacion de metodo select PersonaPCR
    public List<PersonaPCR> SelectPersonaPCR() {
        con.setSession(con.getSessionFactory().openSession());
        con.getSession().beginTransaction();
        List<PersonaPCR> ListaDatos = con.getSession().createQuery("from persona_pcr").getResultList();
        con.getSession().close();
        return ListaDatos;
    }
    
    //Crecion de metodo select Usuario
    public List<Usuario> SelectUsuario() {
        con.setSession(con.getSessionFactory().openSession());
        con.getSession().beginTransaction();
        List<Usuario> ListaDatos = con.getSession().createQuery("from usuario").getResultList();
        con.getSession().close();
        return ListaDatos;
    }
    
    //Creacion de metodo select vacuna
    public List<Vacuna> SelectVacuna() {
        con.setSession(con.getSessionFactory().openSession());
        con.getSession().beginTransaction();
        List<Vacuna> ListaDatos = con.getSession().createQuery("from vacuna").getResultList();
        con.getSession().close();
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
    
    //Creacion de metodo Insert Persona
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
    
    //Creacion de metodo Insert PersonaDosisVacuna
    public boolean Insert(PersonaDosisVacuna dato) {
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
    
    //Creacion de metodo Insert PersonaPCR
    public boolean Insert(PersonaPCR dato) {
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
    
    //Crecion de metodo Insert Usuario
    public boolean Insert(Usuario dato) {
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
    
    //Creacion de metodo Insert Vacuna
    public boolean Insert(Vacuna dato) {
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
    
    //Creacion de metodo Update Persona
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
    
    //Creacion de metodo Update PersonaDosisVacuna
    public boolean Update(PersonaDosisVacuna dato) {
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
    
    //Creacion de metodo Update PersonaPCR
    public boolean Update(PersonaPCR dato) {
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
    
    //Creacion de metodo Update Usuario
    public boolean Update(Usuario dato) {
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
    
    //Creacion de metodo Update Vacuna
    public boolean Update(Vacuna dato) {
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
    
    //Creacion de metodo Delete Persona
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
    
    //Creacion de metodo Delete PersonaDosisVacuna
    public boolean Delete(PersonaDosisVacuna dato) {
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
    
    //Creacion de metodo Delete PesonaPCR
    public boolean Delete(PersonaPCR dato) {
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
    
    //Creacion de metodo Delete Usuario
    public boolean Delete(Usuario dato) {
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
    
    //Creacion de metodo Delete Vacuna
    public boolean Delete(Vacuna dato) {
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
