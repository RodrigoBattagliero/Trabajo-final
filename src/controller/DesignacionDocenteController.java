/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DatosDocentesDTO;
import model.DesignacionDocenteDAO;
import model.DesignacionDocenteDTO;
import resources.DateManager;
import view.PanelDesignacionDocente;

/**
 *
 * @author rodrigo
 */
public class DesignacionDocenteController {
   
    private PanelDesignacionDocente view;
    private DesignacionDocenteDAO model;
    
    
    // Datos Propios
    private int idSolicitud;
    private String numeroResolucion;
    private String cargo;
    private String condicion;
    private String dedicacion;
    private DateManager desde;
    private DateManager hasta;
    private String observaciones;
    

    public DesignacionDocenteController(PanelDesignacionDocente view, DesignacionDocenteDAO model) {
        this.view = view;
        this.model = model;
        
        this.idSolicitud = 0;
        this.numeroResolucion = null;
        this.cargo = null;
        this.condicion = null;
        this.dedicacion = null;
        this.desde = null;
        this.hasta = null;
        this.observaciones = null;
    }
    
    public void init(){
        this.view.setVisible(true);
         this.view.btnIngresoManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresoManualActionPerformed(evt);
            }
        });
        this.view.tblDatosKakan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosKakanMouseClicked(evt);
            }
        });
        this.view.btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
    }
    
    public PanelDesignacionDocente getView(){
        return this.view;
    }
    
    public void setIdSolicitud(int idSolicitud){
        this.idSolicitud = idSolicitud;
    }
    
    public void setRow(DatosDocentesDTO datosDocente){
       ArrayList<DesignacionDocenteDTO> designaciones = (ArrayList) 
               setDesignacionesKakan(datosDocente.getNombre(),datosDocente.getApellido());
       
       int cant = 0;
       
       if(designaciones != null)
           cant = designaciones.size();

       
       DefaultTableModel modelo = (DefaultTableModel) this.view.tblDatosKakan.getModel();
        for (int i = 0; i < cant; i++) {
            String fila[] = {
                String.valueOf(""),
                String.valueOf(""),
                String.valueOf(""),
                String.valueOf(""),
                String.valueOf(""),
                String.valueOf("")
            };
            modelo.addRow(fila);
        }
    }
    
    private ArrayList<DesignacionDocenteDTO> setDesignacionesKakan(String nombre,String apellido){
        return null;
    }
    
    private void btnIngresoManualActionPerformed(ActionEvent evt) {
        disableDatosDesdeKakan();
        enableDatosManual();
    }
    
    private void tblDatosKakanMouseClicked(MouseEvent evt) {
        pasarDatos(this.view.tblDatosKakan.getSelectedRow());
        enableDatosManual();
        disableDatosDesdeKakan();
    }
    
    private void pasarDatos(int fila){
        DefaultTableModel modelo = (DefaultTableModel) this.view.tblDatosKakan.getModel();
        try{
            view.txtNumeroResolucion.setText(String.valueOf(modelo.getValueAt(fila, 0)));
            view.txtCargo.setText(String.valueOf(modelo.getValueAt(fila, 1)));
            view.txtCondicion.setText(String.valueOf(modelo.getValueAt(fila, 2)));
            view.txtDedicacion.setText(String.valueOf(modelo.getValueAt(fila, 3)));
            view.txtDesde.setDate(new Date(new DateManager(String.valueOf(modelo.getValueAt(fila, 4))).getFechaLong()));
            view.txtHasta.setDate(new Date(new DateManager(String.valueOf(modelo.getValueAt(fila, 5))).getFechaLong()));
            
        }catch(NullPointerException e){
            
        }
    }
    
    private void disableDatosDesdeKakan(){
        this.view.tblDatosKakan.setEnabled(false);
        this.view.btnIngresoManual.setEnabled(false);
        this.view.jPanel10.setEnabled(false);
        this.view.tblDatosKakan.setEnabled(false);
    }
    
    private void enableDatosDesdeKakan(){
        this.view.tblDatosKakan.setEnabled(true);
        this.view.btnIngresoManual.setEnabled(true);
        this.view.jPanel10.setEnabled(true);
        this.view.tblDatosKakan.setEnabled(true);
    }
    
    private void enableDatosManual(){
        this.view.txtNumeroResolucion.setEnabled(true);
        this.view.txtCargo.setEnabled(true);
        this.view.txtCondicion.setEnabled(true);
        this.view.txtDedicacion.setEnabled(true);
        this.view.txtDesde.setEnabled(true);
        this.view.txtHasta.setEnabled(true);
        this.view.jPanel11.setEnabled(true);
        this.view.jLabel7.setEnabled(true);
        this.view.jLabel8.setEnabled(true);
        this.view.jLabel9.setEnabled(true);
        this.view.jLabel10.setEnabled(true);
        this.view.jLabel11.setEnabled(true);
        this.view.jLabel12.setEnabled(true);
        this.view.btnVolver.setEnabled(true);
    }
    
    private void disableDatosManual(){
        this.view.txtNumeroResolucion.setEnabled(false);
        this.view.txtCargo.setEnabled(false);
        this.view.txtCondicion.setEnabled(false);
        this.view.txtDedicacion.setEnabled(false);
        this.view.txtDesde.setEnabled(false);
        this.view.txtHasta.setEnabled(false);
        this.view.jPanel11.setEnabled(false);
        this.view.jLabel7.setEnabled(false);
        this.view.jLabel8.setEnabled(false);
        this.view.jLabel9.setEnabled(false);
        this.view.jLabel10.setEnabled(false);
        this.view.jLabel11.setEnabled(false);
        this.view.jLabel12.setEnabled(false);
        this.view.btnVolver.setEnabled(false);
    }
    
    private void limpiarCamposManuales(){
        this.view.txtNumeroResolucion.setText("");
        this.view.txtCargo.setText("");
        this.view.txtCondicion.setText("");
        this.view.txtDedicacion.setText("");
        this.view.txtDesde.setDate(null);
        this.view.txtHasta.setDate(null);
    }
    
    private void btnVolverActionPerformed(ActionEvent evt){
        limpiarCamposManuales();
        enableDatosDesdeKakan();
        disableDatosManual();
    }
    
    private boolean comprobarDatos(){
        boolean b = true;
        
        numeroResolucion = view.txtNumeroResolucion.getText();
        dedicacion = view.txtDedicacion.getText();
        condicion = view.txtCondicion.getText();
        cargo = view.txtCargo.getText();
        try{
         desde = new DateManager(view.txtDesde.getDate());   
        }catch(NullPointerException e){
            desde = new DateManager("00-00-0000 00:00:00");
        }
        
        try{
            hasta = new DateManager(view.txtHasta.getDate());
        }catch(NullPointerException e){
            hasta = new DateManager("00-00-0000 00:00:00");
        }
        observaciones = view.txtObservaciones.getText();
        
        if(numeroResolucion.isEmpty() || dedicacion.isEmpty() || condicion.isEmpty() || cargo.isEmpty() || desde == null || hasta == null  )
               b = false;
        
        if(b){
         return true;   
        }else{
            if(this.observaciones.length() > 0){
                int op = JOptionPane.showConfirmDialog(this.view, 
                        "Faltan campos obligatorios. ¿Desea continuar?",
                        "Fecha de presentación incorrecta.",
                        0);
                
                if(op == 0)
                    return true;
                if(op == 1)
                    return false;
            }else{
                JOptionPane.showMessageDialog(view, 
                        "Los campos obligatorios no están completos."
                       + "\nSi desea procesar la solicitud de todo modos, agregar una OBSERVACIÓN.");
                return false;
            }
        }
        return b;
    }
    
    public void guardarDatos(){
        if(comprobarDatos()){
            model.create(new DesignacionDocenteDTO(
                    0, 
                    numeroResolucion, 
                    cargo, 
                    desde, 
                    hasta, 
                    dedicacion, 
                    observaciones, 
                    idSolicitud)
            );
        }
    }
}

