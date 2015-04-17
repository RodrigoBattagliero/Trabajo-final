/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.CompletarDatosOgagtdDAO;
import model.ConfirmarLoteDAO;
import model.ExpedienteDAO;
import model.HistorialSolicitudesDAO;
import model.OgagtdDAO;
import model.RegistroUnicoDAO;
import model.SolicitudDAO;
import view.PanelAdministrarRegistroUnico;
import view.PanelCompletarDatosDeAlojamientoOComidaVIEJO;
import view.PanelCompletarDatosDeDocentes;
import view.PanelCompletarDatosDeTrasladoVIEJO;
import view.PanelConfirmarLoteDeSolicitudesProcesadas;
import view.PanelConsultarRegistroUnico;
import view.PanelCrearRegistroUnico;
import view.PanelGenerarExpediente;
import view.PanelIniciarSolicitud;
import view.PanelHistorialSolicitudesProcesadas;
import view.PanelSolicitudesACompletarOGAGTD;
import view.VentanaOgagtd;

/**
 *
 * @author rodrigo
 */
public class OgagtdController {
    
    private VentanaOgagtd view;
    private OgagtdDAO modelDAO;
    
    //
    private PanelAdministrarRegistroUnico administrarRegistroUnico;
    //
    private PanelCompletarDatosDeAlojamientoOComidaVIEJO completarDatosDeAlojamientoOComida;
    //
    private PanelCompletarDatosDeDocentes completarDatosDeDocentes;
    //
    private PanelCompletarDatosDeTrasladoVIEJO completarDatosDeTrasalado;
    // 
    private ConfirmarLoteController confirmarLoteController;
    //
    private PanelCrearRegistroUnico crearRegistroUnico;
    
    // MVC Iniciar solicitud
    private PanelIniciarSolicitud iniciarSolicitud;
    private IniciarSolicitudController inicarSolicitudController;
    private SolicitudDAO inciarSolicitudDAO;
    
    // Historial de solicitudes procesadas
    private PanelHistorialSolicitudesProcesadas historialSolicitudesView;
    private HistorialSolicitudesDAO historialSolicitudesDAO;
    private historialSolicitudesController historialSolicitudesController;
    
    // Solicitudes a completar por OGAGTD
    private PanelSolicitudesACompletarOGAGTD solicitudesACompletarView;
    private CompletarDatosOgagtdDAO solicitudesACompletarDAO;
    private CompletarDatosOgagtdController solcitudesACompletarController;
    
    // Generar solicitudes
    private PanelGenerarExpediente generarExpedienteView;
    private ExpedienteDAO generarExpedienteModel;
    private ExpedienteController generarExpedienteController;
    
    // Registro unico
    private PanelConsultarRegistroUnico registroUnicoView;
    private RegistroUnicoDAO registroUnicoModel;
    private CrearRegistroUnicoController registroUnicoController;
    

    public OgagtdController(VentanaOgagtd View, OgagtdDAO modelDAO) {
        this.view = View;
        this.modelDAO = modelDAO;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.setLocationRelativeTo(null);
        this.view.mItemIniciarNuevaSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemIniciarNuevaSolicitudActionPerformed(evt);
            }
        });
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
        
        this.view.mItemGenerarNuevoExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemGenerarNuevoExpedienteActionPerformed(evt);
            }
        });
        
        this.view.mItemListarHistorialExpedientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemListarHistorialExpedientesActionPerformed(evt);
            }
        });
        
        this.view.mItemConsultarRegistroUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemConsultarRegistroUnicoActionPerformed(evt);
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
        setPanelSolicitudesACompletar();
    }

    
    public void mItemConfirmarLoteDeSolicitudesProcesadasActionPerformed(java.awt.event.ActionEvent evt){
        setPanelConfirmarLoteDeSolicitudesProcesadas();
    }
    
    public void mItemListarHistorialDeSolicitudesProcesadasActionPerformed(java.awt.event.ActionEvent evt){
        setPanelListarSolicitudesProcesadas();
    }
    
    private void mItemGenerarNuevoExpedienteActionPerformed(ActionEvent evt) {
        setPanelGenerarExpediente();
    }
    
    private void mItemListarHistorialExpedientesActionPerformed(ActionEvent evt) {
        setHistorialExpediente();
    }
    
    private void mItemConsultarRegistroUnicoActionPerformed(ActionEvent evt) {
        setConsultarRegistroUnico();
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
    public void setPanelCompletarDatosDeAlojamientoOComida(){
        completarDatosDeAlojamientoOComida = new PanelCompletarDatosDeAlojamientoOComidaVIEJO();
        completarDatosDeAlojamientoOComida.setVisible(true);
        this.view.jScrollPane1.setViewportView(completarDatosDeAlojamientoOComida);
    }
    
    public void setPanelCompletarDatosDeDocentes(){
        completarDatosDeDocentes = new PanelCompletarDatosDeDocentes();
        completarDatosDeDocentes.setVisible(true);
        this.view.jScrollPane1.setViewportView(completarDatosDeDocentes);
    }
    
    public void setCompletarDatosDeTrasalado(){
        completarDatosDeTrasalado = new PanelCompletarDatosDeTrasladoVIEJO();
        completarDatosDeTrasalado.setVisible(true);
        this.view.jScrollPane1.setViewportView(completarDatosDeTrasalado);
    }
    
    public void setPanelConfirmarLoteDeSolicitudesProcesadas(){
        ConfirmarLoteController confirmarLoteController = new ConfirmarLoteController(new PanelConfirmarLoteDeSolicitudesProcesadas(), new RegistroUnicoDAO());
        confirmarLoteController.init();
        this.view.jScrollPane1.setViewportView(confirmarLoteController.getView());
        
    }
    
    public void setPanelCrearRegistroUnico(){
        crearRegistroUnico = new PanelCrearRegistroUnico();
        crearRegistroUnico.setVisible(true);
        this.view.jScrollPane1.setViewportView(crearRegistroUnico);
    }
    
     public void setPanelIniciarSolicitud(){
        iniciarSolicitud = new PanelIniciarSolicitud();
        inciarSolicitudDAO = new SolicitudDAO();
        inicarSolicitudController = new IniciarSolicitudController(iniciarSolicitud, inciarSolicitudDAO);
        inicarSolicitudController.init();
        this.view.jScrollPane1.setViewportView(iniciarSolicitud);
    }
   
    public void setPanelListarSolicitudesProcesadas(){
        PanelHistorialSolicitudesProcesadas historialSolicitudesView = new PanelHistorialSolicitudesProcesadas();
        HistorialSolicitudesDAO historialSolicitudesDAO = new HistorialSolicitudesDAO();
        historialSolicitudesController historialSolicitudesController = new historialSolicitudesController(historialSolicitudesView, historialSolicitudesDAO);
        historialSolicitudesController.init();
        this.view.jScrollPane1.setViewportView(historialSolicitudesView);
    }
    
    public void setPanelSolicitudesACompletarOGAGTD(){
        solicitudesACompletarView = new PanelSolicitudesACompletarOGAGTD();
        solicitudesACompletarView.setVisible(true);
        this.view.jScrollPane1.setViewportView(solicitudesACompletarView);
    }
    
    public void setPanelSolicitudesACompletar(){
        solcitudesACompletarController = new CompletarDatosOgagtdController(
                new PanelSolicitudesACompletarOGAGTD(),
                new CompletarDatosOgagtdDAO()
             );
        solcitudesACompletarController.init();
        
        this.view.jScrollPane1.setViewportView(solcitudesACompletarController.getView());
        
    }
    
    public void setPanelGenerarExpediente(){
        generarExpedienteView = new PanelGenerarExpediente();
        generarExpedienteModel = new ExpedienteDAO();
        generarExpedienteController = new ExpedienteController(generarExpedienteView, generarExpedienteModel);
        generarExpedienteController.init();
        
        this.view.jScrollPane1.setViewportView(generarExpedienteView);
    }
    
    public void setHistorialExpediente(){
        
    }
    
    public void setConsultarRegistroUnico(){
        registroUnicoView = new PanelConsultarRegistroUnico();
        //registroUnicoModel = new RegistroUnicoDAO();
        //registroUnicoController = new CrearRegistroUnicoController(crearRegistroUnico, registroUnicoModel, inicarSolicitudController)
        this.view.jScrollPane1.setViewportView(registroUnicoView);
    }
    
    
    
}
