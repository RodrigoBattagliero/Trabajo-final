/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanastesis.view.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import ventanastesis.view.PanelSolicitudesACompletarOGAGTD;

/**
 *
 * @author rodrigo
 */

public class SolicitudesACompletarController implements ActionListener {
    
    private PanelSolicitudesACompletarOGAGTD SolicitudesACompletarOGAGTD;
    
    public SolicitudesACompletarController(PanelSolicitudesACompletarOGAGTD vista){
        SolicitudesACompletarOGAGTD = vista;
        SolicitudesACompletarOGAGTD.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(SolicitudesACompletarOGAGTD, "Mensaje");
    }
}
