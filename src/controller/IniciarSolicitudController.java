/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.SolicitudDAO;
import view.PanelCompletarDatosDeDocentes;
import view.PanelIniciarSolicitud;

/**
 *
 * @author rodrigo
 */
public class IniciarSolicitudController {
    PanelIniciarSolicitud view;
    SolicitudDAO model;

    public IniciarSolicitudController(PanelIniciarSolicitud v, SolicitudDAO m) {
        this.view = v;
        this.model = m;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }
    
    public void btnGuardarActionPerformed(java.awt.event.ActionEvent evt){
        PanelCompletarDatosDeDocentes datosDocente = new PanelCompletarDatosDeDocentes();
        datosDocente.setVisible(true);
        this.view.pDatosDocentes.add(datosDocente);
        this.view.pDatosDocentes.setVisible(true);
        datosDocente.setVisible(true);
        disableFields();
        this.view.updateUI();
    }
    
    public void disableFields(){
        this.view.txtFechaDePresentacion.setEnabled(false);
        this.view.txtNumeroDeSolicitud.setEnabled(false);
        this.view.txtObservaciones.setEditable(false);
        this.view.btnGuardar.setEnabled(false);
        this.view.btnCancelar.setEnabled(false);
    }
    
    
}
