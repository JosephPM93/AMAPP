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

@Entity(name = "persona_pcr")
public class PersonaPCR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;
    @Column(name = "Persona_id")
    private int Persona_id;
    @Column(name = "Pcr_id")
    private int Pcr_id;
    @Column(name = "Fecha_realizada")
    private Date Fecha_realizada;

    public PersonaPCR(int Id, int Persona_id, int Pcr_id, Date Fecha_realizada) {
        this.Id = Id;
        this.Persona_id = Persona_id;
        this.Pcr_id = Pcr_id;
        this.Fecha_realizada = Fecha_realizada;
    }

    public PersonaPCR(int Persona_id, int Pcr_id, Date Fecha_realizada) {
        this.Persona_id = Persona_id;
        this.Pcr_id = Pcr_id;
        this.Fecha_realizada = Fecha_realizada;
    }
    
    public PersonaPCR() {
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
     * @return the Pcr_id
     */
    public int getPcr_id() {
        return Pcr_id;
    }

    /**
     * @param Pcr_id the Pcr_id to set
     */
    public void setPcr_id(int Pcr_id) {
        this.Pcr_id = Pcr_id;
    }

    /**
     * @return the Fecha_realizada
     */
    public Date getFecha_realizada() {
        return Fecha_realizada;
    }

    /**
     * @param Fecha_realizada the Fecha_realizada to set
     */
    public void setFecha_realizada(Date Fecha_realizada) {
        this.Fecha_realizada = Fecha_realizada;
    }
    
}
