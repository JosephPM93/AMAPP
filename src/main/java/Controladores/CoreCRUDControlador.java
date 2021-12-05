/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//Importancion de librerias
import Modelos.*;
import java.sql.Date;
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
    /**
     * En este metodo se intenta obtener la dosis seleccionada, por medio de la
     * DB agregandola a la lista de tipo objecto de la clase Dosis
     *
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<Dosis> SelectDosis() {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<Dosis> ListaDatos = con.getSession().createQuery("from dosis").getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Creacion de metodo select PCR
    /**
     * En este metodo se intenta obtener la prueba PCR seleccionada, por medio
     * de la DB agregandola a la lista de tipo objecto de la clase PCR
     *
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<PCR> SelectPCR() {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<PCR> ListaDatos = con.getSession().createQuery("from pcr").getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Creacion de metodo select Persona
    /**
     * En este metodo se intenta obtener la persona seleccionada, por medio de
     * la DB agregandola a la lista de tipo objecto de la clase Persona
     *
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<Persona> SelectPersona() {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<Persona> ListaDatos = con.getSession().createQuery("from personas").getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //Creacion de metodo select Persona
    /**
     * En este metodo se intenta obtener la persona seleccionada, por medio de
     * la DB agregandola a la lista de tipo objecto de la clase Persona
     *
     * @param fecha
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<Persona> SelectPersona(Date fecha) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<Persona> ListaDatos = con.getSession().createQuery("from personas where Fecha_ingreso='"+fecha+"'").getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Creacion de metdo select para PersonaDosisVacuna
    /**
     * En este metodo se intenta obtener de una tabla pivote la seleccion de
     * Persona, Dosis o Vacuna por medio de la DB agregandola a la lista de tipo
     * objecto de la clase PersonaDosisVacuna.
     *
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<PersonaDosisVacuna> SelectPersonaDosisVacuna() {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<PersonaDosisVacuna> ListaDatos = con.getSession().createQuery("from persona_dosis_vacuna").getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Creacion de metdo select para PersonaDosisVacuna
    /**
     * En este metodo se intenta obtener de una tabla pivote la seleccion de
     * Persona, Dosis o Vacuna por medio de la DB agregandola a la lista de tipo
     * objecto de la clase PersonaDosisVacuna.
     *
     * @param id
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<PersonaDosisVacuna> SelectPersonaDosisVacuna(int id) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<PersonaDosisVacuna> ListaDatos = con.getSession().createQuery("from persona_dosis_vacuna where Persona_id=" + id).getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Creacion de metodo select PersonaPCR
    /**
     * En este metodo se intenta obtener la PersonaPCR seleccionada, por medio
     * de la DB agregandola a la lista de tipo objecto de la clase PersonaPCR.
     *
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<PersonaPCR> SelectPersonaPCR() {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<PersonaPCR> ListaDatos = con.getSession().createQuery("from persona_pcr").getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Creacion de metodo select PersonaPCR
    /**
     * En este metodo se intenta obtener la PersonaPCR seleccionada, por medio
     * de la DB agregandola a la lista de tipo objecto de la clase PersonaPCR.
     *
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<PersonaPCR> SelectPersonaPCR(int id) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<PersonaPCR> ListaDatos = con.getSession().createQuery("from persona_pcr where Persona_id = " + id).getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Crecion de metodo select Usuario
    /**
     * En este metodo se intenta obtener el usuario seleccionada por medio de la
     * DB agregandola a la lista de tipo objecto de la clase Usuario.
     *
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<Usuario> SelectUsuario() {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<Usuario> ListaDatos = con.getSession().createQuery("from usuario").getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Creacion de metodo select vacuna
    /**
     * En este metodo se intenta obtener la vacuna seleccionada por medio de la
     * DB agregandola a la lista de tipo objecto de la clase Vacuna.
     *
     * @return ListaDatos - Retonor de la lista con elementos agregados.
     *
     */
    public List<Vacuna> SelectVacuna() {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            List<Vacuna> ListaDatos = con.getSession().createQuery("from vacuna").getResultList();
            con.getSession().close();
            return ListaDatos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    ============================================
        MÉTODOS INSERT PARA TABLAS DE LA BD
    ============================================
     */
    //Creacion de metodo Insert dosis
    /**
     * En este metodo se intenta Insertar y guardar el dato de Dosis ingresado,
     * en la DB
     *
     * @param dato - Creacion de un objeto de la clase Dosis que queremos
     * Insertar
     * @return boolean - Si el dato ingresado se Inserto y guardo con exito nos
     * retornara un valor true, en caso contrario en que algo fallara con la
     * insersion del dato nos retornara false.
     *
     */
    public boolean Insert(Dosis dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().save(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Insert PCR
    /**
     * En este metodo se intenta Insertar y guardar el dato de PCR ingresado, en
     * la DB
     *
     * @param dato - Creacion de un objeto de la clase PCR que queremos Insertar
     * @return boolean - Si el dato ingresado se Inserto y guardo con exito nos
     * retornara un valor true, en caso contrario en que algo fallara con la
     * insersion del dato nos retornara false.
     *
     */
    public boolean Insert(PCR dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().save(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Insert Persona
    /**
     * En este metodo se intenta Insertar y guardar el dato de Persona
     * ingresado, en la DB
     *
     * @param dato - Creacion de un objeto de la clase Persona que queremos
     * Insertar
     * @return boolean - Si el dato ingresado se Inserto y guardo con exito nos
     * retornara un valor true, en caso contrario en que algo fallara con la
     * insersion del dato nos retornara false.
     *
     */
    public boolean Insert(Persona dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().save(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Insert PersonaDosisVacuna
    /**
     * En este metodo se intenta Insertar y guardar el dato de Persona, Dosis
     * y/o vacuna ingresado, en la DB
     *
     * @param dato - Creacion de un objeto de la clase PersonaDosisVacuna que
     * queremos Insertar
     * @return boolean - Si el dato ingresado se Inserto y guardo con exito nos
     * retornara un valor true, en caso contrario en que algo fallara con la
     * insersion del dato nos retornara false.
     *
     */
    public boolean Insert(PersonaDosisVacuna dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().save(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * En este metodo se intenta Insertar y guardar el dato de PersonaPCR
     * ingresado, en la DB
     *
     * @param dato - Creacion de un objeto de la clase PersonaPCR que queremos
     * Insertar
     * @return boolean - Si el dato ingresado se Inserto y guardo con exito nos
     * retornara un valor true, en caso contrario en que algo fallara con la
     * insersion del dato nos retornara false.
     *
     */
    //Creacion de metodo Insert PersonaPCR
    public boolean Insert(PersonaPCR dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().save(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Crecion de metodo Insert Usuario
    /**
     * En este metodo se intenta Insertar y guardar el dato de Usuario
     * ingresado, en la DB
     *
     * @param dato - Creacion de un objeto de la clase Usuario que queremos
     * Insertar
     * @return boolean - Si el dato ingresado se Inserto y guardo con exito nos
     * retornara un valor true, en caso contrario en que algo fallara con la
     * insersion del dato nos retornara false.
     *
     */
    public boolean Insert(Usuario dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().save(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Insert Vacuna
    /**
     * En este metodo se intenta Insertar y guardar el dato de Vacuna ingresado,
     * en la DB
     *
     * @param dato - Creacion de un objeto de la clase Vacuna que queremos
     * Insertar
     * @return boolean - Si el dato ingresado se Inserto y guardo con exito nos
     * retornara un valor true, en caso contrario en que algo fallara con la
     * insersion del dato nos retornara false.
     *
     */
    public boolean Insert(Vacuna dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().save(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    ============================================
        MÉTODOS UPDATE PARA TABLAS DE LA BD
    ============================================
     */
    //Creacion de metodo Update dosis
    /**
     * Este metodo se encarga de subir los elementos ingresados a la DB.
     *
     * @param dato - Creacion de un objecto de la clase Dosis que tomaremos para
     * intentar subir a la DB
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * subir el elemento a la DB nos retornara true, caso contrario que surga
     * una falla nos retornara false.
     *
     */
    public boolean Update(Dosis dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().update(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Update PCR
    /**
     * Este metodo se encarga de subir los elementos ingresados a la DB.
     *
     * @param dato - Creacion de un objecto de la clase PCR que tomaremos para
     * intentar subir a la DB
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * subir el elemento a la DB nos retornara true, caso contrario que surga
     * una falla nos retornara false.
     *
     */
    public boolean Update(PCR dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().update(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Update Persona
    /**
     * Este metodo se encarga de subir los elementos ingresados a la DB.
     *
     * @param dato - Creacion de un objecto de la clase Persona que tomaremos
     * para intentar subir a la DB
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * subir el elemento a la DB nos retornara true, caso contrario que surga
     * una falla nos retornara false.
     *
     */
    public boolean Update(Persona dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().update(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Update PersonaDosisVacuna
    /**
     * Este metodo se encarga de subir los elementos ingresados a la DB.
     *
     * @param dato - Creacion de un objecto de la clase PersonaDosisVacuna que
     * tomaremos para intentar subir a la DB
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * subir el elemento a la DB nos retornara true, caso contrario que surga
     * una falla nos retornara false.
     *
     */
    public boolean Update(PersonaDosisVacuna dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().update(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Update PersonaPCR
    /**
     * Este metodo se encarga de subir los elementos ingresados a la DB.
     *
     * @param dato - Creacion de un objecto de la clase PersonasPCR que
     * tomaremos para intentar subir a la DB
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * subir el elemento a la DB nos retornara true, caso contrario que surga
     * una falla nos retornara false.
     *
     */
    public boolean Update(PersonaPCR dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().update(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Update Usuario
    /**
     * Este metodo se encarga de subir los elementos ingresados a la DB.
     *
     * @param dato - Creacion de un objecto de la clase Usuario que tomaremos
     * para intentar subir a la DB
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * subir el elemento a la DB nos retornara true, caso contrario que surga
     * una falla nos retornara false.
     *
     */
    public boolean Update(Usuario dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().update(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Update Vacuna
    /**
     * Este metodo se encarga de subir los elementos ingresados a la DB.
     *
     * @param dato - Creacion de un objecto de la clase Vacuna que tomaremos
     * para intentar subir a la DB
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * subir el elemento a la DB nos retornara true, caso contrario que surga
     * una falla nos retornara false.
     *
     */
    public boolean Update(Vacuna dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().update(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    ============================================
        MÉTODOS DELETE PARA TABLAS DE LA BD
    ============================================
     */
    //Creacion de metodo Delete dosis
    /**
     * Este metodo se encarga de eliminar el elemento desde la app y de la DB.
     *
     * @param dato - Creacion de un objecto de la clase Dosis que tomaremos para
     * intentar eliminar de la DB.
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * eliminar el elemento de la DB nos retornara true, caso contrario que
     * surga una falla nos retornara false.
     *
     */
    public boolean Delete(Dosis dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().delete(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Delete PCR
    /**
     * Este metodo se encarga de eliminar el elemento desde la app y de la DB.
     *
     * @param dato - Creacion de un objecto de la clase PCR que tomaremos para
     * intentar eliminar de la DB.
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * eliminar el elemento de la DB nos retornara true, caso contrario que
     * surga una falla nos retornara false.
     *
     */
    public boolean Delete(PCR dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().delete(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Delete Persona
    /**
     * Este metodo se encarga de eliminar el elemento desde la app y de la DB.
     *
     * @param dato - Creacion de un objecto de la clase Persona que tomaremos
     * para intentar eliminar de la DB.
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * eliminar el elemento de la DB nos retornara true, caso contrario que
     * surga una falla nos retornara false.
     *
     */
    public boolean Delete(Persona dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().delete(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Delete PersonaDosisVacuna
    /**
     * Este metodo se encarga de eliminar el elemento desde la app y de la DB.
     *
     * @param dato - Creacion de un objecto de la clase PersonaDosisVacuna que
     * tomaremos para intentar eliminar de la DB.
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * eliminar el elemento de la DB nos retornara true, caso contrario que
     * surga una falla nos retornara false.
     *
     */
    public boolean Delete(PersonaDosisVacuna dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().delete(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Delete PesonaPCR
    /**
     * Este metodo se encarga de eliminar el elemento desde la app y de la DB.
     *
     * @param dato - Creacion de un objecto de la clase PersonaPCR que tomaremos
     * para intentar eliminar de la DB.
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * eliminar el elemento de la DB nos retornara true, caso contrario que
     * surga una falla nos retornara false.
     *
     */
    public boolean Delete(PersonaPCR dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().delete(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Delete Usuario
    /**
     * Este metodo se encarga de eliminar el elemento desde la app y de la DB.
     *
     * @param dato - Creacion de un objecto de la clase Usuario que tomaremos
     * para intentar eliminar de la DB.
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * eliminar el elemento de la DB nos retornara true, caso contrario que
     * surga una falla nos retornara false.
     *
     */
    public boolean Delete(Usuario dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().delete(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Creacion de metodo Delete Vacuna
    /**
     * Este metodo se encarga de eliminar el elemento desde la app y de la DB.
     *
     * @param dato - Creacion de un objecto de la clase Vacuna que tomaremos
     * para intentar eliminar de la DB.
     * @return boolean - En caso de no haber ningun inconveniente a la hora de
     * eliminar el elemento de la DB nos retornara true, caso contrario que
     * surga una falla nos retornara false.
     *
     */
    public boolean Delete(Vacuna dato) {
        try {
            con.setSession(con.getSessionFactory().openSession());
            con.getSession().beginTransaction();
            con.getSession().delete(dato);
            con.getSession().getTransaction().commit();
            con.getSession().close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* 
        Metodos para estadistica
     */
    /**
     * Este metodo se encarga de guardar en un Array la cantidad de 'Recuperados y Fallecidos'
     * @return datos - Retorno de Array 
     **/
    public static int[] Casos() {
        int[] datos = new int[3];
        Conexion c = new Conexion();

        Long count = ((Long) c.getSession().createQuery("select count(*) from personas where Recuperado = 0 AND Fallecido = 0").uniqueResult());
        datos[0] = count.intValue();

        count = ((Long) c.getSession().createQuery("select count(*) from personas where Recuperado = 1").uniqueResult());
        datos[1] = count.intValue();

        count = ((Long) c.getSession().createQuery("select count(*) from personas where Fallecido = 1").uniqueResult());
        datos[2] = count.intValue();

        return datos;
    }

}
