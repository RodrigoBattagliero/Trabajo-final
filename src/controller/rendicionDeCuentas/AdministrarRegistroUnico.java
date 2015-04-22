/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.rendicionDeCuentas;

import controller.bedel.*;
import controller.ogagtd.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import model.EstadosDAO;
import model.EstadosDTO;
import model.RegistroUnicoDAO;
import model.RegistroUnicoDTO;
import model.SolicitudDAO;
import model.SolicitudDTO;
import view.PanelAdministrarRegistroUnico;

/**
 *
 * @author rodrigo
 */
public class AdministrarRegistroUnico {
    
    private PanelAdministrarRegistroUnico view;
    private RegistroUnicoDAO model;
    private RegistroUnicoDTO registroUnicoDTO;
    
    private SolicitudDTO solicitudDTO;
    private int idRegistroUnico;

    public AdministrarRegistroUnico(PanelAdministrarRegistroUnico view, RegistroUnicoDAO model,int idRegistroUnico) {
        this.view = view;
        this.model = model;
        this.idRegistroUnico = idRegistroUnico;
        
        if(this.idRegistroUnico != 0){
            registroUnicoDTO = this.model.selectOne(this.idRegistroUnico);
            solicitudDTO = (SolicitudDTO) new SolicitudDAO().selectOne(registroUnicoDTO.getId_solicitud());
        }else{
            registroUnicoDTO = null;
            solicitudDTO = null;
        }
        
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        this.setEstados();
        if(registroUnicoDTO != null){
            this.view.txtNumeroSolicitud.setText(String.valueOf(this.solicitudDTO.getNumero_solicutd()));
            this.view.txtPerfil.setText(String.valueOf(getIdArea()));
            this.view.txtFecha.setText(String.valueOf(this.registroUnicoDTO.getFecha_entrada().getFechaString()));
        }
    }
    
    public PanelAdministrarRegistroUnico getView(){
        return this.view;
    }
    
    private void setEstados(){
        ArrayList<EstadosDTO> estados = (ArrayList<EstadosDTO>) new EstadosDAO().selectAll();
        int size = estados.size();
        for (int i = 0; i < size; i++) {
            this.view.cbxEstado.addItem(estados.get(i).getNombre());
        }
        
    }
    
    private int getIdArea(){
        return 1;
    }
    
    private void actualizarRegistroUnicoDTO(){
        int idEst = this.view.cbxEstado.getSelectedIndex();
        this.registroUnicoDTO.setId_estado(idEst);
        this.registroUnicoDTO.setObservaciones(this.view.txtObservaciones.getText());
    }
    
    private void btnAceptarActionPerformed(ActionEvent evt) {
        actualizarRegistroUnicoDTO();
        this.model.update(this.registroUnicoDTO);
    }
    
}
