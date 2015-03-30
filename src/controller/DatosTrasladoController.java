/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.DatosTrasladoDAO;
import view.PanelCompletarDatosDeTraslado;
import view.PanelCompletarDatosDeTraslado2;

/**
 *
 * @author rodrigo
 */
public class DatosTrasladoController {
    PanelCompletarDatosDeTraslado2 view;
    DatosTrasladoDAO model;

    public DatosTrasladoController(PanelCompletarDatosDeTraslado2 view, DatosTrasladoDAO model) {
        this.view = view;
        this.model = model;
    }
    
    public void init(){
        this.view.setVisible(true);
        /*
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        */
    }
    
    public void btnAceptarActionPerformed(java.awt.event.ActionEvent evt){
        int option = JOptionPane.showConfirmDialog(view, "¿Desea agregar otro comprobante?");
    }
}
