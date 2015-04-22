/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.bedel;

import controller.sedeinterior.*;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import model.ActividadDocenteDAO;
import model.ActividadDocenteDTO;
import resources.DateManager;
import view.PanelActividadDocente;

/**
 *
 * @author rodrigo
 */
public class ActividadEfectivaDocenteController {
    
    private CompletarDatosBedelController bedel;
    private PanelActividadDocente view;
    private ActividadDocenteDAO model;
    
    private int idSolicitud;
    private String asignatura;
    private DateManager fecha;
    private String observaciones;
    
    private ActividadDocenteDTO actividad;

    public ActividadEfectivaDocenteController(PanelActividadDocente view, ActividadDocenteDAO model,CompletarDatosBedelController bedel) {
        this.view = view;
        this.model = model;
        this.bedel = bedel;
        
        this.idSolicitud = 0;
        asignatura = null;
        fecha = null;
        observaciones = null;
        
        actividad = null;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }

        });
    }
    
    public PanelActividadDocente getView(){
        return this.view;
    }
    
    public void setIdSolicitud(int setIdSolicitud){
        this.idSolicitud = setIdSolicitud;
    }
    
    public void guardarDatos(){
        model.create(actividad);
    }
    
    private void btnAceptarActionPerformed(ActionEvent evt) {
        if(verificarDatos()){
            //sedeInterior.setEnableBotones();
            enableFields();
            datosTemporales();
            guardarDatos();
            bedel.setRegistroUnico();
        }
    }
    
    private boolean verificarDatos(){
        boolean b = true;
        asignatura = view.txtAsignatura.getText();
        observaciones = view.txtObservaciones.getText();
        try{
            fecha = new DateManager(view.txtFecha.getDate());
        }catch(NullPointerException e){
            fecha = new DateManager("00-00-000 00:00:00");
            b = false;
        }
        if("".equals(asignatura)) b = false;
        
        if(b){
            return true;
        }else{
            if(this.observaciones.length() > 0){
                int op = JOptionPane.showConfirmDialog(this.view, 
                        "No completo los campos obligatorios. ¿Desea continuar?",
                        "Fecha de presentación incorrecta.",
                        0);
                
                if(op == 0)
                    return true;
                if(op == 1)
                    return false;
            }else{
                JOptionPane.showMessageDialog(view, "No completo los campos obligatorios.\nSi desea procesar la solicitud de todo modos, agregar una OBSERVACIÓN.");
                return false;
            }
        }
        
        return b;
    }
    
    private void enableFields(){
        view.txtAsignatura.setEnabled(false);
        view.txtFecha.setEnabled(false);
        view.txtObservaciones.setEnabled(false);
        view.btnAceptar.setEnabled(false);
    }
    
    private void datosTemporales(){
        actividad = new ActividadDocenteDTO(0, idSolicitud, asignatura, fecha, observaciones);
    }
    
}
