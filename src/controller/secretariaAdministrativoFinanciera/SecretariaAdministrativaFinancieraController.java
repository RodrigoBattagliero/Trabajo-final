/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.secretariaAdministrativoFinanciera;

import model.HistorialSolicitudesDAO;
import model.RegistroUnicoDAO;
import model.RendicionDeCuentasDAO;
import model.SecretariaAdministrativoFinancieraDAO;
import view.PanelConfirmarLoteDeSolicitudesProcesadas;
import view.PanelHistorialSolicitudesProcesadas;
import view.PanelSolicitudesACompletarSedeInterior;
import view.VentanaSecretariaAdministrativoFinanciera;

/**
 *
 * @author rodrigo
 */
public class SecretariaAdministrativaFinancieraController {
    
    private VentanaSecretariaAdministrativoFinanciera view;
    private SecretariaAdministrativoFinancieraDAO modelDAO;
    
   
    

    public SecretariaAdministrativaFinancieraController(VentanaSecretariaAdministrativoFinanciera View, SecretariaAdministrativoFinancieraDAO modelDAO) {
        this.view = View;
        this.modelDAO = modelDAO;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.setLocationRelativeTo(null);
    
        this.view.mItemSolicitudesACompletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemSolicitudesACompletarActionPerformed(evt);
            }
        });
        this.view.mItemConfirmarLoteDeSolicitudesProcesadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemConfirmarLoteDeSolicitudesProcesadasActionPerformed(evt);
            }
        });
        this.view.mItemListarHistorialDeSolicitudesProcesadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemListarHistorialDeSolicitudesProcesadasActionPerformed(evt);
            }
        });
        this.view.menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        
        
    }
    
    
    /*
    *
    * Listener de botones
    *
    */
    
    public void mItemSolicitudesACompletarActionPerformed(java.awt.event.ActionEvent evt){
        setPanelSolicitudesACompletar();
    }

    public void mItemConfirmarLoteDeSolicitudesProcesadasActionPerformed(java.awt.event.ActionEvent evt){
        setPanelConfirmarLoteDeSolicitudesProcesadas();
    }
    
    public void mItemListarHistorialDeSolicitudesProcesadasActionPerformed(java.awt.event.ActionEvent evt){
        setPanelListarSolicitudesProcesadas();
    }
     
    public void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt){
        this.view.removeAll();
        System.exit(0);
    }
    
    /*
    *
    * Metodos para mostrar los paneles 
    *
    */
    
    public void setPanelConfirmarLoteDeSolicitudesProcesadas(){
        ConfirmarLoteController confirmarLoteController = new ConfirmarLoteController(new PanelConfirmarLoteDeSolicitudesProcesadas(), new RegistroUnicoDAO());
        confirmarLoteController.init();
        this.view.jScrollPane1.setViewportView(confirmarLoteController.getView());
        
    }

   
    public void setPanelListarSolicitudesProcesadas(){
        historialSolicitudesController historialSolicitudesController = new historialSolicitudesController(
                new PanelHistorialSolicitudesProcesadas(), 
                new HistorialSolicitudesDAO());
        historialSolicitudesController.init();
        this.view.jScrollPane1.setViewportView(historialSolicitudesController.getView());
    }

    public void setPanelSolicitudesACompletar(){
        CompletarDatosSAF solcitudesACompletarController = new CompletarDatosSAF(
                new PanelSolicitudesACompletarSedeInterior(),
                new SecretariaAdministrativoFinancieraDAO()
             );
        solcitudesACompletarController.init();
        
        this.view.jScrollPane1.setViewportView(solcitudesACompletarController.getView());
        
    }

    
}
