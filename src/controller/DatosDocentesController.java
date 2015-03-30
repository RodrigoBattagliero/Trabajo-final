/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.DatosDocentesDAO;
import model.DatosTrasladoDAO;
import view.PanelCompletarDatosDeDocentes;
import view.PanelCompletarDatosDeTraslado;
import view.PanelCompletarDatosDeTraslado2;

/**
 *
 * @author rodrigo
 */
public class DatosDocentesController {
    
    PanelCompletarDatosDeDocentes view;
    DatosDocentesDAO model;
    
    public DatosDocentesController(PanelCompletarDatosDeDocentes v, DatosDocentesDAO m) {
        this.view = v;
        this.model = m;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
    }
    
    public void btnAceptarActionPerformed(java.awt.event.ActionEvent evt){
        PanelCompletarDatosDeTraslado2 datosTrasladoView = new PanelCompletarDatosDeTraslado2();
        DatosTrasladoDAO datosTrasladoDAO = new DatosTrasladoDAO();
        DatosTrasladoController datosTrasladoController = new DatosTrasladoController(datosTrasladoView,datosTrasladoDAO);
        datosTrasladoController.init();
        
        this.view.pDatosDeTraslado.add(datosTrasladoView);
        this.view.pDatosDeTraslado.setVisible(true);
        disableFields();
        this.view.updateUI();
    }
    
    public void disableFields(){
        this.view.cbxDeptoAcademico.setEnabled(false);
        this.view.txtApellido.setEnabled(false);
        this.view.txtNombre.setEnabled(false);
        this.view.txtDni.setEnabled(false);
        this.view.txtTelefono.setEnabled(false);
        this.view.txtEmail.setEnabled(false);
        this.view.txtLugarDeResidencia.setEnabled(false);
        this.view.txtMotivoDeComision.setEnabled(false);
        this.view.txtFechaHoraIncio.setEnabled(false);
        this.view.txtFechaHoraFinalizacion.setEnabled(false);
        this.view.txtObservaciones.setEnabled(false);
        this.view.btnAceptar.setEnabled(false);
        this.view.btnCancelar.setEnabled(false);
    }
    
    
}