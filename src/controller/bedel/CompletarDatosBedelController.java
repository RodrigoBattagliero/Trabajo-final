/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bedel;

import controller.sedeinterior.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.ActividadDocenteDAO;
import model.BedelDAO;
import model.DatosDocentesDAO;
import model.DatosDocentesDTO;
import model.RegistroUnicoDAO;
import model.RegistroUnicoDTO;
import model.SolicitudDAO;
import model.SolicitudDTO;
import view.PanelActividadDocente;
import view.PanelAdministrarRegistroUnico;
import view.PanelSolicitudesACompletarSedeInterior;

/**
 *
 * @author rodrigo
 */
public class CompletarDatosBedelController {
    private PanelSolicitudesACompletarSedeInterior view;
    private BedelDAO model;
    
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
    private ActividadEfectivaDocenteController actividadDocente;
    
    // Variables propias
    private ArrayList<SolicitudDTO> solicitudesACompletar;
    private ArrayList<DatosDocentesDTO> solicitudesDocentes;
    

    public CompletarDatosBedelController(PanelSolicitudesACompletarSedeInterior view, BedelDAO model) {
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
        this.view.btnAceptar.setEnabled(false);
        this.view.btnCancelar.setEnabled(false);
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
    
    public PanelSolicitudesACompletarSedeInterior getView(){
        return this.view;
    }
    
    public void setEnableBotones(){
        this.view.btnAceptar.setEnabled(true);
        this.view.btnCancelar.setEnabled(true);
    }
    
    public void setRegistroUnico(){
        adminRegistroUnico = new AdministrarRegistroUnico(
                new PanelAdministrarRegistroUnico(), 
                new RegistroUnicoDAO(), 
                this.idRegistroUnico
        );
        adminRegistroUnico.init();
        this.view.pnlAdministrarRegistroUnico.add(adminRegistroUnico.getView());
        this.view.updateUI();
    }
    
    private void setSolicitudesACompletar(){
        registroUnico = new RegistroUnicoDAO();
        solicitud = new SolicitudDAO();
        docentes = new DatosDocentesDAO();
        solicitudesACompletar = new ArrayList();
        solicitudesDocentes = new ArrayList();
        ArrayList<DatosDocentesDTO> docentesAux = null;
        registros = (ArrayList<RegistroUnicoDTO>) registroUnico.selectRegistroUnico(getIdArea(), getIdEstadoEnVerificacion());
        
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
        return 4;
    }
    
    private int getIdEstadoEnVerificacion(){
        return 1;
    }
    
    private void tblSolicitudesMouseClicked(MouseEvent evt) {
        actividadDocente = new ActividadEfectivaDocenteController(
                new PanelActividadDocente(), 
                new ActividadDocenteDAO(),
                this
        );
        actividadDocente.init();
        actividadDocente.setIdSolicitud(getIdRow(this.view.tblSolicitudes.getSelectedRow(),4));
        this.idRegistroUnico = getIdRow(this.view.tblSolicitudes.getSelectedRow(), 5);
        this.view.pActividadDocente.add(actividadDocente.getView());
        this.view.updateUI();
       
    }
    
    private int getIdRow(int fila,int columna){
        return Integer.parseInt((String)this.view.tblSolicitudes.getModel().getValueAt(fila, columna));
    }
    
    private void btnAceptarActionPerformed(ActionEvent evt) {
       //this.designacionDocenteController.guardarDatos();
    }
    
   
}
