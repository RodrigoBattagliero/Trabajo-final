/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sedeinterior;

import controller.ogagtd.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.AreasDAO;
import model.AreasDTO;
import model.EstadosDAO;
import model.EstadosDTO;
import model.RegistroUnicoDAO;
import model.RegistroUnicoDTO;
import resources.DateManager;
import view.PanelCrearRegistroUnico;

/**
 *
 * @author rodrigo
 */
public class CrearRegistroUnicoController {
    private IniciarSolicitudController solicitudController;
    
    private PanelCrearRegistroUnico view;
    private RegistroUnicoDAO model;
    private RegistroUnicoDTO registroDTO;
    
    // Datos propios
    private DateManager fechaEntrada;
    private DateManager fechaSalida;
    private boolean confirmado;
    private String observaciones;
    private int id_solicitud;
    private int id_estado;
    private int id_area;
    private ArrayList<RegistroUnicoDTO> registros;

    public CrearRegistroUnicoController(PanelCrearRegistroUnico view, RegistroUnicoDAO model,IniciarSolicitudController solicitudController) {
        this.view = view;
        this.model = model;
        this.solicitudController = solicitudController;
        
        this.registroDTO = null;
        // Datos propios
        this.fechaEntrada = new DateManager(this.solicitudController.getFechaPresentacion().getFechaDate());
        this.fechaSalida = null;
        this.confirmado = false;
        this.observaciones = null;
        this.id_area = 0;
        this.id_estado = 0;
        this.id_solicitud = 0;
        this.registros = null;
    }
    
    public PanelCrearRegistroUnico getView(){
        return this.view;
    }
    
    public void init(){
        this.view.txtFechaPresentacion.setDate(this.fechaEntrada.getFechaDate());
        this.setEstados();
        this.view.txtPerfil.setText(getPrimeraArea().getNombre());
        this.view.setVisible(true);
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
    }
    
    private void setEstados(){
        ArrayList<EstadosDTO> estados = (ArrayList<EstadosDTO>) new EstadosDAO().selectAll();
        int size = estados.size();
        for (int i = 0; i < size; i++) {
            this.view.cmbEstado.addItem(estados.get(i).getNombre());
        }
        
    }
    
    private AreasDTO getPrimeraArea(){
        AreasDTO a = new AreasDAO().selectOrden(1);
        return a;
    }
    
    private void btnAceptarActionPerformed(ActionEvent evt) {
        if(verificarDatos()){
           this.datosTemporales();
           this.solicitudController.confirmar();
        }
    }
    
    private boolean verificarDatos(){
        boolean b = true;
        try{ 
            this.fechaEntrada = new DateManager(this.view.txtFechaPresentacion.getDate());
        }catch(NullPointerException e){
            this.fechaEntrada = new DateManager("00-00-0000 00:00:00");
        }
        this.confirmado = this.view.chbConfirmado.isSelected();
        this.id_area = getPrimeraArea().getId();
        this.id_estado = this.view.cmbEstado.getSelectedIndex();
        ++this.id_estado;
        if(this.id_estado == 0 || this.fechaEntrada == null){
            JOptionPane.showMessageDialog(view, 
                    "Debe competar los campos obligatorios");
            b = false;
            
        }
        
        return b;
    }
    
    private void datosTemporales(){
        this.registros = new ArrayList();
        this.fechaSalida = new DateManager(new Date());
        this.observaciones = "";
        this.id_solicitud = 0;
        this.registros.add(new RegistroUnicoDTO(0, fechaEntrada, fechaSalida, confirmado, observaciones, id_solicitud, id_area, id_estado));
        this.registros.add(new RegistroUnicoDTO(0,new DateManager(new Date()),new DateManager("00-00-0000 00:00:00"),false,null,id_solicitud,3,1));
    }
    
    public void guardarDatos(int idSolicitud){
        int cant = registros.size();
        for (int i = 0; i < cant; i++) {
            registros.get(i).setId_solicitud(idSolicitud);
            model.create(registros.get(i));
        }
    }
    
    public void setEnable(){
        this.view.removeAll();
        this.view = null;
        this.model = null;
        this.solicitudController = null;
        
        this.registroDTO = null;
        // Datos propios
        this.fechaEntrada = null;
        this.fechaSalida = null;
        this.confirmado = false;
        this.observaciones = null;
        this.id_area = 0;
        this.id_estado = 0;
        this.id_solicitud = 0;
        this.registros = null;
        
        
    }
    
    
}
