/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ogagtd;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.ComprobantesDAO;
import model.ComprobantesDTO;
import model.DatosAlojamientoComidaDAO;
import model.DatosTrasladoDAO;
import model.DatosTrasladoDTO;
import model.SolicitudDAO;
import model.SolicitudDTO;
import resources.DateManager;
import view.PanelCompletarDatosDeAlojamientoOComida;
import view.PanelCompletarDatosDeTraslado;

/**
 *
 * @author rodrigo
 */
public class DatosTrasladoController {
    private IniciarSolicitudController solicitudController;
    private PanelCompletarDatosDeTraslado view;
    private DatosTrasladoDAO model;
    private DatosTrasladoDTO datosTrasladoDTO;
    
    // Comprobtantes
    private ComprobantesDAO comprobanteDAO;
    
    // Datos alojamiento comida
    DatosAlojamientoComidaController datosAlojamientoController;
    
    // Variables (datos) propias
    private String numeroComprobante;
    private String proveedor;
    private String salidaDesde;
    private DateManager salidaFechaHora;
    private String regresoHasta;
    private DateManager regresiFechaHora;
    private Double importe;
    private String observaciones;
    private ArrayList<ComprobantesDTO> comprobantes;
    private ArrayList<DatosTrasladoDTO> traslados;
    

    public DatosTrasladoController(PanelCompletarDatosDeTraslado view, DatosTrasladoDAO model,IniciarSolicitudController solicitudController) {
        this.view = view;
        this.model = model;
        this.solicitudController = solicitudController;
        
        this.datosAlojamientoController = null;
        
        this.numeroComprobante = null;
        this.proveedor = null;
        this.salidaDesde = null;
        this.salidaFechaHora = null;
        this.regresoHasta = null;
        this.regresiFechaHora = null;
        this.importe = null;
        this.observaciones = null;
        this.comprobantes = new ArrayList();
        this.traslados = new ArrayList();
        
        this.comprobanteDAO = null;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnAgregarDatosAlojamientoComida.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDatosAlojamientoComidaActionPerformed(evt);
            }
        });
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarPerformed(evt);
            }
        });
        
        this.view.btnAgregarFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFilaActionPerformed(evt);
            }
        });
        
        this.view.btnEliminarFila.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFilaActionPerformed(evt);
            }
        });
    }
    
    public PanelCompletarDatosDeTraslado getView(){
        return this.view;
    }
    
    public void btnAgregarDatosAlojamientoComidaActionPerformed(java.awt.event.ActionEvent evt){
        if(datosAlojamientoController == null){
            datosAlojamientoController = 
                    new DatosAlojamientoComidaController(
                            new PanelCompletarDatosDeAlojamientoOComida(), 
                            new DatosAlojamientoComidaDAO(),
                            this);
        }
        datosAlojamientoController.init();
        
        this.view.pnlDatosAlojamientoComida.add(datosAlojamientoController.getView());
        this.view.updateUI();
        
        disableFields();
        
    }
    
    private void btnAceptarPerformed(ActionEvent evt) {
        if(this.verificarDatosDeEntrada()){
            this.disableFields();
            this.solicitudController.crearRegistroUnico();
        }
    }
    

    private boolean verificarDatosDeEntrada(){
        comprobantes = new ArrayList();
        traslados = new ArrayList();
        DefaultTableModel modelo = (DefaultTableModel) this.view.tblDatosTraslado.getModel();
        int filas = modelo.getRowCount();
        boolean b = true;
        for(int i=0; i < filas; i++){
            if(modelo.getValueAt(i , 0) != null){
                this.numeroComprobante = String.valueOf(modelo.getValueAt(i , 0));
            }else{
                b = false;
            }
            
            if(modelo.getValueAt(i , 1) != null){
                this.proveedor = String.valueOf(modelo.getValueAt(i , 1));
            }else{
                b = false;
            }
            
            if(modelo.getValueAt(i, 2) != null){
                this.importe = new Double(String.valueOf(modelo.getValueAt(i, 2)));
            }else{
                b = false;
            }
            
            if(modelo.getValueAt(i, 3) != null){
                this.salidaDesde = String.valueOf(modelo.getValueAt(i, 3));
            }else{
                b = false;
            }
            
            if(modelo.getValueAt(i, 4) != null){
                try{
                    this.salidaFechaHora = new DateManager(String.valueOf(modelo.getValueAt(i, 4)));
                }catch (NullPointerException e){
                    this.salidaFechaHora = new DateManager("00-00-0000 00:00:00");
                }
            }else{
                b = false;
            }
            
            if(modelo.getValueAt(i, 5) != null){
                this.regresoHasta = String.valueOf(modelo.getValueAt(i, 5));
            }else{
                b = false;
            }
            
            if(modelo.getValueAt(i, 6) != null){
                try{
                    this.regresiFechaHora = new DateManager(String.valueOf(modelo.getValueAt(i, 6)));
                }catch (NullPointerException e){
                    this.regresiFechaHora = new DateManager("00-00-0000 00:00:00");
                }
            }else{
                b = false;
            }
                
            if(modelo.getValueAt(i, 7) != null)
                this.observaciones = String.valueOf(modelo.getValueAt(i, 7));
            else
                this.observaciones = "";
            
            if(b){
                comprobantes.add(new ComprobantesDTO(0, importe, 0, numeroComprobante, proveedor, observaciones));
                traslados.add(new DatosTrasladoDTO(0, salidaDesde, regresoHasta,salidaFechaHora, regresiFechaHora,0));
            }else{
                if(this.observaciones.length() > 0){
                    int op = JOptionPane.showConfirmDialog(this.view, 
                            "Los campos obligatorios no están completos. \n¿Desea continuar con el proceso de la solicitud?",
                            "Campos incompletos.",
                            0);
                    if(op == 0)
                        return true;
                    if(op == 1)
                        return false;
                }else{
                    JOptionPane.showMessageDialog(view, 
                            "Los campos obligatorios no están completos.\nSi desea procesar la solicitud de todo modos, agregar una OBSERVACIÓN.");
                    return false;
                }
            }
        }
        return b;
    }
    
    private void disableFields(){
        this.view.tblDatosTraslado.setEnabled(false);
        this.view.btnAgregarDatosAlojamientoComida.setEnabled(false);
        this.view.btnAceptar.setEnabled(false);
        this.view.btnCancelar.setEnabled(false);
        this.view.btnAgregarFila.setEnabled(false);
        this.view.btnEliminarFila.setEnabled(false);
    }
    
    private void enableFields(){
        this.view.tblDatosTraslado.setEnabled(true);
        this.view.btnAgregarDatosAlojamientoComida.setEnabled(true);
        this.view.btnAceptar.setEnabled(true);
        this.view.btnCancelar.setEnabled(true);
        this.view.btnAgregarFila.setEnabled(true);
        this.view.btnEliminarFila.setEnabled(true);
    }
    
    private void btnAgregarFilaActionPerformed(ActionEvent evt) {
        DefaultTableModel modelo = (DefaultTableModel) this.view.tblDatosTraslado.getModel();
        modelo.addRow
            (new Vector(8));
    }
    
    private void btnEliminarFilaActionPerformed(ActionEvent evt) {
        DefaultTableModel modelo =(DefaultTableModel) this.view.tblDatosTraslado.getModel();
        if(modelo.getRowCount() > 0)
            modelo.removeRow(modelo.getRowCount()-1);
    }
    
    public void callBack(){
        enableFields();
    }
    
    public void guardarDatos(int idSolicitud){
        int tamano = this.comprobantes.size();
        for(int i = 0; i < tamano; i++){
            this.comprobanteDAO = new ComprobantesDAO();
            this.comprobantes.get(i).setId_solicitud(idSolicitud);
            int idComprobante = this.comprobanteDAO.create(this.comprobantes.get(i));
            this.traslados.get(i).setId_comprobante(idComprobante);
            this.model.create(this.traslados.get(i));
        }
        try{
            this.datosAlojamientoController.guardarDatos(idSolicitud);
        }catch(NullPointerException e){
            
        }
    }
    
    public void setEnable(){
        
        this.view.removeAll();
        this.view.setVisible(false);
        this.view = null;
        this.model = null;
        this.solicitudController = null;
        
        this.datosAlojamientoController = null;
        
        this.numeroComprobante = null;
        this.proveedor = null;
        this.salidaDesde = null;
        this.salidaFechaHora = null;
        this.regresoHasta = null;
        this.regresiFechaHora = null;
        this.importe = null;
        this.observaciones = null;
        this.comprobantes = null;
        this.traslados = null;
        
        this.comprobanteDAO = null;
 
        
    }
}
