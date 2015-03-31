/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.OgagtdDAO;
import model.SolicitudDAO;
import view.PanelAdministrarRegistroUnico;
import view.PanelCompletarDatosDeAlojamientoOComidaVIEJO;
import view.PanelCompletarDatosDeDocentes;
import view.PanelCompletarDatosDeTrasladoVIEJO;
import view.PanelConfirmarLoteDeSolicitudesProcesadas;
import view.PanelCrearRegistroUnico;
import view.PanelIniciarSolicitud;
import view.PanelListarSolicitudesProcesadas;
import view.PanelSolicitudesACompletarOGAGTD;
import view.VentanaOgagtd;

/**
 *
 * @author rodrigo
 */
public class OgagtdController {
    
    private VentanaOgagtd View;
    private OgagtdDAO modelDAO;
    
    //
    private PanelAdministrarRegistroUnico AdministrarRegistroUnico;
    //
    private PanelCompletarDatosDeAlojamientoOComidaVIEJO CompletarDatosDeAlojamientoOComida;
    //
    private PanelCompletarDatosDeDocentes CompletarDatosDeDocentes;
    //
    private PanelCompletarDatosDeTrasladoVIEJO CompletarDatosDeTrasalado;
    //
    private PanelConfirmarLoteDeSolicitudesProcesadas ConfirmarLoteDeSolicitudesProcesadas;
    //
    private PanelCrearRegistroUnico CrearRegistroUnico;
    
    // MVC Iniciar solicitud
    private PanelIniciarSolicitud IniciarSolicitud;
    private IniciarSolicitudController inicarSolicitudController;
    private SolicitudDAO inciarSolicitudDAO;
    
    //
    private PanelListarSolicitudesProcesadas ListarSolicitudesProcesadas;
    //
    private PanelSolicitudesACompletarOGAGTD SolicitudesACompletarOGAGTD;
    //

    public OgagtdController(VentanaOgagtd View, OgagtdDAO modelDAO) {
        this.View = View;
        this.modelDAO = modelDAO;
    }
    
    public void init(){
        this.View.setVisible(true);
        this.View.setLocationRelativeTo(null);
        this.View.mItemIniciarNuevaSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemIniciarNuevaSolicitudActionPerformed(evt);
            }
        });
        this.View.mItemSolicitudesACompletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemSolicitudesACompletarActionPerformed(evt);
            }
        });
        this.View.mItemConfirmarLoteDeSolicitudesProcesadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemConfirmarLoteDeSolicitudesProcesadasActionPerformed(evt);
            }
        });
        this.View.mItemListarHistorialDeSolicitudesProcesadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemListarHistorialDeSolicitudesProcesadasActionPerformed(evt);
            }
        });
        this.View.menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
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
    public void mItemIniciarNuevaSolicitudActionPerformed(java.awt.event.ActionEvent evt){
        setPanelIniciarSolicitud();
    }
    
    public void mItemSolicitudesACompletarActionPerformed(java.awt.event.ActionEvent evt){
        this.View.removeAll();
        setPanelSolicitudesACompletarOGAGTD();
    }

    
    public void mItemConfirmarLoteDeSolicitudesProcesadasActionPerformed(java.awt.event.ActionEvent evt){
        setPanelConfirmarLoteDeSolicitudesProcesadas();
    }
    
    public void mItemListarHistorialDeSolicitudesProcesadasActionPerformed(java.awt.event.ActionEvent evt){
        this.View.removeAll();
        setPanelListarSolicitudesProcesadas();
    }
    
    public void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt){
        this.View.removeAll();
        System.exit(0);
    }
    
    /*
    *
    * Metodos para mostrar los paneles 
    *
    */
    public void setPanelCompletarDatosDeAlojamientoOComida(){
        CompletarDatosDeAlojamientoOComida = new PanelCompletarDatosDeAlojamientoOComidaVIEJO();
        CompletarDatosDeAlojamientoOComida.setVisible(true);
        this.View.jScrollPane1.setViewportView(CompletarDatosDeAlojamientoOComida);
    }
    
    public void setPanelCompletarDatosDeDocentes(){
        CompletarDatosDeDocentes = new PanelCompletarDatosDeDocentes();
        CompletarDatosDeDocentes.setVisible(true);
        this.View.jScrollPane1.setViewportView(CompletarDatosDeDocentes);
    }
    
    public void setCompletarDatosDeTrasalado(){
        CompletarDatosDeTrasalado = new PanelCompletarDatosDeTrasladoVIEJO();
        CompletarDatosDeTrasalado.setVisible(true);
        this.View.jScrollPane1.setViewportView(CompletarDatosDeTrasalado);
    }
    
    public void setPanelConfirmarLoteDeSolicitudesProcesadas(){
        ConfirmarLoteDeSolicitudesProcesadas = new PanelConfirmarLoteDeSolicitudesProcesadas();
        ConfirmarLoteDeSolicitudesProcesadas.setVisible(true);
        this.View.jScrollPane1.setViewportView(ConfirmarLoteDeSolicitudesProcesadas);
    }
    
    public void setPanelCrearRegistroUnico(){
        CrearRegistroUnico = new PanelCrearRegistroUnico();
        CrearRegistroUnico.setVisible(true);
        this.View.jScrollPane1.setViewportView(CrearRegistroUnico);
    }
    
     public void setPanelIniciarSolicitud(){
        IniciarSolicitud = new PanelIniciarSolicitud();
        inciarSolicitudDAO = new SolicitudDAO();
        inicarSolicitudController = new IniciarSolicitudController(IniciarSolicitud, inciarSolicitudDAO);
        inicarSolicitudController.init();
        this.View.jScrollPane1.setViewportView(IniciarSolicitud);
    }
   
    public void setPanelListarSolicitudesProcesadas(){
        ListarSolicitudesProcesadas = new PanelListarSolicitudesProcesadas();
        ListarSolicitudesProcesadas.setVisible(true);
        this.View.jScrollPane1.setViewportView(ListarSolicitudesProcesadas);
    }
    
    public void setPanelSolicitudesACompletarOGAGTD(){
        SolicitudesACompletarOGAGTD = new PanelSolicitudesACompletarOGAGTD();
        SolicitudesACompletarOGAGTD.setVisible(true);
        this.View.jScrollPane1.setViewportView(SolicitudesACompletarOGAGTD);
    }
    
    
    
    
}
