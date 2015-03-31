/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import model.DatosAlojamientoComidaDAO;
import model.DatosTrasladoDAO;
import view.PanelCompletarDatosDeAlojamientoOComida;
import view.PanelCompletarDatosDeTraslado;

/**
 *
 * @author rodrigo
 */
public class DatosTrasladoController {
    private IniciarSolicitudController solicitudController;
    
    private PanelCompletarDatosDeTraslado view;
    private DatosTrasladoDAO model;

    public DatosTrasladoController(PanelCompletarDatosDeTraslado view, DatosTrasladoDAO model,IniciarSolicitudController solicitudController) {
        this.view = view;
        this.model = model;
        this.solicitudController = solicitudController;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnAgregarDatosAlojamientoComida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDatosAlojamientoComidaActionPerformed(evt);
            }
        });
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarPerformed(evt);
            }
        });
    }
    
    public void btnAgregarDatosAlojamientoComidaActionPerformed(java.awt.event.ActionEvent evt){
        PanelCompletarDatosDeAlojamientoOComida datosAlojamientoView = new PanelCompletarDatosDeAlojamientoOComida();
        DatosAlojamientoComidaDAO datosAlojamientoDAO = new DatosAlojamientoComidaDAO();
        DatosAlojamientoComidaController datosAlojamientoController = new DatosAlojamientoComidaController(datosAlojamientoView, datosAlojamientoDAO);
        datosAlojamientoController.init();
        
        this.view.pnlDatosAlojamientoComida.add(datosAlojamientoView);
        this.view.updateUI();
        
        disableFields();
        if(datosAlojamientoController.verificar()){
            this.view.btnAceptar.setEnabled(true);
            this.view.btnCancelar.setEnabled(true);
        }
    }
    
    private void btnAceptarPerformed(ActionEvent evt) {
        this.solicitudController.crearRegistroUnico();
    }
    
    public void disableFields(){
        this.view.tblDatosTraslado.setEnabled(false);
        this.view.btnAgregarDatosAlojamientoComida.setEnabled(false);
        this.view.btnAceptar.setEnabled(false);
        this.view.btnCancelar.setEnabled(false);
    }
}
