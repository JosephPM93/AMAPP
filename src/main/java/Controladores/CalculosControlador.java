/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Dosis;
import Modelos.PCR;
import Modelos.Persona;
import Modelos.PersonaDosisVacuna;
import Modelos.PersonaPCR;
import Modelos.Vacuna;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author José Padilla
 */
public class CalculosControlador {

    static CoreCRUDControlador coreCrud = new CoreCRUDControlador();

    /**
     * Este metodo es para el llenamiento de la lista vacunas
     *
     * @param modelo - Establecimiento del modelo para JTable
     * @param datos - Lista de tipo objeto de la clase Vacuna
     * @return modelo - Retorna el modelo de la tabla
     *
     */
    public static DefaultListModel rellenarListaVacuna(DefaultListModel modelo, List<Vacuna> datos) {
        modelo = new DefaultListModel();
        modelo.addElement("Sin seleccionar");
        for (Vacuna d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para el llenamiento de la lista PCR
     *
     * @param modelo - Establecimiento del modelo para JTable
     * @param datos - Lista de tipo objeto de la clase PCR
     * @return modelo - Retorna el modelo de la tabla
     *
     */
    public static DefaultListModel rellenarListaPCR(DefaultListModel modelo, List<PCR> datos) {
        modelo = new DefaultListModel();
        modelo.addElement("Sin seleccionar");
        for (PCR d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para el llenamiento de la lista Dosis
     *
     * @param modelo - Establecimiento del modelo para JTable
     * @param datos - Lista de tipo objeto de la clase Dosis
     * @return modelo - Retorna el modelo de la tabla
     *
     */
    public static DefaultListModel rellenarListaDosis(DefaultListModel modelo, List<Dosis> datos) {
        modelo = new DefaultListModel();
        modelo.addElement("Sin seleccionar");
        for (Dosis d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para el llenamiento de la lista Personas
     *
     * @param modelo - Establecimiento del modelo para JTable
     * @param datos - Lista de tipo objeto de la clase Persona
     * @return modelo - Retorna el modelo de la tabla
     *
     */
    public static DefaultTableModel rellenarTablaPersonas(DefaultTableModel modelo, List<Persona> datos, List<Vacuna> ListaVacunas, List<PCR> ListaPCR, List<Dosis> ListaDosis) {
        modelo = new DefaultTableModel();

        Object Titulos[] = {"Id", "N", "Nombre Completo", "DUI/DUI Responsable", "Edad", "Sexo", "Última dosis", "Prueba PCR", "Estado"};
        modelo.setColumnIdentifiers(Titulos);
        modelo.getDataVector().removeAllElements();

        try {
            int i = 1;
            for (Persona d : datos) {
                List<PersonaDosisVacuna> lpdv = coreCrud.SelectPersonaDosisVacuna(d.getId());
                List<PersonaPCR> lppcr = coreCrud.SelectPersonaPCR(d.getId());
                PersonaDosisVacuna pdv = null;
                PersonaPCR ppcr = null;
                if (lpdv.size() != 0) {
                    pdv = lpdv.get(0);
                }
                if (lppcr.size() != 0) {
                    ppcr = lppcr.get(0);
                }
                Object rows[] = {
                    d.getId(),
                    i,
                    d.getNombres() + " " + d.getApellidos(),
                    d.getDui(),
                    calcularAnios(d.getF_nacimiento()) + " Años",
                    d.isSexo() ? "Masculino" : "Femenino",
                    pdv == null ? "Desconocido"
                    : buscarEnListaDosis(ListaDosis, pdv.getDosis_id()).toString()
                    + ", " + buscarEnListaVacuna(ListaVacunas, pdv.getVacuna_id()).toString()
                    + ", " + pdv.getFecha_puesta(),
                    ppcr == null ? "Desconocido"
                    : buscarEnListaPCR(ListaPCR, ppcr.getPcr_id()).toString() + ", " + ppcr.getFecha_realizada(),
                    d.isFallecido() ? "Fallecido" : d.isRecuperado() ? "Recuperado" : d.isSintomas() ? "Con síntomas" : "Sin síntomas",};
                modelo.addRow(rows);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelo;
    }
    
    public static long calcularAnios(Date FechaInicial){
        return ChronoUnit.YEARS.between(FechaInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()); 
    }

    /**
     * Este metodo es para la seleccion del ComboBox Vacuna
     *
     * @param modelo - Establecimiento del modelo para ComboBox
     * @param datos - Lista de tipo objeto de la clase Vacuna
     * @return modelo - Retorna el modelo del ComboBox
     *
     */
    public static DefaultComboBoxModel rellenarListaVacuna(DefaultComboBoxModel modelo, List<Vacuna> datos) {
        modelo = new DefaultComboBoxModel();
        modelo.addElement("Sin seleccionar");
        for (Vacuna d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para la seleccion del ComboBox PCR
     *
     * @param modelo - Establecimiento del modelo para ComboBox
     * @param datos - Lista de tipo objeto de la clase PCR
     * @return modelo - Retorna el modelo del ComboBox
     *
     */
    public static DefaultComboBoxModel rellenarListaPCR(DefaultComboBoxModel modelo, List<PCR> datos) {
        modelo = new DefaultComboBoxModel();
        modelo.addElement("Sin seleccionar");
        for (PCR d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }

    /**
     * Este metodo es para la seleccion del ComboBox Dosis
     *
     * @param modelo - Establecimiento del modelo para ComboBox
     * @param datos - Lista de tipo objeto de la clase Dosis
     * @return modelo - Retorna el modelo del ComboBox
     *
     */
    public static DefaultComboBoxModel rellenarListaDosis(DefaultComboBoxModel modelo, List<Dosis> datos) {
        modelo = new DefaultComboBoxModel();
        modelo.addElement("Sin seleccionar");
        for (Dosis d : datos) {
            modelo.addElement(d.toString());
        }
        return modelo;
    }
    /**
     * Este metodo sirve para buscar en la lista Dosis por medio de un parametro id, si se encuentra un elemento dentro de la lista.
     * @param datos 
     * @param id 
     * @return res - Nos retorna si el valor ha sido encontrado el valor sino, nos retorna un valor null
     **/
    public static Dosis buscarEnListaDosis(List<Dosis> datos, int id) {
        Dosis res = null;
        for (Dosis d : datos) {
            if (d.getId() == id) {
                res = d;
                break;
            }
        }
        return res;
    }
    /**
     * Este metodo sirve para buscar en la lista PCR por medio de un parametro id, si se encuentra un elemento dentro de la lista.
     * @param datos 
     * @param id 
     * @return res - Nos retorna si el valor ha sido encontrado el valor sino, nos retorna un valor null
     **/
    public static PCR buscarEnListaPCR(List<PCR> datos, int id) {
        PCR res = null;
        for (PCR d : datos) {
            if (d.getId() == id) {
                res = d;
                break;
            }
        }
        return res;
    }
    /**
     * Este metodo sirve para buscar en la lista Vacuna por medio de un parametro id, si se encuentra un elemento dentro de la lista.
     * @param datos 
     * @param id 
     * @return res - Nos retorna si el valor ha sido encontrado el valor sino, nos retorna un valor null
     **/
    public static Vacuna buscarEnListaVacuna(List<Vacuna> datos, int id) {
        Vacuna res = null;
        for (Vacuna d : datos) {
            if (d.getId() == id) {
                res = d;
                break;
            }
        }
        return res;
    }
    /**
     * Este metodo sirve para buscar en la lista Persona por medio de un parametro id, si se encuentra un elemento dentro de la lista.
     * @param datos 
     * @param id 
     * @return res - Nos retorna si el valor ha sido encontrado el valor sino, nos retorna un valor null
     **/
    public static Persona buscarEnListaPersona(List<Persona> datos, int id) {
        Persona res = null;
        for (Persona d : datos) {
            if (d.getId() == id) {
                res = d;
                break;
            }
        }
        return res;
    }
}
