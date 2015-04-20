/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sedeinterior;

import java.awt.event.ActionEvent;
import model.CompletarDatosSedeInteriorDAO;
import model.ExpedienteDAO;
import model.HistorialSolicitudesDAO;
import model.RegistroUnicoDAO;
import model.SedeInteriorDAO;
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
import view.PanelSolicitudesACompletarSedeInterior;
import view.VentanaSedeInterior;

/**
 *
 * @author rodrigo
 */
public class SedeInteriorController {
    
    private VentanaSedeInterior view;
    private SedeInteriorDAO modelDAO;
    
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
    private historialSolicitudesController historialSolicitudesController;
    
    // Solicitudes a completar por Sede interior
    private PanelSolicitudesACompletarSedeInterior solicitudesACompletarView;
    
    // Generar solicitudes
    private PanelGenerarExpediente generarExpedienteView;
    private ExpedienteDAO generarExpedienteModel;
    private ExpedienteController generarExpedienteController;
    
    // Registro unico
    private PanelConsultarRegistroUnico registroUnicoView;
    private RegistroUnicoDAO registroUnicoModel;
    private ConsultarRegistroUnicoController registroUnicoController;
    

    public SedeInteriorController(VentanaSedeInterior View, SedeInteriorDAO modelDAO) {
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
        historialSolicitudesController historialSolicitudesController = new historialSolicitudesController(
                new PanelHistorialSolicitudesProcesadas(), 
                new HistorialSolicitudesDAO());
        historialSolicitudesController.init();
        this.view.jScrollPane1.setViewportView(historialSolicitudesController.getView());
    }
    
    public void setPanelSolicitudesACompletar(){
        CompletarDatosSedeInteriorController solcitudesACompletarController = new CompletarDatosSedeInteriorController(
                new PanelSolicitudesACompletarSedeInterior(),
                new CompletarDatosSedeInteriorDAO()
             );
        solcitudesACompletarController.init();
        
        this.view.jScrollPane1.setViewportView(solcitudesACompletarController.getView());
        
    }
    
    public void setConsultarRegistroUnico(){
        registroUnicoController = new ConsultarRegistroUnicoController(
                new PanelConsultarRegistroUnico(),
                new RegistroUnicoDAO()
        );
        registroUnicoController.init();
        this.view.jScrollPane1.setViewportView(registroUnicoController.getView());
    }
    
    
    
}
