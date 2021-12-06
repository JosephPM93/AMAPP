/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.CalculosControlador;
import Controladores.ConsultasControlador;
import Controladores.CoreCRUDControlador;
import Libs.Validaciones;
import Modelos.Dosis;
import Modelos.PCR;
import Modelos.Persona;
import Modelos.PersonaDosisVacuna;
import Modelos.PersonaPCR;
import Modelos.Vacuna;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author José Padilla
 */
public class RegistroFormularioVista extends javax.swing.JDialog {

    List<Vacuna> ListaVacunas;
    List<PCR> ListaPCR;
    List<Dosis> ListaDosis;

    DefaultComboBoxModel ComboBoxModelVacuna;
    DefaultComboBoxModel ComboBoxModelPCR;
    DefaultComboBoxModel ComboBoxModelDosis;

    Validaciones valid = new Validaciones();
    CoreCRUDControlador coreCrud = new CoreCRUDControlador();
    ConsultasControlador cons = new ConsultasControlador();
    CalculosControlador cal = new CalculosControlador();

    Persona casoPersona;
    PersonaDosisVacuna pdv;
    PersonaPCR ppcr;

    String mensaje_pacientes = ": Datos básicos paciente";
    String mensaje_dosis = ": Datos dosis y vacunas";
    String mensaje_pcr = ": Datos PCR";
    String mensaje_error = " no guardado.";
    String mensaje_recomendacion = "\nIntente editarlos después";

    boolean ModoEditarPersona = false;
    boolean ModoEditarPDV = false;
    boolean ModoEditarPPCR = false;

    Date fechaIngreso;

    /**
     * Creates new form RegistroFormularioVista
     */
    public RegistroFormularioVista() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     *
     * @param ListaVacunas
     * @param ListaPCR
     * @param ListaDosis
     * @param fechaIngreso
     */
    public RegistroFormularioVista(List<Vacuna> ListaVacunas, List<PCR> ListaPCR, List<Dosis> ListaDosis, Date fechaIngreso) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Registrar nuevo caso");
        this.setResizable(false);

        this.JL_FDosis.setVisible(false);
        this.JDC_FechaDosis.setVisible(false);

        this.JL_FPCR.setVisible(false);
        this.JDC_FechaPCR.setVisible(false);

        this.JL_Vacuna.setVisible(false);
        this.JComboBox_Vacuna.setVisible(false);

        this.ListaVacunas = ListaVacunas;
        this.ListaPCR = ListaPCR;
        this.ListaDosis = ListaDosis;

        this.fechaIngreso = fechaIngreso;
        inicializarDatos();
    }

    /**
     *
     * @param ListaVacunas
     * @param ListaPCR
     * @param ListaDosis
     * @param casoPersona
     * @param pdv
     * @param pcr
     * @param fechaIngreso
     */
    public RegistroFormularioVista(List<Vacuna> ListaVacunas, List<PCR> ListaPCR, List<Dosis> ListaDosis, Persona casoPersona, PersonaDosisVacuna pdv, PersonaPCR pcr, Date fechaIngreso) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Editar un caso");
        this.setResizable(false);

        ModoEditarPersona = true;

        this.ListaVacunas = ListaVacunas;
        this.ListaPCR = ListaPCR;
        this.ListaDosis = ListaDosis;
        this.casoPersona = casoPersona;
        this.pdv = pdv;
        this.ppcr = pcr;

        this.fechaIngreso = fechaIngreso;

        if (this.pdv == null) {
            this.JL_FDosis.setVisible(false);
            this.JDC_FechaDosis.setVisible(false);
            this.JL_Vacuna.setVisible(false);
            this.JComboBox_Vacuna.setVisible(false);
        } else {
            this.ModoEditarPDV = true;
            System.out.println("ModoEditarPDV: " + ModoEditarPDV);
        }

        if (this.ppcr == null) {
            this.JL_FPCR.setVisible(false);
            this.JDC_FechaPCR.setVisible(false);
        } else {
            this.ModoEditarPPCR = true;
            System.out.println("ModoEditarPPCR: " + ModoEditarPPCR);
        }

        inicializarDatos();

    }

    private void inicializarDatos() {
        this.JComboBox_Vacuna.setModel(CalculosControlador.rellenarListaVacuna(ComboBoxModelVacuna, ListaVacunas));
        this.JComboBox_PCR.setModel(CalculosControlador.rellenarListaPCR(ComboBoxModelPCR, ListaPCR));
        this.JComboBox_Dosis.setModel(CalculosControlador.rellenarListaDosis(ComboBoxModelDosis, ListaDosis));

        if (ModoEditarPersona) {
            if (this.casoPersona != null) {
                this.JTF_Nombres.setText(this.casoPersona.getNombres());
                this.JTF_Apellidos.setText(this.casoPersona.getApellidos());
                this.JTF_DUI.setText(this.casoPersona.getDui());
                this.JDC_FechaNacimiento.setDate(this.casoPersona.getF_nacimiento());
                if (this.casoPersona.isSexo()) {
                    this.JRBTN_M.setSelected(true);
                } else {
                    this.JRBTN_F.setSelected(true);
                }
                this.JCB_Sintomas.setSelected(this.casoPersona.isSintomas());
                this.JCB_Recuperado.setSelected(this.casoPersona.isRecuperado());
                this.JCB_Fallecido.setSelected(this.casoPersona.isFallecido());
                this.JTA_DatosExtras.setText(this.casoPersona.getDetalles());

                if (this.pdv != null) {
                    this.JComboBox_Dosis.setSelectedItem(cal.buscarEnListaDosis(ListaDosis, this.pdv.getDosis_id()).getNombre());
                    this.JComboBox_Vacuna.setSelectedItem(cal.buscarEnListaVacuna(ListaVacunas, this.pdv.getVacuna_id()).getNombre());
                    this.JDC_FechaDosis.setDate(this.pdv.getFecha_puesta());
                }

                if (this.ppcr != null) {
                    this.JComboBox_PCR.setSelectedItem(cal.buscarEnListaPCR(ListaPCR, this.ppcr.getPcr_id()).getNombre());
                    this.JDC_FechaPCR.setDate(this.ppcr.getFecha_realizada());
                }
            } else {
                cons.MensajeError();
            }
        }
    }

    private boolean esValidoFormulario(int i) {
        boolean base
                = !valid.EsVacio(this.JTF_Nombres)
                && !valid.EsVacio(this.JTF_Apellidos)
                && !valid.EsVacio(this.JTF_DUI)
                && !valid.EsVacio(this.JDC_FechaNacimiento)
                && (this.JRBTN_F.isSelected() || this.JRBTN_M.isSelected());
        return switch (i) {
            case 0 ->
                base;
            case 1 ->
                base && !valid.EsVacio(this.JDC_FechaDosis);
            case 2 ->
                base && !valid.EsVacio(this.JDC_FechaPCR);
            case 3 ->
                base && !valid.EsVacio(this.JDC_FechaDosis) && !valid.EsVacio(this.JDC_FechaPCR);
            default ->
                base;
        };
    }

    private Persona crearDatosPersona() {
        Persona casoNuevo = new Persona(
                this.JTF_Nombres.getText().trim(),
                this.JTF_Apellidos.getText().trim(),
                this.JTF_DUI.getText().trim(),
                JDC_FechaNacimiento.getDate(),
                this.JRBTN_M.isSelected(),
                this.JCB_Sintomas.isSelected(),
                this.JCB_Recuperado.isSelected(),
                this.JCB_Fallecido.isSelected(),
                this.JTA_DatosExtras.getText(),
                fechaIngreso
        );
        if (casoPersona != null) {
            int IdTemp = casoPersona.getId();
            Date fechaIngresoTemp = casoPersona.getFecha_ingreso();
            casoPersona = casoNuevo;
            casoPersona.setId(IdTemp);
            casoPersona.setFecha_ingreso(fechaIngresoTemp);
        }
        return casoNuevo;
    }

    private PersonaDosisVacuna crearDatosDosisVacunaPersona() {
        Persona caso;
        if (casoPersona == null) {
            List<Persona> personas = coreCrud.SelectPersona();
            caso = personas.get(personas.size() - 1);
        } else {
            caso = casoPersona;
        }
        return new PersonaDosisVacuna(
                caso.getId(),
                this.ListaDosis.get(this.JComboBox_Dosis.getSelectedIndex() - 1).getId(),
                this.ListaVacunas.get(this.JComboBox_Vacuna.getSelectedIndex() - 1).getId(),
                this.JDC_FechaDosis.getDate()
        );
    }

    private PersonaPCR crearPersonaPCR() {
        Persona caso;
        if (casoPersona == null) {
            List<Persona> personas = coreCrud.SelectPersona();
            caso = personas.get(personas.size() - 1);
        } else {
            caso = casoPersona;
        }
        return new PersonaPCR(
                caso.getId(),
                this.ListaPCR.get(this.JComboBox_PCR.getSelectedIndex() - 1).getId(),
                this.JDC_FechaPCR.getDate()
        );
    }

    private Persona actualizarCasoPersona() {
        Persona p = crearDatosPersona();
        p.setId(this.casoPersona.getId());
        p.setFecha_ingreso(this.casoPersona.getFecha_ingreso());
        return p;
    }

    private PersonaDosisVacuna actualizarDatosDosisVacunaPersona() {
        PersonaDosisVacuna p = crearDatosDosisVacunaPersona();
        p.setId(this.pdv.getId());
        p.setPersona_id(this.casoPersona.getId());
        return p;
    }

    private PersonaPCR actualizarPersonaPCR() {
        PersonaPCR p = crearPersonaPCR();
        p.setId(this.ppcr.getId());
        p.setPersona_id(this.casoPersona.getId());
        return p;
    }

    private boolean IngresarPersona() {
        return (!ModoEditarPersona) ? coreCrud.Insert(crearDatosPersona()) : coreCrud.Update(actualizarCasoPersona());
    }

    private boolean IngresarDosisVacunaPersona() {
        if (this.JComboBox_Dosis.getSelectedIndex() != 0 && this.JComboBox_Vacuna.getSelectedIndex() != 0) {
            return (!ModoEditarPDV) ? coreCrud.Insert(crearDatosDosisVacunaPersona()) : coreCrud.Update(actualizarDatosDosisVacunaPersona());
        } else {
            if (pdv != null) {
                return coreCrud.Delete(pdv);
            } else {
                return true;
            }
        }
    }

    private boolean IngresarPCRPersona() {
        return (!ModoEditarPPCR) ? coreCrud.Insert(crearPersonaPCR()) : coreCrud.Update(actualizarPersonaPCR());
    }

    private boolean EliminarDosisVacunaPersona() {
        return coreCrud.Delete(this.pdv);
    }

    private boolean EliminarPCRPersona() {
        return coreCrud.Delete(ppcr);
    }

    private boolean GuardarDatos() {
        boolean Exito = false;
        if ((this.JComboBox_Dosis.getSelectedIndex() != 0 && this.JComboBox_Vacuna.getSelectedIndex() != 0) && this.JComboBox_PCR.getSelectedIndex() != 0) {
            if (esValidoFormulario(3)) {
                if (IngresarPersona()) {
                    cons.DatosGuardadosMensaje(mensaje_pacientes);
                    Exito = true;
                    if (IngresarDosisVacunaPersona()) {
                        cons.DatosGuardadosMensaje(mensaje_dosis);
                    } else {
                        cons.MensajeError(mensaje_dosis + mensaje_error + mensaje_recomendacion);
                    }
                    if (IngresarPCRPersona()) {
                        cons.DatosGuardadosMensaje(mensaje_pcr);
                    } else {
                        cons.MensajeError(mensaje_pcr + mensaje_error + mensaje_recomendacion);
                    }
                } else {
                    cons.MensajeError(mensaje_pacientes + mensaje_error);
                }
            } else {
                cons.ValorNoValidoMensaje();
            }
        } else if (this.JComboBox_Dosis.getSelectedIndex() != 0 && this.JComboBox_Vacuna.getSelectedIndex() != 0) {
            if (esValidoFormulario(1)) {
                if (IngresarPersona()) {
                    cons.DatosGuardadosMensaje(mensaje_pacientes);
                    Exito = true;
                    if (IngresarDosisVacunaPersona()) {
                        cons.DatosGuardadosMensaje(mensaje_dosis);
                    } else {
                        cons.MensajeError(mensaje_dosis + mensaje_error + mensaje_recomendacion);
                    }
                } else {
                    cons.MensajeError(mensaje_pacientes + mensaje_error);
                }
            } else {
                cons.ValorNoValidoMensaje();
            }
        } else if (this.JComboBox_PCR.getSelectedIndex() != 0) {
            if (esValidoFormulario(2)) {

                if (IngresarPersona()) {
                    cons.DatosGuardadosMensaje(mensaje_pacientes);
                    Exito = true;
                    if (IngresarPCRPersona()) {
                        cons.DatosGuardadosMensaje(mensaje_pcr);
                    } else {
                        cons.MensajeError(mensaje_pcr + mensaje_error + mensaje_recomendacion);
                    }
                } else {
                    cons.MensajeError(mensaje_pacientes + mensaje_error);
                }
            } else {
                cons.ValorNoValidoMensaje();
            }
        } else {
            int res = JOptionPane.showConfirmDialog(null, "¿Desea continuar sin asignar datos de vacunación y prueba PCR?", "¿?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.OK_OPTION) {
                if (esValidoFormulario(0)) {
                    if (IngresarPersona()) {
                        cons.DatosGuardadosMensaje(mensaje_pacientes);
                        Exito = true;
                    } else {
                        cons.MensajeError(mensaje_pacientes + mensaje_error);
                    }
                } else {
                    cons.ValorNoValidoMensaje();
                }
            }
        }

        if (this.JComboBox_Dosis.getSelectedIndex() == 0 && this.pdv != null) {
            this.EliminarDosisVacunaPersona();
        }

        if (this.JComboBox_PCR.getSelectedIndex() == 0 && this.ppcr != null) {
            EliminarPCRPersona();
        }

        return Exito;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JTF_Nombres = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        JTF_Apellidos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        JTF_DUI = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JDC_FechaNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        JRBTN_F = new javax.swing.JRadioButton();
        JRBTN_M = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        JComboBox_PCR = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        JCB_Sintomas = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        JComboBox_Dosis = new javax.swing.JComboBox<>();
        JL_Vacuna = new javax.swing.JLabel();
        JComboBox_Vacuna = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        JCB_Fallecido = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        JCB_Recuperado = new javax.swing.JCheckBox();
        JL_FDosis = new javax.swing.JLabel();
        JDC_FechaDosis = new com.toedter.calendar.JDateChooser();
        JL_FPCR = new javax.swing.JLabel();
        JDC_FechaPCR = new com.toedter.calendar.JDateChooser();
        JBTN_Cancelar = new javax.swing.JButton();
        JBTN_Guardar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTA_DatosExtras = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos personales"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Nombre(s)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel2.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(JTF_Nombres, gridBagConstraints);

        jLabel2.setText("Apellido(s)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel2.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(JTF_Apellidos, gridBagConstraints);

        jLabel3.setText("DUI");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(JTF_DUI, gridBagConstraints);

        jLabel4.setText("F. Nac.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel2.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel2.add(JDC_FechaNacimiento, gridBagConstraints);

        jLabel5.setText("Sexo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel2.add(jLabel5, gridBagConstraints);

        buttonGroup1.add(JRBTN_F);
        JRBTN_F.setText("F");

        buttonGroup1.add(JRBTN_M);
        JRBTN_M.setText("M");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JRBTN_F, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JRBTN_M)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JRBTN_F)
                    .addComponent(JRBTN_M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        jPanel2.add(jPanel4, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos médicos"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("PCR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel3.add(jLabel6, gridBagConstraints);

        JComboBox_PCR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JComboBox_PCR.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JComboBox_PCRItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(JComboBox_PCR, gridBagConstraints);

        jLabel7.setText("Síntomas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel3.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(JCB_Sintomas, gridBagConstraints);

        jLabel8.setText("Dósis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel3.add(jLabel8, gridBagConstraints);

        JComboBox_Dosis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JComboBox_Dosis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JComboBox_DosisItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(JComboBox_Dosis, gridBagConstraints);

        JL_Vacuna.setText("Vacuna");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel3.add(JL_Vacuna, gridBagConstraints);

        JComboBox_Vacuna.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(JComboBox_Vacuna, gridBagConstraints);

        jLabel10.setText("Fallecido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel3.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(JCB_Fallecido, gridBagConstraints);

        jLabel11.setText("Recuperado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel3.add(jLabel11, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(JCB_Recuperado, gridBagConstraints);

        JL_FDosis.setText("F. Dosis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel3.add(JL_FDosis, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(JDC_FechaDosis, gridBagConstraints);

        JL_FPCR.setText("F. PCR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jPanel3.add(JL_FPCR, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        jPanel3.add(JDC_FechaPCR, gridBagConstraints);

        JBTN_Cancelar.setText("Cancelar");
        JBTN_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_CancelarActionPerformed(evt);
            }
        });

        JBTN_Guardar.setText("Guardar");
        JBTN_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_GuardarActionPerformed(evt);
            }
        });

        JTA_DatosExtras.setColumns(20);
        JTA_DatosExtras.setRows(5);
        JTA_DatosExtras.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos extras"));
        jScrollPane2.setViewportView(JTA_DatosExtras);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JBTN_Cancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBTN_Guardar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBTN_Cancelar)
                    .addComponent(JBTN_Guardar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBTN_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_CancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_JBTN_CancelarActionPerformed

    private void JBTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_GuardarActionPerformed
        // TODO add your handling code here:
        if (GuardarDatos()) {
            this.dispose();
        }
    }//GEN-LAST:event_JBTN_GuardarActionPerformed

    private void JComboBox_DosisItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JComboBox_DosisItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 1) {
            if (this.JComboBox_Dosis.getSelectedIndex() != -1 && this.JComboBox_Dosis.getSelectedIndex() != 0) {
                this.JL_FDosis.setVisible(true);
                this.JDC_FechaDosis.setVisible(true);
                this.JL_Vacuna.setVisible(true);
                this.JComboBox_Vacuna.setVisible(true);
            } else {
                this.JL_FDosis.setVisible(false);
                this.JDC_FechaDosis.setVisible(false);
                this.JL_Vacuna.setVisible(false);
                this.JComboBox_Vacuna.setVisible(false);
            }
        }
    }//GEN-LAST:event_JComboBox_DosisItemStateChanged

    private void JComboBox_PCRItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JComboBox_PCRItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 1) {
            if (this.JComboBox_PCR.getSelectedIndex() != -1 && this.JComboBox_PCR.getSelectedIndex() != 0) {
                this.JL_FPCR.setVisible(true);
                this.JDC_FechaPCR.setVisible(true);
            } else {
                this.JL_FPCR.setVisible(false);
                this.JDC_FechaPCR.setVisible(false);
            }
        }
    }//GEN-LAST:event_JComboBox_PCRItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBTN_Cancelar;
    private javax.swing.JButton JBTN_Guardar;
    private javax.swing.JCheckBox JCB_Fallecido;
    private javax.swing.JCheckBox JCB_Recuperado;
    private javax.swing.JCheckBox JCB_Sintomas;
    private javax.swing.JComboBox<String> JComboBox_Dosis;
    private javax.swing.JComboBox<String> JComboBox_PCR;
    private javax.swing.JComboBox<String> JComboBox_Vacuna;
    private com.toedter.calendar.JDateChooser JDC_FechaDosis;
    private com.toedter.calendar.JDateChooser JDC_FechaNacimiento;
    private com.toedter.calendar.JDateChooser JDC_FechaPCR;
    private javax.swing.JLabel JL_FDosis;
    private javax.swing.JLabel JL_FPCR;
    private javax.swing.JLabel JL_Vacuna;
    private javax.swing.JRadioButton JRBTN_F;
    private javax.swing.JRadioButton JRBTN_M;
    private javax.swing.JTextArea JTA_DatosExtras;
    private javax.swing.JTextField JTF_Apellidos;
    private javax.swing.JTextField JTF_DUI;
    private javax.swing.JTextField JTF_Nombres;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
