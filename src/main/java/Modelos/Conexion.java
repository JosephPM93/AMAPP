/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author José Padilla
 */
public class Conexion {

    private static SessionFactory SessionFactory;
    private static Session Session;
    
    public Conexion(){
        IniciarConexion();
    }

    private void IniciarConexion() {
        try {
            setSessionFactory(new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Dosis.class)
                    .addAnnotatedClass(PCR.class)
                    .addAnnotatedClass(Persona.class)
                    .addAnnotatedClass(PersonaDosisVacuna.class)
                    .addAnnotatedClass(Usuario.class)
                    .addAnnotatedClass(Vacuna.class)
                    .addAnnotatedClass(PersonaPCR.class)
                    .buildSessionFactory());
            
            setSession(getSessionFactory().openSession());

        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Sin conexión a la base de datos local.\nMensaje de error: " + e,
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
            System.exit(0);
        }
    }

    /**
     * @return the SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return SessionFactory;
    }

    /**
     * @param aSessionFactory the SessionFactory to set
     */
    public static void setSessionFactory(SessionFactory aSessionFactory) {
        SessionFactory = aSessionFactory;
    }

    /**
     * @return the Session
     */
    public static Session getSession() {
        return Session;
    }

    /**
     * @param aSession the Session to set
     */
    public static void setSession(Session aSession) {
        Session = aSession;
    }
    
}
