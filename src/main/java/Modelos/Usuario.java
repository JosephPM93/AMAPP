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
@Entity(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;
    @Column(name = "Nombres")
    private String Nombres;
    @Column(name = "Apellidos")
    private String Apellidos;
    @Column(name = "Contrasenia")
    private String Contrasenia;
    @Column(name = "Email")
    private String Email;

    public Usuario(int Id, String Nombres, String Apellidos, String Contrasenia, String Email) {
        this.Id = Id;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Contrasenia = Contrasenia;
        this.Email = Email;
    }

    public Usuario(String Nombres, String Apellidos, String Contrasenia, String Email) {
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Contrasenia = Contrasenia;
        this.Email = Email;
    }

    public Usuario() {
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
     * @return the Nombres
     */
    public String getNombres() {
        return Nombres;
    }

    /**
     * @param Nombres the Nombres to set
     */
    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    /**
     * @return the Apellidos
     */
    public String getApellidos() {
        return Apellidos;
    }

    /**
     * @param Apellidos the Apellidos to set
     */
    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    /**
     * @return the Contrasenia
     */
    public String getContrasenia() {
        return Contrasenia;
    }

    /**
     * @param Contrasenia the Contrasenia to set
     */
    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
}
