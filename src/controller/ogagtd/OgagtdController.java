/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ogagtd;

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
    private ConsultarRegistroUnicoController registroUnicoController;
    
    // Solicitudes iniciadas en sede interior
    private SolicitudesIniciadasEnSedeInteriorController solicitudesIniadasEnSedeInterior;
    

    public OgagtdController(VentanaOgagtd View, OgagtdDAO modelDAO) {
        this.view = View;
        this.modelDAO = modelDAO;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.setLocationRelativeTo(null);
        this.view.mItemIniciarNuevaSolicitud.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemIniciarNuevaSolicitudActionPerformed(evt);
            }
        });
        this.view.mItemSolicitudesACompletar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemSolicitudesACompletarActionPerformed(evt);
            }
        });
        this.view.mItemConfirmarLoteDeSolicitudesProcesadas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemConfirmarLoteDeSolicitudesProcesadasActionPerformed(evt);
            }
        });
        this.view.mItemListarHistorialDeSolicitudesProcesadas.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemListarHistorialDeSolicitudesProcesadasActionPerformed(evt);
            }
        });
        this.view.menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSalirActionPerformed(evt);
            }
        });
        
        this.view.mItemGenerarNuevoExpediente.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemGenerarNuevoExpedienteActionPerformed(evt);
            }
        });
        
        this.view.mItemListarHistorialExpedientes.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemListarHistorialExpedientesActionPerformed(evt);
            }
        });
        
        this.view.mItemConsultarRegistroUnico.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemConsultarRegistroUnicoActionPerformed(evt);
            }
        });
        
        this.view.mItemSedeInterior.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemSedeInteriorActionPerformed(evt);
            }
        });
        
    }
    
    
    /*
    *
    * Listener de botones
    *
    */
    private void mItemIniciarNuevaSolicitudActionPerformed(java.awt.event.ActionEvent evt){
        setPanelIniciarSolicitud();
    }
    
    private void mItemSolicitudesACompletarActionPerformed(java.awt.event.ActionEvent evt){
        setPanelSolicitudesACompletar();
    }

    
    private void mItemConfirmarLoteDeSolicitudesProcesadasActionPerformed(java.awt.event.ActionEvent evt){
        setPanelConfirmarLoteDeSolicitudesProcesadas();
    }
    
    private void mItemListarHistorialDeSolicitudesProcesadasActionPerformed(java.awt.event.ActionEvent evt){
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
     
    private void menuItemSalirActionPerformed(java.awt.event.ActionEvent evt){
        this.view.removeAll();
        System.exit(0);
    }
    
    private void mItemSedeInteriorActionPerformed(java.awt.event.ActionEvent evt){
        setSolicitudesIniciadasEnSedeInterior();
    }
    
    
    /*
    *
    * Metodos para mostrar los paneles 
    *
    */
    private void setPanelCompletarDatosDeAlojamientoOComida(){
        completarDatosDeAlojamientoOComida = new PanelCompletarDatosDeAlojamientoOComidaVIEJO();
        completarDatosDeAlojamientoOComida.setVisible(true);
        this.view.jScrollPane1.setViewportView(completarDatosDeAlojamientoOComida);
    }
    
    private void setPanelCompletarDatosDeDocentes(){
        completarDatosDeDocentes = new PanelCompletarDatosDeDocentes();
        completarDatosDeDocentes.setVisible(true);
        this.view.jScrollPane1.setViewportView(completarDatosDeDocentes);
    }
    
    private void setCompletarDatosDeTrasalado(){
        completarDatosDeTrasalado = new PanelCompletarDatosDeTrasladoVIEJO();
        completarDatosDeTrasalado.setVisible(true);
        this.view.jScrollPane1.setViewportView(completarDatosDeTrasalado);
    }
    
    private void setPanelConfirmarLoteDeSolicitudesProcesadas(){
        ConfirmarLoteController confirmarLoteController = new ConfirmarLoteController(new PanelConfirmarLoteDeSolicitudesProcesadas(), new RegistroUnicoDAO());
        confirmarLoteController.init();
        this.view.jScrollPane1.setViewportView(confirmarLoteController.getView());
        
    }
    
    private void setPanelCrearRegistroUnico(){
        crearRegistroUnico = new PanelCrearRegistroUnico();
        crearRegistroUnico.setVisible(true);
        this.view.jScrollPane1.setViewportView(crearRegistroUnico);
    }
    
    private void setPanelIniciarSolicitud(){
        iniciarSolicitud = new PanelIniciarSolicitud();
        inciarSolicitudDAO = new SolicitudDAO();
        inicarSolicitudController = new IniciarSolicitudController(iniciarSolicitud, inciarSolicitudDAO);
        inicarSolicitudController.init();
        this.view.jScrollPane1.setViewportView(iniciarSolicitud);
    }
   
    private void setPanelListarSolicitudesProcesadas(){
        historialSolicitudesController historialSolicitudesController = new historialSolicitudesController(
                new PanelHistorialSolicitudesProcesadas(), 
                new HistorialSolicitudesDAO());
        historialSolicitudesController.init();
        this.view.jScrollPane1.setViewportView(historialSolicitudesController.getView());
    }
    
    private void setPanelSolicitudesACompletarOGAGTD(){
        solicitudesACompletarView = new PanelSolicitudesACompletarOGAGTD();
        solicitudesACompletarView.setVisible(true);
        this.view.jScrollPane1.setViewportView(solicitudesACompletarView);
    }
    
    private void setPanelSolicitudesACompletar(){
        solcitudesACompletarController = new CompletarDatosOgagtdController(
                new PanelSolicitudesACompletarOGAGTD(),
                new CompletarDatosOgagtdDAO()
             );
        solcitudesACompletarController.init();
        
        this.view.jScrollPane1.setViewportView(solcitudesACompletarController.getView());
        
    }
    
    private void setPanelGenerarExpediente(){
        generarExpedienteView = new PanelGenerarExpediente();
        generarExpedienteModel = new ExpedienteDAO();
        generarExpedienteController = new ExpedienteController(generarExpedienteView, generarExpedienteModel);
        generarExpedienteController.init();
        
        this.view.jScrollPane1.setViewportView(generarExpedienteView);
    }
    
    private void setHistorialExpediente(){
        
    }
    
    private void setConsultarRegistroUnico(){
        registroUnicoController = new ConsultarRegistroUnicoController(
                new PanelConsultarRegistroUnico(),
                new RegistroUnicoDAO()
        );
        registroUnicoController.init();
        this.view.jScrollPane1.setViewportView(registroUnicoController.getView());
    }
    
    private void setSolicitudesIniciadasEnSedeInterior(){
        solicitudesIniadasEnSedeInterior = new SolicitudesIniciadasEnSedeInteriorController(
                new PanelSolicitudesACompletarOGAGTD(),
                new CompletarDatosOgagtdDAO()
        );
        solicitudesIniadasEnSedeInterior.init();
         this.view.jScrollPane1.setViewportView(solicitudesIniadasEnSedeInterior.getView());
    }
    
    
    
}
