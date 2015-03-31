/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.DatosDocentesDAO;
import model.DatosTrasladoDAO;
import model.RegistroUnicoDAO;
import model.SolicitudDAO;
import view.PanelCompletarDatosDeDocentes;
import view.PanelCompletarDatosDeTraslado;
import view.PanelCrearRegistroUnico;
import view.PanelIniciarSolicitud;

/**
 *
 * @author rodrigo
 */
public class IniciarSolicitudController {
    
    // Iniciar solicitud
    private PanelIniciarSolicitud view;
    private SolicitudDAO model;
    
    // Datos docentes
    private PanelCompletarDatosDeDocentes datosDocenteView;
    private DatosDocentesDAO datosDocentesDAO;
    private DatosDocentesController datosDocentesController;
    
    // Comprobante de traslado
    private PanelCompletarDatosDeTraslado datosTrasladoView;
    private DatosTrasladoDAO datosTrasladoDAO;
    private DatosTrasladoController datosTrasladoController;
    
    // Registro Unico
    private PanelCrearRegistroUnico crearRegistroUnicoView;
    private RegistroUnicoDAO crearRegistroUnicoModel;
    private CrearRegistroUnicoController crearRegitroUnicoController;

    public IniciarSolicitudController(PanelIniciarSolicitud v, SolicitudDAO m) {
        this.view = v;
        this.model = m;
        
        // Datos docentes
        this.datosDocenteView = null;
        this.datosDocentesDAO = null;
        this.datosDocentesController = null;
        
        // Comprobante de traslado
        this.datosTrasladoView = null;
        this.datosTrasladoDAO = null;
        this.datosTrasladoController = null;
        
        // Crear registro unico
        this.crearRegistroUnicoView = null;
        this.crearRegistroUnicoModel = null;
        this.crearRegitroUnicoController = null;
        
        
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
    }
    
    public void btnGuardarActionPerformed(java.awt.event.ActionEvent evt){
        datosDocentes();
    }
    
    public void disableFields(){
        this.view.txtFechaDePresentacion.setEnabled(false);
        this.view.txtNumeroDeSolicitud.setEnabled(false);
        this.view.txtObservaciones.setEditable(false);
        this.view.btnGuardar.setEnabled(false);
        this.view.btnCancelar.setEnabled(false);
    }
    
    // Datos docentes
    public void datosDocentes(){
        // Datos docentes
        datosDocenteView = new PanelCompletarDatosDeDocentes();
        datosDocentesDAO = new DatosDocentesDAO();
        datosDocentesController = new DatosDocentesController(datosDocenteView,datosDocentesDAO,this);
        
        datosDocentesController.init();
        this.view.pnlDatosDocente.add(datosDocenteView);
        this.view.pnlDatosDocente.setVisible(true);
        disableFields();
        this.view.updateUI();
    }
    
    // Comprobante de traslado
    public void comprobanteDeTraslado(){
        datosTrasladoView = new PanelCompletarDatosDeTraslado();
        datosTrasladoDAO = new DatosTrasladoDAO();
        datosTrasladoController = new DatosTrasladoController(datosTrasladoView,datosTrasladoDAO,this);
        datosTrasladoController.init();
        
        this.view.pnlComprobanteTraslado.add(datosTrasladoView);
        this.view.pnlComprobanteTraslado.setVisible(true);
        this.view.updateUI();
    }
    
    // Crear Registro Unico
    public void crearRegistroUnico(){
        this.crearRegistroUnicoView = new PanelCrearRegistroUnico();
        this.crearRegistroUnicoModel = new RegistroUnicoDAO();
        this.crearRegitroUnicoController = new CrearRegistroUnicoController(crearRegistroUnicoView, crearRegistroUnicoModel,this);
        this.crearRegitroUnicoController.init();
        
        this.view.pnlRegistroUnico.add(crearRegistroUnicoView);
        this.view.pnlRegistroUnico.setVisible(true);
        this.view.updateUI();
    }
    
    // Confirmar registro de solicitud
    public int confirmar(){
        return JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea guardar la solicitud?");
    }
    
    
}
