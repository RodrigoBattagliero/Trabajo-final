/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import model.RegistroUnicoDAO;
import view.PanelCrearRegistroUnico;

/**
 *
 * @author rodrigo
 */
public class CrearRegistroUnicoController {
    private IniciarSolicitudController solicitudController;
    
    private PanelCrearRegistroUnico view;
    private RegistroUnicoDAO model;

    public CrearRegistroUnicoController(PanelCrearRegistroUnico view, RegistroUnicoDAO model,IniciarSolicitudController solicitudController) {
        this.view = view;
        this.model = model;
        this.solicitudController = solicitudController;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
    }
    
    private void btnAceptarActionPerformed(ActionEvent evt) {
        if(this.solicitudController.confirmar() == 0)
            JOptionPane.showMessageDialog(null, "Se creo la solicitud con éxito");
        else
            JOptionPane.showMessageDialog(null, "Se cancelo el registro");
    }
    
    
    
}
