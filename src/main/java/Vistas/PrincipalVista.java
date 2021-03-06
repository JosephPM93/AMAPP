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
import Libs.Estadistica;
import Modelos.PCR;
import Modelos.Persona;
import Modelos.PersonaDosisVacuna;
import Modelos.PersonaPCR;
import Modelos.Vacuna;
import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author José Padilla
 */
public class PrincipalVista extends javax.swing.JFrame {

    List<Vacuna> ListaVacunas;
    List<PCR> ListaPCR;
    List<Dosis> ListaDosis;

    List<Persona> ListaPersonas;

    DefaultListModel JListModelVacunas;
    DefaultListModel JListModelPCR;
    DefaultListModel JListModelDosis;

    DefaultTableModel JTableModelPersonas;

    RegistroFormularioVista rfv;

    Validaciones valid = new Validaciones();

    CoreCRUDControlador coreCrud = new CoreCRUDControlador();
    ConsultasControlador cons = new ConsultasControlador();
    CalculosControlador cal = new CalculosControlador();

    /**
     * Creates new form MainView
     */
    public PrincipalVista() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     *
     * @param ListaVacunas
     * @param ListaPCR
     * @param ListaDosis
     * @param ListaPersonas
     */
    public PrincipalVista(List<Vacuna> ListaVacunas, List<PCR> ListaPCR, List<Dosis> ListaDosis, List<Persona> ListaPersonas) {
        initComponents();
        this.setLocationRelativeTo(null);
        inicializarDatos(ListaVacunas, ListaPCR, ListaDosis, ListaPersonas);
    }

    private void inicializarDatos(List<Vacuna> ListaVacunas, List<PCR> ListaPCR, List<Dosis> ListaDosis, List<Persona> ListaPersonas) {
        jPanel10.setVisible(false);
        jPanel11.setVisible(false);
        this.setTitle("AMAPP");
        this.ListaVacunas = ListaVacunas;
        this.ListaPCR = ListaPCR;
        this.ListaDosis = ListaDosis;
        this.ListaPersonas = ListaPersonas;

        this.JList_Vacuna.setModel(CalculosControlador.rellenarListaVacuna(JListModelVacunas, this.ListaVacunas));
        this.JList_PCR.setModel(CalculosControlador.rellenarListaPCR(JListModelPCR, this.ListaPCR));
        this.JList_Dosis.setModel(CalculosControlador.rellenarListaDosis(JListModelDosis, this.ListaDosis));

        this.JTable_Personas.setModel(CalculosControlador.rellenarTablaPersonas(JTableModelPersonas, this.ListaPersonas, this.ListaVacunas, this.ListaPCR, this.ListaDosis));
        this.JTable_Personas.setDefaultEditor(Object.class, null);
        this.JTable_Personas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        if (ListaPersonas.size() != 0) {
            this.JDC_fechaRegistros.setDate(this.ListaPersonas.get(0).getFecha_ingreso());
        }
        
        InicializarGraficas();
    }

    /**
     *
     * @param type
     * @param tabla
     */
    public void NuevaPCR_Dosis_Vacuna(Object type, String tabla) {
        String Nombre = "";
        while (true) {
            Nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de " + tabla, "Ingresar datos", JOptionPane.QUESTION_MESSAGE);
            if (!valid.EsVacio(Nombre)) {
                if (type == Dosis.class) {
                    Dosis NuevaDosis = new Dosis(Nombre);
                    if (coreCrud.Insert(NuevaDosis)) {
                        this.ListaDosis.add(NuevaDosis);
                        this.JList_Dosis.setModel(CalculosControlador.rellenarListaDosis(JListModelDosis, ListaDosis));
                        cons.DatosGuardadosMensaje();
                    } else {
                        cons.MensajeError();
                    }
                } else if (type == PCR.class) {
                    PCR NuevaPCR = new PCR(Nombre);
                    if (coreCrud.Insert(NuevaPCR)) {
                        this.ListaPCR.add(NuevaPCR);
                        this.JList_PCR.setModel(CalculosControlador.rellenarListaPCR(JListModelPCR, ListaPCR));
                        cons.DatosGuardadosMensaje();
                    } else {
                        cons.MensajeError();
                    }
                } else if (type == Vacuna.class) {
                    Vacuna NuevaVacuna = new Vacuna(Nombre);
                    if (coreCrud.Insert(NuevaVacuna)) {
                        this.ListaVacunas.add(NuevaVacuna);
                        this.JList_Vacuna.setModel(CalculosControlador.rellenarListaVacuna(JListModelVacunas, ListaVacunas));
                        cons.DatosGuardadosMensaje();
                    } else {
                        cons.MensajeError();
                    }
                } else {
                    System.out.println("ERORRRRRRRRRRRRRRR");
                }
                break;
            } else if (Nombre == null) {
                break;
            } else {
                cons.ValorNoValidoMensaje();
            }
        }
    }

    /**
     *
     * @param type
     * @param tabla
     */
    public void ModificarPCR_Dosis_Vacuna(Object type, String tabla) {
        if ((this.JList_Dosis.getSelectedIndex() != -1 && this.JList_Dosis.getSelectedIndex() != 0)
                || (this.JList_PCR.getSelectedIndex() != -1 && this.JList_PCR.getSelectedIndex() != 0)
                || (this.JList_Vacuna.getSelectedIndex() != -1 && this.JList_Vacuna.getSelectedIndex() != 0)) {

            String Nombre = "";
            while (true) {
                Nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de " + tabla, "Ingresar datos", JOptionPane.QUESTION_MESSAGE);
                if (!valid.EsVacio(Nombre)) {
                    if (type == Dosis.class) {
                        Dosis NuevaDosis = this.ListaDosis.get(this.JList_Dosis.getSelectedIndex() - 1);
                        NuevaDosis.setNombre(Nombre);
                        if (coreCrud.Update(NuevaDosis)) {
                            for (Dosis d : this.ListaDosis) {
                                if (d.getId() == NuevaDosis.getId()) {
                                    d.setNombre(NuevaDosis.getNombre());
                                }
                            }
                            this.JList_Dosis.setModel(CalculosControlador.rellenarListaDosis(JListModelDosis, ListaDosis));
                            cons.DatosGuardadosMensaje();
                        } else {
                            cons.MensajeError();
                        }
                    } else if (type == PCR.class) {
                        PCR NuevaPCR = this.ListaPCR.get(this.JList_PCR.getSelectedIndex() - 1);
                        NuevaPCR.setNombre(Nombre);
                        if (coreCrud.Update(NuevaPCR)) {
                            for (PCR d : this.ListaPCR) {
                                if (d.getId() == NuevaPCR.getId()) {
                                    d.setNombre(NuevaPCR.getNombre());
                                }
                            }
                            this.JList_PCR.setModel(CalculosControlador.rellenarListaPCR(JListModelPCR, ListaPCR));
                            cons.DatosGuardadosMensaje();
                        } else {
                            cons.MensajeError();
                        }
                    } else if (type == Vacuna.class) {
                        Vacuna NuevaVacuna = this.ListaVacunas.get(this.JList_Vacuna.getSelectedIndex() - 1);
                        NuevaVacuna.setNombre(Nombre);
                        if (coreCrud.Update(NuevaVacuna)) {
                            for (Vacuna d : this.ListaVacunas) {
                                if (d.getId() == NuevaVacuna.getId()) {
                                    d.setNombre(NuevaVacuna.getNombre());
                                }
                            }
                            this.JList_Vacuna.setModel(CalculosControlador.rellenarListaVacuna(JListModelVacunas, ListaVacunas));
                            cons.DatosGuardadosMensaje();
                        } else {
                            cons.MensajeError();
                        }
                    } else {
                        System.out.println("ERORRRRRRRRRRRRRRR");
                    }
                    break;
                } else if (Nombre == null) {
                    break;
                } else {
                    cons.ValorNoValidoMensaje();
                }
            }
        } else {
            cons.NoHaySeleccionMensaje();
        }
    }

    /**
     *
     * @param type
     * @param tabla
     */
    public void EliminarPCR_Dosis_Vacuna(Object type, String tabla) {
        if ((this.JList_Dosis.getSelectedIndex() != -1 && this.JList_Dosis.getSelectedIndex() != 0)
                || (this.JList_PCR.getSelectedIndex() != -1 && this.JList_PCR.getSelectedIndex() != 0)
                || (this.JList_Vacuna.getSelectedIndex() != -1 && this.JList_Vacuna.getSelectedIndex() != 0)) {
            if (type == Dosis.class) {
                Dosis EliminarDosis = this.ListaDosis.get(this.JList_Dosis.getSelectedIndex() - 1);
                if (coreCrud.Delete(EliminarDosis)) {
                    this.ListaDosis.remove(EliminarDosis);
                    this.JList_Dosis.setModel(CalculosControlador.rellenarListaDosis(JListModelDosis, ListaDosis));
                    cons.DatosGuardadosMensaje();
                } else {
                    cons.MensajeError();
                }
            } else if (type == PCR.class) {
                PCR EliminarPCR = this.ListaPCR.get(this.JList_PCR.getSelectedIndex() - 1);
                if (coreCrud.Delete(EliminarPCR)) {
                    this.ListaPCR.remove(EliminarPCR);
                    this.JList_PCR.setModel(CalculosControlador.rellenarListaPCR(JListModelPCR, ListaPCR));
                    cons.DatosGuardadosMensaje();
                } else {
                    cons.MensajeError();
                }
            } else if (type == Vacuna.class) {
                Vacuna EliminarVacuna = this.ListaVacunas.get(this.JList_Vacuna.getSelectedIndex() - 1);
                if (coreCrud.Delete(EliminarVacuna)) {
                    this.ListaVacunas.remove(EliminarVacuna);
                    this.JList_Vacuna.setModel(CalculosControlador.rellenarListaVacuna(JListModelVacunas, ListaVacunas));
                    cons.DatosGuardadosMensaje();
                } else {
                    cons.MensajeError();
                }
            }
        }
    }

    public void InicializarGraficas(){
      Estadistica estadisticas = new Estadistica();
      DefaultCategoryDataset dcd = new DefaultCategoryDataset();
      int[] datos = estadisticas.casosConfirmados();
      
      dcd.setValue(datos[0],"Sospechosos","Sospechosos");
      dcd.setValue(datos[1],"Recuperados","Recuperados");
      dcd.setValue(datos[2],"Fallecidos","Fallecidos");
      JFreeChart grafico_barras = ChartFactory.createBarChart(
            "Casos",  //nombre del grafico
            "Casos confirmados",
            "Casos",  //nombre de la numeracion
            dcd,  //datos del grafico
            PlotOrientation.VERTICAL,  //orientacion
            true,  //legenda de barras individuales por color
            true,  //herramientas
            false  //url
      );
      
      ChartPanel panel = new ChartPanel(grafico_barras);
      panel.setMouseWheelEnabled(true);
      panel.setPreferredSize(new Dimension(350,300));
      
      jPanel8.setLayout(new BorderLayout());
      jPanel8.add(panel, BorderLayout.NORTH);
      
      
      DefaultPieDataset dcd2 = new DefaultPieDataset();
      datos = estadisticas.personasFallecidas();
      dcd2.setValue("18-30 años", datos[0]);
      dcd2.setValue("30-45 años", datos[1]);
      dcd2.setValue("45-60 años", datos[2]);
      dcd2.setValue("60-75 años", datos[3]);
      JFreeChart grafico_barras2 = ChartFactory.createPieChart(
            "Decesos",  //nombre del grafico
            dcd2,  //datos del grafico
            true,  //legenda de barras individuales por color
            true,  //herramientas
            false  //url
      );
      ChartPanel panel2 = new ChartPanel(grafico_barras2);
      panel2.setMouseWheelEnabled(true);
      panel2.setPreferredSize(new Dimension(350,300));
      
      jPanel9.setLayout(new BorderLayout());
      jPanel9.add(panel2, BorderLayout.NORTH);
      
      
      int CasosGrupoEtario = 2;
      DefaultPieDataset dpd = new DefaultPieDataset();
      dpd.setValue("GraficaPastel",CasosGrupoEtario);
      
      JFreeChart grafico_pastel = ChartFactory.createPieChart(
        "Caso ETARIO",      //nombre del grafico
        dpd,      //datos
        true,      //nombre de las categorias
        true,      //herramientas
        false      //generacion de url
      );
      
      ChartPanel panel3 = new ChartPanel(grafico_pastel);
      panel3.setMouseWheelEnabled(true);
      panel3.setPreferredSize(new Dimension(350, 300));
      
      jPanel11.setLayout(new BorderLayout());
      jPanel11.add(panel3, BorderLayout.NORTH);
      
      int DesesosGrupoEtario = 1;
      DefaultPieDataset dpd2 = new DefaultPieDataset();
      dpd2.setValue("GraficaPastel",DesesosGrupoEtario);
      
      JFreeChart grafico_pastel2 = ChartFactory.createPieChart(
        "Caso Desesos",      //nombre del grafico
        dpd2,      //datos
        true,      //nombre de las categorias
        true,      //herramientas
        false      //generacion de url
      );
      
      ChartPanel panel4 = new ChartPanel(grafico_pastel2);
      panel4.setMouseWheelEnabled(true);
      panel4.setPreferredSize(new Dimension(350, 300));
      
      jPanel10.setLayout(new BorderLayout());
      jPanel10.add(panel4, BorderLayout.NORTH);
      
      pack();
      repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JTBP_pestanias = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        JBTN_graficActualizar = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        JL_estadistica = new javax.swing.JLabel();
        JP_registros = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Personas = new javax.swing.JTable();
        JDC_fechaRegistros = new com.toedter.calendar.JDateChooser();
        JBTN_nuevo = new javax.swing.JButton();
        JBTN_modificar = new javax.swing.JButton();
        JBTN_seleccionar = new javax.swing.JButton();
        JBTN_elminar = new javax.swing.JButton();
        JL_tituloRegistros = new javax.swing.JLabel();
        JTF_Refrescar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JList_PCR = new javax.swing.JList<>();
        JBTN_NuevaPCR = new javax.swing.JButton();
        JBTN_ModificarPCR = new javax.swing.JButton();
        JBTN_EliminarPCR = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JList_Dosis = new javax.swing.JList<>();
        JBTN_NuevaDosis = new javax.swing.JButton();
        JBTN_ModificarDosis = new javax.swing.JButton();
        JBTN_EliminarDosis = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        JList_Vacuna = new javax.swing.JList<>();
        JBTN_NuevaVacuna = new javax.swing.JButton();
        JBTN_ModificarVacuna = new javax.swing.JButton();
        JBTN_EliminarVacuna = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JBTN_graficActualizar.setText("Actualizar");
        JBTN_graficActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_graficActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        JL_estadistica.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        JL_estadistica.setText("Estadisticas");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(272, 272, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(580, 580, 580)
                .addComponent(JL_estadistica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBTN_graficActualizar)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_estadistica)
                    .addComponent(JBTN_graficActualizar))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        JTBP_pestanias.addTab("Estadísticas", jPanel1);

        JTable_Personas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTable_Personas);

        JBTN_nuevo.setText("Nuevo");
        JBTN_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_nuevoActionPerformed(evt);
            }
        });

        JBTN_modificar.setText("Modificar");
        JBTN_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_modificarActionPerformed(evt);
            }
        });

        JBTN_seleccionar.setText("Ver Detalles");
        JBTN_seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_seleccionarActionPerformed(evt);
            }
        });

        JBTN_elminar.setText("Eliminar");
        JBTN_elminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_elminarActionPerformed(evt);
            }
        });

        JL_tituloRegistros.setText("Registros: ");

        JTF_Refrescar.setText("Refrescar");
        JTF_Refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTF_RefrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JP_registrosLayout = new javax.swing.GroupLayout(JP_registros);
        JP_registros.setLayout(JP_registrosLayout);
        JP_registrosLayout.setHorizontalGroup(
            JP_registrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_registrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_registrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(JP_registrosLayout.createSequentialGroup()
                        .addComponent(JL_tituloRegistros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTF_Refrescar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 696, Short.MAX_VALUE)
                        .addComponent(JBTN_nuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTN_modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTN_seleccionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTN_elminar)
                        .addGap(5, 5, 5)
                        .addComponent(JDC_fechaRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        JP_registrosLayout.setVerticalGroup(
            JP_registrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JP_registrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JP_registrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JDC_fechaRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JP_registrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JBTN_nuevo)
                        .addComponent(JBTN_modificar)
                        .addComponent(JBTN_seleccionar)
                        .addComponent(JBTN_elminar)
                        .addComponent(JL_tituloRegistros)
                        .addComponent(JTF_Refrescar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );

        JTBP_pestanias.addTab("Registros", JP_registros);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones para PCR"));

        JList_PCR.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        JList_PCR.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                JList_PCRValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(JList_PCR);

        JBTN_NuevaPCR.setText("Nuevo");
        JBTN_NuevaPCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_NuevaPCRActionPerformed(evt);
            }
        });

        JBTN_ModificarPCR.setText("Editar");
        JBTN_ModificarPCR.setEnabled(false);
        JBTN_ModificarPCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_ModificarPCRActionPerformed(evt);
            }
        });

        JBTN_EliminarPCR.setText("Eliminar");
        JBTN_EliminarPCR.setEnabled(false);
        JBTN_EliminarPCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_EliminarPCRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBTN_NuevaPCR)
                    .addComponent(JBTN_ModificarPCR)
                    .addComponent(JBTN_EliminarPCR))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(JBTN_NuevaPCR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTN_ModificarPCR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTN_EliminarPCR, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones para Dosis"));

        JList_Dosis.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        JList_Dosis.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                JList_DosisValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(JList_Dosis);

        JBTN_NuevaDosis.setText("Nuevo");
        JBTN_NuevaDosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_NuevaDosisActionPerformed(evt);
            }
        });

        JBTN_ModificarDosis.setText("Editar");
        JBTN_ModificarDosis.setEnabled(false);
        JBTN_ModificarDosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_ModificarDosisActionPerformed(evt);
            }
        });

        JBTN_EliminarDosis.setText("Eliminar");
        JBTN_EliminarDosis.setEnabled(false);
        JBTN_EliminarDosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_EliminarDosisActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBTN_NuevaDosis)
                    .addComponent(JBTN_ModificarDosis)
                    .addComponent(JBTN_EliminarDosis))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(JBTN_NuevaDosis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTN_ModificarDosis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTN_EliminarDosis, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones para Vacuna"));

        JList_Vacuna.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        JList_Vacuna.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                JList_VacunaValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(JList_Vacuna);

        JBTN_NuevaVacuna.setText("Nuevo");
        JBTN_NuevaVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_NuevaVacunaActionPerformed(evt);
            }
        });

        JBTN_ModificarVacuna.setText("Editar");
        JBTN_ModificarVacuna.setEnabled(false);
        JBTN_ModificarVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_ModificarVacunaActionPerformed(evt);
            }
        });

        JBTN_EliminarVacuna.setText("Eliminar");
        JBTN_EliminarVacuna.setEnabled(false);
        JBTN_EliminarVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTN_EliminarVacunaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBTN_NuevaVacuna)
                    .addComponent(JBTN_ModificarVacuna)
                    .addComponent(JBTN_EliminarVacuna))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(JBTN_NuevaVacuna)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTN_ModificarVacuna)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBTN_EliminarVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 424, Short.MAX_VALUE))
        );

        JTBP_pestanias.addTab("Variables de entorno", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTBP_pestanias)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTBP_pestanias)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBTN_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_nuevoActionPerformed
        // TODO add your handling code here:
        JDialog rfv = new RegistroFormularioVista(ListaVacunas, ListaPCR, ListaDosis, this.JDC_fechaRegistros.getDate());
        rfv.setModal(true);
        rfv.enableInputMethods(true);
        rfv.setVisible(true);
    }//GEN-LAST:event_JBTN_nuevoActionPerformed

    private void JList_DosisValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_JList_DosisValueChanged
        // TODO add your handling code here:
        if (!(this.JList_Dosis.isSelectionEmpty() || this.JList_Dosis.isSelectedIndex(0))) {
            this.JBTN_ModificarDosis.setEnabled(true);
            this.JBTN_EliminarDosis.setEnabled(true);
        } else {
            this.JBTN_ModificarDosis.setEnabled(false);
            this.JBTN_EliminarDosis.setEnabled(false);
        }
    }//GEN-LAST:event_JList_DosisValueChanged

    private void JList_PCRValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_JList_PCRValueChanged
        // TODO add your handling code here:
        if (!(this.JList_PCR.isSelectionEmpty() || this.JList_PCR.isSelectedIndex(0))) {
            this.JBTN_ModificarPCR.setEnabled(true);
            this.JBTN_EliminarPCR.setEnabled(true);
        } else {
            this.JBTN_ModificarPCR.setEnabled(false);
            this.JBTN_EliminarPCR.setEnabled(false);
        }
    }//GEN-LAST:event_JList_PCRValueChanged

    private void JList_VacunaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_JList_VacunaValueChanged
        // TODO add your handling code here:
        if (!(this.JList_Vacuna.isSelectionEmpty() || this.JList_Vacuna.isSelectedIndex(0))) {
            this.JBTN_ModificarVacuna.setEnabled(true);
            this.JBTN_EliminarVacuna.setEnabled(true);
        } else {
            this.JBTN_ModificarVacuna.setEnabled(false);
            this.JBTN_EliminarVacuna.setEnabled(false);
        }
    }//GEN-LAST:event_JList_VacunaValueChanged

    private void JBTN_NuevaDosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_NuevaDosisActionPerformed
        // TODO add your handling code here:
        NuevaPCR_Dosis_Vacuna(Dosis.class, "Dosis");
    }//GEN-LAST:event_JBTN_NuevaDosisActionPerformed

    private void JBTN_NuevaPCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_NuevaPCRActionPerformed
        // TODO add your handling code here:
        NuevaPCR_Dosis_Vacuna(PCR.class, "PCR");
    }//GEN-LAST:event_JBTN_NuevaPCRActionPerformed

    private void JBTN_NuevaVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_NuevaVacunaActionPerformed
        // TODO add your handling code here:
        NuevaPCR_Dosis_Vacuna(Vacuna.class, "Vacuna");
    }//GEN-LAST:event_JBTN_NuevaVacunaActionPerformed

    private void JBTN_ModificarDosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_ModificarDosisActionPerformed
        // TODO add your handling code here:
        ModificarPCR_Dosis_Vacuna(Dosis.class, "Dosis");
    }//GEN-LAST:event_JBTN_ModificarDosisActionPerformed

    private void JBTN_ModificarPCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_ModificarPCRActionPerformed
        // TODO add your handling code here:
        ModificarPCR_Dosis_Vacuna(PCR.class, "PCR");
    }//GEN-LAST:event_JBTN_ModificarPCRActionPerformed

    private void JBTN_ModificarVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_ModificarVacunaActionPerformed
        // TODO add your handling code here:
        ModificarPCR_Dosis_Vacuna(Vacuna.class, "Vacuna");
    }//GEN-LAST:event_JBTN_ModificarVacunaActionPerformed

    private void JBTN_EliminarDosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_EliminarDosisActionPerformed
        // TODO add your handling code here:
        EliminarPCR_Dosis_Vacuna(Dosis.class, "Dosis");
    }//GEN-LAST:event_JBTN_EliminarDosisActionPerformed

    private void JBTN_EliminarPCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_EliminarPCRActionPerformed
        // TODO add your handling code here:
        EliminarPCR_Dosis_Vacuna(PCR.class, "PCR");
    }//GEN-LAST:event_JBTN_EliminarPCRActionPerformed

    private void JBTN_EliminarVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_EliminarVacunaActionPerformed
        // TODO add your handling code here:
        EliminarPCR_Dosis_Vacuna(Vacuna.class, "Vacuna");
    }//GEN-LAST:event_JBTN_EliminarVacunaActionPerformed

    private void JBTN_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_modificarActionPerformed
        // TODO add your handling code here:
        if (this.JTable_Personas.getSelectedRow() != -1) {
            Persona caso = cal.buscarEnListaPersona(ListaPersonas,
                    (int) this.JTable_Personas.getValueAt(
                            this.JTable_Personas.getSelectedRow(),
                            0
                    ));
            List<PersonaDosisVacuna> lpdv = coreCrud.SelectPersonaDosisVacuna(caso.getId());
            List<PersonaPCR> lppcr = coreCrud.SelectPersonaPCR(caso.getId());
            PersonaDosisVacuna pdv = null;
            PersonaPCR ppcr = null;
            if (lpdv.size() != 0) {
                pdv = lpdv.get(0);
            }
            if (lppcr.size() != 0) {
                ppcr = lppcr.get(0);
            }

            JDialog rfv = new RegistroFormularioVista(ListaVacunas, ListaPCR, ListaDosis, caso, pdv, ppcr, this.JDC_fechaRegistros.getDate());
            rfv.setModal(true);
            rfv.enableInputMethods(true);
            rfv.setVisible(true);

        } else {
            cons.NoHaySeleccionMensaje();
        }
    }//GEN-LAST:event_JBTN_modificarActionPerformed

    private void JBTN_seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_seleccionarActionPerformed
        // TODO add your handling code here:
        if (this.JTable_Personas.getSelectedRow() != -1) {
            JDialog rfv = new DetallesPersonaVista(
                    cal.buscarEnListaPersona(
                            ListaPersonas,
                            (int) this.JTable_Personas.getValueAt(
                                    this.JTable_Personas.getSelectedRow(),
                                    0
                            )
                    )
            );
            rfv.setModal(true);
            rfv.enableInputMethods(true);
            rfv.setVisible(true);
        } else {
            cons.NoHaySeleccionMensaje();
        }
    }//GEN-LAST:event_JBTN_seleccionarActionPerformed

    private void JTF_RefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTF_RefrescarActionPerformed
        // TODO add your handling code here:
        this.inicializarDatos(coreCrud.SelectVacuna(), coreCrud.SelectPCR(), coreCrud.SelectDosis(), coreCrud.SelectPersona(new java.sql.Date(this.JDC_fechaRegistros.getCalendar().getTime().getTime())));
    }//GEN-LAST:event_JTF_RefrescarActionPerformed

    private void JBTN_elminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_elminarActionPerformed
        // TODO add your handling code here:
        if (this.JTable_Personas.getSelectedRow() != -1) {
            Persona caso = cal.buscarEnListaPersona(ListaPersonas,
                    (int) this.JTable_Personas.getValueAt(
                            this.JTable_Personas.getSelectedRow(),
                            0
                    ));
            List<PersonaDosisVacuna> lpdv = coreCrud.SelectPersonaDosisVacuna(caso.getId());
            List<PersonaPCR> lppcr = coreCrud.SelectPersonaPCR(caso.getId());
            PersonaDosisVacuna pdv;
            PersonaPCR ppcr;
            if (lpdv != null && !lpdv.isEmpty()) {
                pdv = lpdv.get(0);
                if (coreCrud.Delete(pdv)) {
                    cons.DatosGuardadosMensaje("\nSe realizó la eliminación de Datos: \nDosis, Vacuna de la persona seleccionada");
                } else {
                    cons.MensajeError("\nNo realizó la eliminación de Datos: \nDosis, Vacuna de la persona seleccionada");
                }

            }
            if (lppcr != null && !lppcr.isEmpty()) {
                ppcr = lppcr.get(0);
                if (coreCrud.Delete(ppcr)) {
                    cons.DatosGuardadosMensaje("\nSe realizó la eliminación de Datos: \nPcr de la persona seleccionada");
                } else {
                    cons.MensajeError("\nNo realizó la eliminación de Datos: \nPcr de la persona seleccionada");
                }
            }

            if (coreCrud.Delete(caso)) {
                cons.DatosGuardadosMensaje("\nSe realizó la eliminación de Datos: \nPersona seleccionada");
            } else {
                cons.MensajeError("\nNo realizó la eliminación de Datos: \nPersona seleccionada");
            }
        } else {
            cons.NoHaySeleccionMensaje();
        }
    }//GEN-LAST:event_JBTN_elminarActionPerformed

    private void JBTN_graficActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTN_graficActualizarActionPerformed
        // TODO add your handling code here:   
        InicializarGraficas();
    }//GEN-LAST:event_JBTN_graficActualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBTN_EliminarDosis;
    private javax.swing.JButton JBTN_EliminarPCR;
    private javax.swing.JButton JBTN_EliminarVacuna;
    private javax.swing.JButton JBTN_ModificarDosis;
    private javax.swing.JButton JBTN_ModificarPCR;
    private javax.swing.JButton JBTN_ModificarVacuna;
    private javax.swing.JButton JBTN_NuevaDosis;
    private javax.swing.JButton JBTN_NuevaPCR;
    private javax.swing.JButton JBTN_NuevaVacuna;
    private javax.swing.JButton JBTN_elminar;
    private javax.swing.JButton JBTN_graficActualizar;
    private javax.swing.JButton JBTN_modificar;
    private javax.swing.JButton JBTN_nuevo;
    private javax.swing.JButton JBTN_seleccionar;
    private com.toedter.calendar.JDateChooser JDC_fechaRegistros;
    private javax.swing.JLabel JL_estadistica;
    private javax.swing.JLabel JL_tituloRegistros;
    private javax.swing.JList<String> JList_Dosis;
    private javax.swing.JList<String> JList_PCR;
    private javax.swing.JList<String> JList_Vacuna;
    private javax.swing.JPanel JP_registros;
    private javax.swing.JTabbedPane JTBP_pestanias;
    private javax.swing.JButton JTF_Refrescar;
    private javax.swing.JTable JTable_Personas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
}
