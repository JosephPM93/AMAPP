/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author José Padilla
 */
@Entity(name = "pcr")
public class PCR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;
    @Column(name = "Nombre")
    private String Nombre;

    /**
     *
     * @param Id
     * @param Nombre
     */
    public PCR(int Id, String Nombre) {
        this.Id = Id;
        this.Nombre = Nombre;
    }

    /**
     *
     * @param Nombre
     */
    public PCR(String Nombre) {
        this.Nombre = Nombre;
    }
    
    /**
     *
     */
    public PCR() {
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
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    @Override
    public String toString() {
        return Nombre;
    }
}
