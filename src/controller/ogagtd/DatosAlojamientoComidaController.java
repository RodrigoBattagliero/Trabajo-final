/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ogagtd;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ComprobantesDAO;
import model.ComprobantesDTO;
import model.DatosAlojamientoComidaDAO;
import model.DatosAlojamientoComidaDTO;
import model.DatosTrasladoDTO;
import view.PanelCompletarDatosDeAlojamientoOComida;

/**
 *
 * @author rodrigo
 */
public class DatosAlojamientoComidaController {
    
    private PanelCompletarDatosDeAlojamientoOComida view;
    private DatosAlojamientoComidaDAO model;
    private DatosTrasladoController trasladoController;
    
    // Datos propios
    private String numero_comprobante;
    private String proveedor;
    private String descripcion;
    private Double importe;
    private String observaciones;
    
    private ArrayList<ComprobantesDTO> comprobanteDTO;
    private ComprobantesDAO comprobateDAO;
    private ArrayList<DatosAlojamientoComidaDTO> datos;

    public DatosAlojamientoComidaController(PanelCompletarDatosDeAlojamientoOComida view, DatosAlojamientoComidaDAO model,DatosTrasladoController trasladoController) {
        this.view = view;
        this.model = model;
        this.trasladoController = trasladoController;
        
        this.numero_comprobante = null;
        this.proveedor = null;
        this.descripcion = null;
        this.importe = null;
        this.observaciones = null;
        
        this.datos = null;
        this.comprobanteDTO = null;
        this.comprobateDAO = null;
        
    }
    
   public void init(){
       this.view.setVisible(true);
       this.enableFields();
       this.view.btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
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
   
   public PanelCompletarDatosDeAlojamientoOComida getView(){
       return  this.view;
   }
   
   private void btnAgregarActionPerformed(ActionEvent evt) {
       if(this.verificarDatos()){
            disableFields();   
            this.trasladoController.callBack();
       }
   }

   private boolean verificarDatos(){
       this.datos = new ArrayList();
       this.comprobanteDTO = new ArrayList();
       String importeStr = "";
       DefaultTableModel modelo = (DefaultTableModel) this.view.tblDatosAlojamientoComida.getModel();
       int cantidad = modelo.getRowCount();
       boolean b = true;
       for (int i = 0; i < cantidad; i++) {
           try{
                this.numero_comprobante = String.valueOf(modelo.getValueAt(i, 0));
                this.proveedor = String.valueOf(modelo.getValueAt(i, 1));
                this.descripcion = String.valueOf(modelo.getValueAt(i, 2));
                importeStr = String.valueOf(modelo.getValueAt(i, 3));
           }catch(NullPointerException e){
               b = false;
           }
           if(b){
                try{
                    this.importe = Double.parseDouble(importeStr);
                }catch(Exception e){
                    b = false;
                }
           }
           /*
           if(modelo.getValueAt(i, 0) != null)
               this.numero_comprobante = String.valueOf(modelo.getValueAt(i, 0));
           else
               b = false;
           
           if(modelo.getValueAt(i, 1) != null)
               this.proveedor = String.valueOf(modelo.getValueAt(i, 1));
           else
               b = false;
           
           if(modelo.getValueAt(i, 2) != null)
               this.descripcion = String.valueOf(modelo.getValueAt(i, 2));
           else
               b = false;
           
           if(modelo.getValueAt(i, 3) != null)
               this.importe = new Double(String.valueOf(modelo.getValueAt(i, 3)));
           else
               b = false;
           */
           
           if(modelo.getValueAt(i, 4) != null)
               this.observaciones = String.valueOf(modelo.getValueAt(i, 4));
           else
               this.observaciones = "";
           System.out.println(this.observaciones.length());
           if(b){
                
                comprobanteDTO.add(new ComprobantesDTO(0, importe, 0, numero_comprobante, proveedor, observaciones));
                datos.add(new DatosAlojamientoComidaDTO(0, 1, descripcion, 0));
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
       return true;
   }
    
   private void disableFields() {
       this.view.tblDatosAlojamientoComida.setEnabled(false);
       this.view.btnAgregar.setEnabled(false);
       this.view.btnCancelar.setEnabled(false);
       this.view.btnAgregarFila.setEnabled(false);
       this.view.btnEliminarFila.setEnabled(false);
   }
   
   private void enableFields() {
       this.view.tblDatosAlojamientoComida.setEnabled(true);
       this.view.btnAgregar.setEnabled(true);
       this.view.btnCancelar.setEnabled(true);
       this.view.btnAgregarFila.setEnabled(true);
       this.view.btnEliminarFila.setEnabled(true);
   }
   
   public void guardarDatos(int idSolicitud){
       int cant = this.datos.size();
       comprobateDAO = new ComprobantesDAO();
       for (int i = 0; i < cant; i++) {
          this.comprobanteDTO.get(i).setId_solicitud(idSolicitud);
          int idComprobante = comprobateDAO.create(this.comprobanteDTO.get(i));
          this.datos.get(i).setId_comprobante(idComprobante);
          this.model.create(this.datos.get(i));
       }
   }
   
    private void btnAgregarFilaActionPerformed(ActionEvent evt) {
        DefaultTableModel modelo = (DefaultTableModel) this.view.tblDatosAlojamientoComida.getModel();
        modelo.addRow
            (new Object[][]{
                {null, null, null, null, null, null, null, null}
            });
    }
    
    private void btnEliminarFilaActionPerformed(ActionEvent evt) {
        DefaultTableModel modelo =(DefaultTableModel) this.view.tblDatosAlojamientoComida.getModel();
        if(modelo.getRowCount() > 0)
            modelo.removeRow(modelo.getRowCount()-1);
    }
    
    public void setEnable(){
        this.view.removeAll();
        this.view.setVisible(false);
        this.view = null;
        this.model = null;
        this.trasladoController = null;
        
        this.numero_comprobante = null;
        this.proveedor = null;
        this.descripcion = null;
        this.importe = null;
        this.observaciones = null;
        
        this.datos = null;
        this.comprobanteDTO = null;
        this.comprobateDAO = null;
        
       
   }
}
