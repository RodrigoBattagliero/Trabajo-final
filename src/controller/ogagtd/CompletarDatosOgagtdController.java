/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ogagtd;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.CompletarDatosOgagtdDAO;
import model.DatosDocentesDAO;
import model.DatosDocentesDTO;
import model.DesignacionDocenteDAO;
import model.RegistroUnicoDAO;
import model.RegistroUnicoDTO;
import model.SolicitudDAO;
import model.SolicitudDTO;
import view.PanelAdministrarRegistroUnico;
import view.PanelDesignacionDocente;
import view.PanelSolicitudesACompletarOGAGTD;

/**
 *
 * @author rodrigo
 */
public class CompletarDatosOgagtdController {
    private PanelSolicitudesACompletarOGAGTD view;
    private CompletarDatosOgagtdDAO model;
    
    // Registro unico
    private RegistroUnicoDAO registroUnico;
    
    private int idRegistroUnico;
    ArrayList<RegistroUnicoDTO> registros;
    private AdministrarRegistroUnico adminRegistroUnico;
    
    
    // Solicitud
    private SolicitudDAO solicitud;
    
    // Docentes
    private DatosDocentesDAO docentes;
    
    // Datos designacion
    private PanelDesignacionDocente designacionDocenteView;
    private DesignacionDocenteDAO designacionDocenteModel;
    private DesignacionDocenteController designacionDocenteController;
    
    // Variables propias
    private ArrayList<SolicitudDTO> solicitudesACompletar;
    private ArrayList<DatosDocentesDTO> solicitudesDocentes;
    

    public CompletarDatosOgagtdController(PanelSolicitudesACompletarOGAGTD view, CompletarDatosOgagtdDAO model) {
        this.view = view;
        this.model = model;
        
        this.registros = null;
        this.solicitud = null;
        this.registroUnico = null;
        this.solicitudesACompletar = null;
        this.docentes = null;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.tblSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSolicitudesMouseClicked(evt);
            }
        });
        
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        
        this.setSolicitudesACompletar();
        int cant = solicitudesACompletar.size();
        DefaultTableModel modelo = (DefaultTableModel) this.view.tblSolicitudes.getModel();
        for (int i = 0; i < cant; i++) {
            String row[] = {
                String.valueOf(solicitudesACompletar.get(i).getNumero_solicutd()),
                String.valueOf(solicitudesDocentes.get(i).getApellido()+", "+solicitudesDocentes.get(i).getNombre()),
                String.valueOf(solicitudesACompletar.get(i).getFecha_alta().getFechaString()),
                String.valueOf(solicitudesDocentes.get(i).getId()),
                String.valueOf(solicitudesACompletar.get(i).getId()),
                String.valueOf(registros.get(i).getId())
            };
            modelo.addRow(row);
        }
        
    }
    
    public PanelSolicitudesACompletarOGAGTD getView(){
        return this.view;
    }
    
    private void setSolicitudesACompletar(){
        registroUnico = new RegistroUnicoDAO();
        solicitud = new SolicitudDAO();
        docentes = new DatosDocentesDAO();
        solicitudesACompletar = new ArrayList();
        solicitudesDocentes = new ArrayList();
        ArrayList<DatosDocentesDTO> docentesAux = null;
        registros = (ArrayList<RegistroUnicoDTO>) registroUnico.selectRegistroUnicoTipo(getIdArea(), getIdEstadoEnVerificacion(),getTipo());
        
        int cant = registros.size();
        for (int i = 0; i < cant; i++) {
                
                solicitudesACompletar.add(
                        solicitud.selectOne(
                                registros.get(i).getId_solicitud()
                        )
                );
            
            docentesAux = (ArrayList<DatosDocentesDTO>) docentes.selectRelated(registros.get(i).getId_solicitud());
            solicitudesDocentes.add(docentesAux.get(0));
        }
        
    }
    
    private int getIdArea(){
        return 1;
    }
    
    private int getIdEstadoEnVerificacion(){
        return 1;
    }
    private int getTipo(){
        return 1;
    }
    private void tblSolicitudesMouseClicked(MouseEvent evt) {
        designacionDocenteController = new DesignacionDocenteController(
                new PanelDesignacionDocente(), 
                new DesignacionDocenteDAO()
        );
        designacionDocenteController.init();
        DatosDocentesDTO datosDoc = new DatosDocentesDAO().selectOne(getIdRow(this.view.tblSolicitudes.getSelectedRow(),3));
        designacionDocenteController.setIdSolicitud(getIdRow(this.view.tblSolicitudes.getSelectedRow(),4));
        designacionDocenteController.setRow(datosDoc);
        this.idRegistroUnico = getIdRow(this.view.tblSolicitudes.getSelectedRow(), 5);
        this.view.pDatosDesignacion.add(designacionDocenteController.getView());
        this.view.updateUI();
       
    }
    
    private int getIdRow(int fila,int columna){
        return Integer.parseInt((String)this.view.tblSolicitudes.getModel().getValueAt(fila, columna));
    }
    
    private void btnAceptarActionPerformed(ActionEvent evt) {
       //this.designacionDocenteController.guardarDatos();
        adminRegistroUnico = new AdministrarRegistroUnico(
                new PanelAdministrarRegistroUnico(), 
                new RegistroUnicoDAO(), 
                this.idRegistroUnico
        );
        adminRegistroUnico.init();
        this.view.pnlAdministrarRegistroUnico.add(adminRegistroUnico.getView());
        this.view.updateUI();
    }
    
   
}
