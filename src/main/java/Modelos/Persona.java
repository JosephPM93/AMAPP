/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author José Padilla
 */
@Entity(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;
    @Column(name = "Nombres")
    private String Nombres;
    @Column(name = "Apellidos")
    private String Apellidos;
    @Column(name = "Dui")
    private String Dui;
    @Column(name = "F_nacimiento")
    private Date F_nacimiento;
    @Column(name = "Sexo")
    private boolean Sexo;
    @Column(name = "Sintomas")
    private boolean Sintomas;
    @Column(name = "Recuperado")
    private boolean Recuperado;
    @Column(name = "Fallecido")
    private boolean Fallecido;
    @Column(name = "Detalles")
    private String Detalles;
    @Column(name = "Fecha_ingreso")
    private Date Fecha_ingreso;

    /**
     *
     * @param Id
     * @param Nombres
     * @param Apellidos
     * @param Dui
     * @param F_nacimiento
     * @param Sexo
     * @param Sintomas
     * @param Recuperado
     * @param Fallecido
     * @param Detalles
     * @param Fecha_ingreso
     */
    public Persona(int Id, String Nombres, String Apellidos, String Dui, Date F_nacimiento, boolean Sexo, boolean Sintomas, boolean Recuperado, boolean Fallecido, String Detalles, Date Fecha_ingreso) {
        this.Id = Id;
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Dui = Dui;
        this.F_nacimiento = F_nacimiento;
        this.Sexo = Sexo;
        this.Sintomas = Sintomas;
        this.Recuperado = Recuperado;
        this.Fallecido = Fallecido;
        this.Detalles = Detalles;
        this.Fecha_ingreso = Fecha_ingreso;
    }

    /**
     *
     * @param Nombres
     * @param Apellidos
     * @param Dui
     * @param F_nacimiento
     * @param Sexo
     * @param Sintomas
     * @param Recuperado
     * @param Fallecido
     * @param Detalles
     * @param Fecha_ingreso
     */
    public Persona(String Nombres, String Apellidos, String Dui, Date F_nacimiento, boolean Sexo, boolean Sintomas, boolean Recuperado, boolean Fallecido, String Detalles, Date Fecha_ingreso) {
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Dui = Dui;
        this.F_nacimiento = F_nacimiento;
        this.Sexo = Sexo;
        this.Sintomas = Sintomas;
        this.Recuperado = Recuperado;
        this.Fallecido = Fallecido;
        this.Detalles = Detalles;
        this.Fecha_ingreso = Fecha_ingreso;
    }
    
    /**
     *
     */
    public Persona() {
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
     * @return the Dui
     */
    public String getDui() {
        return Dui;
    }

    /**
     * @param Dui the Dui to set
     */
    public void setDui(String Dui) {
        this.Dui = Dui;
    }

    /**
     * @return the F_nacimiento
     */
    public Date getF_nacimiento() {
        return F_nacimiento;
    }

    /**
     * @param F_nacimiento the F_nacimiento to set
     */
    public void setF_nacimiento(Date F_nacimiento) {
        this.F_nacimiento = F_nacimiento;
    }

    /**
     * @return the Sexo
     */
    public boolean isSexo() {
        return Sexo;
    }

    /**
     * @param Sexo the Sexo to set
     */
    public void setSexo(boolean Sexo) {
        this.Sexo = Sexo;
    }

    /**
     * @return the Sintomas
     */
    public boolean isSintomas() {
        return Sintomas;
    }

    /**
     * @param Sintomas the Sintomas to set
     */
    public void setSintomas(boolean Sintomas) {
        this.Sintomas = Sintomas;
    }

    /**
     * @return the Recuperado
     */
    public boolean isRecuperado() {
        return Recuperado;
    }

    /**
     * @param Recuperado the Recuperado to set
     */
    public void setRecuperado(boolean Recuperado) {
        this.Recuperado = Recuperado;
    }

    /**
     * @return the Fallecido
     */
    public boolean isFallecido() {
        return Fallecido;
    }

    /**
     * @param Fallecido the Fallecido to set
     */
    public void setFallecido(boolean Fallecido) {
        this.Fallecido = Fallecido;
    }

    /**
     * @return the Detalles
     */
    public String getDetalles() {
        return Detalles;
    }

    /**
     * @param Detalles the Detalles to set
     */
    public void setDetalles(String Detalles) {
        this.Detalles = Detalles;
    }

    /**
     * @return the Fecha_ingreso
     */
    public Date getFecha_ingreso() {
        return Fecha_ingreso;
    }

    /**
     * @param Fecha_ingreso the Fecha_ingreso to set
     */
    public void setFecha_ingreso(Date Fecha_ingreso) {
        this.Fecha_ingreso = Fecha_ingreso;
    }

}
