/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.MouseEvent;
import model.HistorialSolicitudesDAO;
import view.PanelHistorialSolicitudesProcesadas;

/**
 *
 * @author rodrigo
 */
public class historialSolicitudesController {
    
    private PanelHistorialSolicitudesProcesadas view;
    private HistorialSolicitudesDAO model;

    public historialSolicitudesController(PanelHistorialSolicitudesProcesadas view, HistorialSolicitudesDAO model) {
        this.view = view;
        this.model = model;
        
        this.view.tblSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSolicitudesMouseClicked(evt);
            }
        });
    }
    
    public void init(){
        this.view.setVisible(true);
    }
    
    private void tblSolicitudesMouseClicked(MouseEvent evt) {
        
    }
    
}
