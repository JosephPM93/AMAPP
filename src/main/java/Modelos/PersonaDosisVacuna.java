/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author José Padilla
 */

@Entity(name = "persona_dosis_vacuna")
public class PersonaDosisVacuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;
    @Column(name = "Persona_id")
    private int Persona_id;
    @Column(name = "Dosis_id")
    private int Dosis_id;
    @Column(name = "Vacuna_id")
    private int Vacuna_id;
    @Column(name = "Fecha_puesta")
    private Date Fecha_puesta;

    public PersonaDosisVacuna(int Id, int Persona_id, int Dosis_id, int Vacuna_id, Date Fecha_puesta) {
        this.Id = Id;
        this.Persona_id = Persona_id;
        this.Dosis_id = Dosis_id;
        this.Vacuna_id = Vacuna_id;
        this.Fecha_puesta = Fecha_puesta;
    }

    public PersonaDosisVacuna(int Persona_id, int Dosis_id, int Vacuna_id, Date Fecha_puesta) {
        this.Persona_id = Persona_id;
        this.Dosis_id = Dosis_id;
        this.Vacuna_id = Vacuna_id;
        this.Fecha_puesta = Fecha_puesta;
    }

    public PersonaDosisVacuna() {
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the Persona_id
     */
    public int getPersona_id() {
        return Persona_id;
    }

    /**
     * @param Persona_id the Persona_id to set
     */
    public void setPersona_id(int Persona_id) {
        this.Persona_id = Persona_id;
    }

    /**
     * @return the Dosis_id
     */
    public int getDosis_id() {
        return Dosis_id;
    }

    /**
     * @param Dosis_id the Dosis_id to set
     */
    public void setDosis_id(int Dosis_id) {
        this.Dosis_id = Dosis_id;
    }

    /**
     * @return the Vacuna_id
     */
    public int getVacuna_id() {
        return Vacuna_id;
    }

    /**
     * @param Vacuna_id the Vacuna_id to set
     */
    public void setVacuna_id(int Vacuna_id) {
        this.Vacuna_id = Vacuna_id;
    }

    /**
     * @return the Fecha_puesta
     */
    public Date getFecha_puesta() {
        return Fecha_puesta;
    }

    /**
     * @param Fecha_puesta the Fecha_puesta to set
     */
    public void setFecha_puesta(Date Fecha_puesta) {
        this.Fecha_puesta = Fecha_puesta;
    }
    
}
