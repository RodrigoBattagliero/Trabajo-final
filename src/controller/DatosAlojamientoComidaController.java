/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import model.DatosAlojamientoComidaDAO;
import view.PanelCompletarDatosDeAlojamientoOComida;

/**
 *
 * @author rodrigo
 */
public class DatosAlojamientoComidaController {
    private PanelCompletarDatosDeAlojamientoOComida view;
    private DatosAlojamientoComidaDAO model;

    public DatosAlojamientoComidaController(PanelCompletarDatosDeAlojamientoOComida view, DatosAlojamientoComidaDAO model) {
        this.view = view;
        this.model = model;
    }
    
   public void init(){
       this.view.setVisible(true);
       this.view.btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
   }
   
   private void btnAgregarActionPerformed(ActionEvent evt) {
        disableFields();
   }

    private void disableFields() {
        this.view.tblDatosAlojamientoComida.setEnabled(false);
        this.view.btnAgregar.setEnabled(false);
        this.view.btnCancelar.setEnabled(false);
    }
    
    public boolean verificar(){
        return true;
    }
}
